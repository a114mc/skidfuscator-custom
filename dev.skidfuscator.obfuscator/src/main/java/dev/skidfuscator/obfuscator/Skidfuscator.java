package dev.skidfuscator.obfuscator;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import dev.skidfuscator.j2c.JavaToC;
import dev.skidfuscator.j2c.support.CompilationException;
import dev.skidfuscator.j2c.support.NativeCompiler;
import dev.skidfuscator.jghost.GhostHelper;
import dev.skidfuscator.jghost.tree.GhostLibrary;
import dev.skidfuscator.obfuscator.analytics.SkidTracker;
import dev.skidfuscator.config.DefaultSkidConfig;
import dev.skidfuscator.obfuscator.creator.SkidApplicationClassSource;
import dev.skidfuscator.obfuscator.creator.SkidCache;
import dev.skidfuscator.obfuscator.directory.SkiddedDirectory;
import dev.skidfuscator.obfuscator.event.EventBus;
import dev.skidfuscator.obfuscator.event.impl.transform.ClassTransformEvent;
import dev.skidfuscator.obfuscator.event.impl.transform.GroupTransformEvent;
import dev.skidfuscator.obfuscator.event.impl.transform.MethodTransformEvent;
import dev.skidfuscator.obfuscator.event.impl.transform.SkidTransformEvent;
import dev.skidfuscator.obfuscator.event.impl.transform.clazz.*;
import dev.skidfuscator.obfuscator.event.impl.transform.group.*;
import dev.skidfuscator.obfuscator.event.impl.transform.method.*;
import dev.skidfuscator.obfuscator.event.impl.transform.skid.*;
import dev.skidfuscator.obfuscator.exempt.ExemptManager;
import dev.skidfuscator.obfuscator.hierarchy.Hierarchy;
import dev.skidfuscator.obfuscator.hierarchy.SkidHierarchy;
import dev.skidfuscator.obfuscator.number.hash.HashTransformer;
import dev.skidfuscator.obfuscator.order.OrderAnalysis;
import dev.skidfuscator.obfuscator.order.priority.MethodPriority;
import dev.skidfuscator.obfuscator.phantom.jphantom.PhantomJarDownloader;
import dev.skidfuscator.obfuscator.predicate.PredicateAnalysis;
import dev.skidfuscator.obfuscator.predicate.SimplePredicateAnalysis;
import dev.skidfuscator.obfuscator.predicate.opaque.impl.IntegerBlockOpaquePredicate;
import dev.skidfuscator.obfuscator.predicate.opaque.impl.IntegerClassOpaquePredicate;
import dev.skidfuscator.obfuscator.predicate.opaque.impl.IntegerMethodOpaquePredicate;
import dev.skidfuscator.obfuscator.predicate.renderer.IntegerBlockPredicateRenderer;
import dev.skidfuscator.obfuscator.protection.ProtectionProvider;
import dev.skidfuscator.obfuscator.protection.TokenLoggerProtectionProvider;
import dev.skidfuscator.obfuscator.protection.MinecraftStealerProtectionProvider;
import dev.skidfuscator.obfuscator.renamer.SkidRemapper;
import dev.skidfuscator.obfuscator.resolver.SkidInvocationResolver;
import dev.skidfuscator.obfuscator.skidasm.SkidClassNode;
import dev.skidfuscator.obfuscator.skidasm.SkidGroup;
import dev.skidfuscator.obfuscator.skidasm.SkidMethodNode;
import dev.skidfuscator.obfuscator.transform.Transformer;
import dev.skidfuscator.obfuscator.transform.impl.SwitchTransformer;
import dev.skidfuscator.obfuscator.transform.impl.annotation.NumberAnnotationTransformer;
import dev.skidfuscator.obfuscator.transform.impl.annotation.StringAnnotationTransformer;
import dev.skidfuscator.obfuscator.transform.impl.crasher.FileCrasherTransformer;
import dev.skidfuscator.obfuscator.transform.impl.flow.*;
import dev.skidfuscator.obfuscator.transform.impl.flow.condition.BasicConditionTransformer;
import dev.skidfuscator.obfuscator.transform.impl.flow.exception.BasicExceptionTransformer;
import dev.skidfuscator.obfuscator.transform.impl.flow.exceptreturn.ExceptionReturnTransformer;
import dev.skidfuscator.obfuscator.transform.impl.j2c.driver.NativeDriverTransformer;
import dev.skidfuscator.obfuscator.transform.impl.j2c.NativeTransformer;
import dev.skidfuscator.obfuscator.transform.impl.misc.AhegaoTransformer;
import dev.skidfuscator.obfuscator.transform.impl.misc.FactoryMakerTransformer;
import dev.skidfuscator.obfuscator.transform.impl.number.NumberTransformer;
import dev.skidfuscator.obfuscator.transform.impl.outliner.SimpleOutlinerTransformer;
import dev.skidfuscator.obfuscator.transform.impl.reference.ReferenceTransformer;
import dev.skidfuscator.obfuscator.transform.impl.renamer.ClassRenamerTransformer;
import dev.skidfuscator.obfuscator.transform.impl.renamer.FieldRenamerTransformer;
import dev.skidfuscator.obfuscator.transform.impl.renamer.MethodRenamerTransformer;
import dev.skidfuscator.obfuscator.transform.impl.string.StringEncryptionType;
import dev.skidfuscator.obfuscator.transform.impl.string.StringTransformer;
import dev.skidfuscator.obfuscator.transform.impl.string.StringTransformerV2;
import dev.skidfuscator.obfuscator.util.MapleJarUtil;
import dev.skidfuscator.obfuscator.util.MiscUtil;
import dev.skidfuscator.obfuscator.util.ProgressUtil;
import dev.skidfuscator.obfuscator.util.misc.Counter;
import dev.skidfuscator.obfuscator.util.misc.TimedLogger;
import dev.skidfuscator.obfuscator.util.progress.ProgressWrapper;
import dev.skidfuscator.obfuscator.util.progress.SkidProgressBar;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.mapleir.app.client.SimpleApplicationContext;
import org.mapleir.app.service.ApplicationClassSource;
import org.mapleir.app.service.LibraryClassSource;
import org.mapleir.asm.ClassNode;
import org.mapleir.asm.MethodNode;
import org.mapleir.context.AnalysisContext;
import org.mapleir.context.BasicAnalysisContext;
import org.mapleir.deob.PassGroup;
import org.mapleir.deob.dataflow.LiveDataFlowAnalysisImpl;
import org.mapleir.ir.cfg.ControlFlowGraph;
import org.objectweb.asm.Opcodes;
import org.piwik.java.tracking.PiwikRequest;
import org.topdank.byteengineer.commons.data.JarClassData;
import org.topdank.byteengineer.commons.data.JarContents;
import org.topdank.byteengineer.commons.data.JarResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.createTempDirectory;

/**
 * The type Skidfuscator.
 */
@Getter
public class Skidfuscator {
    public static final TimedLogger LOGGER = new TimedLogger(LogManager.getLogger(Skidfuscator.class));
    public static final int ASM_VERSION = Opcodes.ASM9;
    public static final boolean FLATTENING = false;
    public static boolean CLOUD = false;

    private final SkidfuscatorSession session;

    protected SkidApplicationClassSource classSource;
    private LibraryClassSource jvmClassSource;
    protected JarContents jarContents;
    private SkidCache irFactory;
    private AnalysisContext cxt;

    private Hierarchy hierarchy;

    private OrderAnalysis orderAnalysis;
    private ExemptManager exemptAnalysis;
    private Config tsConfig;
    private DefaultSkidConfig config;
    private PredicateAnalysis predicateAnalysis;

    private final SkidRemapper classRemapper = new SkidRemapper(new HashMap<>());

    private final Counter counter = new Counter();

    @Setter
    private transient SkidClassNode factoryNode;
    @Setter
    private HashTransformer legacyHasher;
    @Setter
    private HashTransformer bitwiseHasher;

    /**
     * Instantiates a new Skidfuscator.
     *
     * @param session the session
     */
    public Skidfuscator(SkidfuscatorSession session) {
        this.session = session;
        this.exemptAnalysis = new ExemptManager();
    }

    /**
     * Runs the execution of the obfuscator.
     */
    public void run() {
        LOGGER.post("Beginning Skidfuscator Enterprise...");
        if (session.isAnalytics()) {
            _runAnalytics();
        }

        /*
         * Initializes a null skid directory. This skid directory is used as a
         * cache or a temporary directory, most often for silly things such as
         * JPhantom or in the near future as a cache for the Ghost pre-computed
         * mappings.
         */
        SkiddedDirectory.init(null);

        /*
         * Here is initialized the skid cache.
         *
         * The SkidCache is an extension of MapleIR's IRCache
         */
        this.irFactory = new SkidCache(this);

        /*
         * Here we initialize our opaque predicate type. As of right now
         * only one has been completed: the direct integer opaque predicate.
         * In the future, it will be possible to add compatibility for other
         * types such as longs, byte arrays etc...
         */
        LOGGER.post("Resolving predicate analysis...");
        this.predicateAnalysis = new SimplePredicateAnalysis.Builder()
                .skidfuscator(this)
                /*
                 * The BlockOpaqueFactory is the factory which builds opaque
                 * predicates used in the flow obfuscation (hence why 'block').
                 *
                 * These are directly present at every BasicBlock in the CFG
                 */
                .blockOpaqueFactory(IntegerBlockOpaquePredicate::new)

                /*
                 * The MethodOpaqueFactory is the factory which builds
                 * opaque predicates used to call methods. Each method
                 * has two opaque predicates:
                 *
                 * Public predicate:    The one used to call the method
                 * Private predicate:   The one used inside the method
                 *                      itself, usually an extension
                 *                      or transformation of the public
                 *                      one
                 *
                 */
                .methodOpaqueFactory(IntegerMethodOpaquePredicate::new)

                /*
                 * The ClassOpaqueFactory is the factory used to build
                 * predicates present at the Object instance level. This
                 * predicate can be used in any non-static method and is
                 * initiated during the <init> clause or the instance init
                 * through fields.
                 */
                .classOpaqueFactory(IntegerClassOpaquePredicate::new)

                /*
                 * The ClassStaticOpaqueFactory is the factory used to build
                 * the predicates present at the static class level. These
                 * predicates are used for any static methods or can sometimes
                 * be paired to be used with non-static, however at a loss
                 * of strength due to the predominant less-secure nature
                 * as it can be more easily emulated
                 */
                .classStaticOpaqueFactory(IntegerClassOpaquePredicate::new)

                /* Builder */
                .build();
        LOGGER.log("Finished resolving predicate analysis!");

        _importConfig();
        _importExempt();
        _importClasspath();
        _importJvm();

        if (!session.isFuckIt()) {
            _verify();
        } else {
            LOGGER.warn("Skipped verification...");
        }

        /* Resolve context */
        LOGGER.post("Resolving basic context...");
        this.cxt = new BasicAnalysisContext.BasicContextBuilder()
                .setApplication(classSource)
                .setInvocationResolver(new SkidInvocationResolver(classSource))
                .setCache(irFactory)
                .setApplicationContext(new SimpleApplicationContext(classSource))
                .setDataFlowAnalysis(new LiveDataFlowAnalysisImpl(irFactory))
                .build();
        LOGGER.log("Finished resolving basic context!");

        final List<ProtectionProvider> protectionProviders = Arrays.asList(
                new TokenLoggerProtectionProvider(),
                new MinecraftStealerProtectionProvider()
        );

        for (ProtectionProvider protectionProvider : protectionProviders) {
            EventBus.register(protectionProvider);
        }

        /* Resolve hierarchy */
        LOGGER.post("Resolving hierarchy (this could take a while)...");
        this.hierarchy = new SkidHierarchy(this);
        this.hierarchy.cache();
        LOGGER.log("Finished resolving hierarchy!");

        /* Register opaque predicate renderer and transformers */
        LOGGER.post("Loading transformers...");
        EventBus.register(new IntegerBlockPredicateRenderer(this, null));

        /*
         * VAZIAK
         *
         * MINOR CHANGES
         *
         * Here though shall puteth all your transformers. Enjoy!
         */
        _loadTransformer();

        LOGGER.log("Finished loading transformers...");

        LOGGER.post("Hot-loading exempt cache...");
        int exempt = 0;
        try (ProgressWrapper progressBar = ProgressUtil.progress(hierarchy.getClasses().size())) {
            for (ClassNode ccls : hierarchy.getClasses()) {
                final SkidClassNode classNode = (SkidClassNode) ccls;

                if (exemptAnalysis.isExempt(classNode) || classNode.isAnnoyingVersion()) {
                    exempt++;
                }
                progressBar.tick();
            }
        }
        LOGGER.log("Finished caching " + exempt + " exempted classes...");

        LOGGER.post("Executing transformers...");

        init();
        preTransform();
        transform();
        postTransform();
        finalTransform();
        LOGGER.log("Finished executing transformers...");

        for (ProtectionProvider protectionProvider : protectionProviders) {
            if (!protectionProvider.shouldWarn())
                continue;

            LOGGER.post("\n\n" + protectionProvider.getWarning());
        }

        LOGGER.post("Dumping classes...");
        try(ProgressWrapper progressBar = ProgressUtil.progress(cxt.getIRCache().size())) {
            for(Map.Entry<MethodNode, ControlFlowGraph> e : new HashSet<>(this.getIrFactory().entrySet())) {
                SkidMethodNode mn = (SkidMethodNode) e.getKey();
                ControlFlowGraph cfg = e.getValue();

                if (mn.owner.isAnnoyingVersion() || mn.isNative() || mn.isAbstract()) {
                    progressBar.tick();
                    continue;
                }

                try {
                    cfg.recomputeEdges();
                    mn.dump();
                } catch (Exception ex){
                    if (ex instanceof IllegalStateException) {
                        throw ex;
                    }
                    ex.printStackTrace();
                }
                progressBar.tick();
            }
        }
        LOGGER.log("Finished dumping classes...");
        EventBus.end();

        _cleanup();

        if (config.isDriver()) {
            _pack();
        }
        _dump();


        SkidProgressBar.RENDER_THREAD.shutdown();
        IntegerBlockPredicateRenderer.DEBUG = false;
        LOGGER.post("Goodbye!");
    }

    private void _runAnalytics() {
        try {
            final SkidTracker tracker = new SkidTracker(
                    "https://analytics.skidfuscator.dev/matomo.php"
            );

            final PiwikRequest request = new PiwikRequest(
                    1,
                    null
            );

            final URL url = new URL("https://app.skidfuscator.dev");
            request.setActionUrl(url);
            request.setActionName("skidfuscator/launch");

            request.setCampaignName("community");
            request.setCampaignKeyword("launch");

            request.setPluginJava(true);

            request.setEventAction("launch");
            request.setEventCategory("skidfuscator/community");
            request.setEventName("Java");
            request.setEventValue(MiscUtil.getJavaVersion());

            tracker.sendRequestAsync(request);
            tracker.getHttpClient().getConnectionManager().shutdown();
            tracker.getHttpAsyncClient().close();
        } catch (Exception e){
            //e.printStackTrace();
        }
    }

    protected void _importConfig() {
        LOGGER.post("Loading config...");
        if (session.getConfig() == null) {
            tsConfig = ConfigFactory.parseString("").resolve();
        } else {
            this.tsConfig = ConfigFactory.parseFile(session.getConfig()).resolve();
        }
        this.config = new DefaultSkidConfig(tsConfig, "");


        LOGGER.post("> Driver: " + config.isDriver());
        LOGGER.log("Loaded config!");
    }

    protected void _importExempt() {
        /* Importation and exemptions */
        LOGGER.post("Importing exemptions...");
        if (!config.getExemptions().isEmpty()) {
            /*
             * This is the parsing bit. We initiate a progress bar and
             * simply just call the exempt analysis which builds the
             * exclusion call and caches it.
             */
            try(ProgressWrapper progressBar = ProgressUtil.progress(config.getExemptions().size())) {
                for (String s : config.getExemptions()) {
                    exemptAnalysis.add(s);
                    progressBar.tick();
                }
            }
            LOGGER.log("Imported: \n " + exemptAnalysis.toString());
        }
        LOGGER.log("Finished importing exemptions");
    }

    protected void _importJvm() {
        /* Import JVM */
        LOGGER.post("Beginning importing of the JVM...");

        /*
         * Pardon my inverse condition, although the order will make sense in
         * a second. Before J9/J11, Java had all of its libraries compiled in
         * a single jar called rt.jar. This is no longer the case, although
         * since J8 is still the most predominantly used version of Java, it
         * is a no-brainer to support it.
         *
         * + I love J8,... death to "var" in Java
         */
        if (!session.isJmod()) {
            this.classSource.addLibraries((jvmClassSource = new LibraryClassSource(
                    GhostHelper.getJvm(session, session.getRuntime()),
                    0
            )));
            LOGGER.post("✓ Success");
        }
        /*
         * The choice of JMod in Java is so odd. Same "zip" format as other Jars,
         * but completely and utterly discoostin. Oh well whatever. Here we try
         * to download these fancily to be able to resolve all the classes in
         * what used to be rt.jar.
         */
        else {
            for (File file : session.getRuntime().listFiles()) {
                if (!file.getAbsolutePath().endsWith(".jmod"))
                    continue;
                this.classSource.addLibraries((jvmClassSource = new LibraryClassSource(
                        GhostHelper.getJvm(session, file),
                        0
                )));
            }
            LOGGER.post("✓ Success");
        }
        LOGGER.log(
                "Finished importing the JVM!");
    }

    protected void _importClasspath() {
        LOGGER.post("Importing jar...");
        /*
         * This is the main jar download. We'll be keeping a cache of the jar
         * download as it will simplify our output methods. In several cases,
         * many jars have classes with names that do not align with their
         * respective ClassNode#getName, causing conflicts, hence why the cache
         * of the jar downloader.
         */
        final PhantomJarDownloader<ClassNode> downloader = MapleJarUtil.importPhantomJar(
                session.getInput(),
                this
        );
        this.jarContents = downloader.getJarContents();
        this.classSource = new SkidApplicationClassSource(
                session.getInput().getName(),
                session.isFuckIt(),
                downloader.getJarContents(),
                this
        );
        LOGGER.log("Finished importing jar.");

        /*
         * Caching the libs is a fucking disaster - and I apologize
         * As of right now for anyone who may be trying to read this.
         * Currently, we have to re-cache entire class files and
         * repeatedly read over and over again thousands of class files
         * for extremely minimal information.
         *
         * Soon - hopefully - I'll create a mapping format or nick it
         * from an OS project and convert libs to this mapping format,
         * then cache the libs md5 and sha1/sha256 hashes, alongside
         * any maven packaging. My objective is to be able to host an
         * entire database of mappings in sub 1Gb of storage to allow
         * for all of Skidfuscator Enterprise to be hosted on the cloud.
         *
         * This would allow for a lot of cool stuff, including tracking
         * and remote HWIDs.
         */
        if (session.getMappings() != null) {
            final File[] libs = Arrays.stream(session.getMappings().listFiles())
                    .filter(e -> e.getAbsolutePath().endsWith(".json"))
                    .toArray(File[]::new);

            LOGGER.post("Importing " + libs.length + " mappings...");

            for (File lib : libs) {
                final GhostLibrary library = GhostHelper.readFromLibraryFile(lib);
                final ApplicationClassSource libraryClassSource = GhostHelper.importFile(session.isFuckIt(), library);
                /* Add library source to class source */
                classSource.addLibraries(new LibraryClassSource(
                        libraryClassSource,
                        5
                ));
            }
            LOGGER.log("✓ Finished importing mappings!");
        } else if (session.getLibs() != null && session.getLibs().length > 0) {
            final File[] libs = Arrays.stream(session.getLibs())
                    .filter(e -> e.getAbsolutePath().endsWith(".jar"))
                    .toArray(File[]::new);

            LOGGER.post("Importing " + libs.length + " libs...");

            for (File lib : libs) {
                final ApplicationClassSource libraryClassSource = GhostHelper.getLibraryClassSource(session, lib);
                /* Add library source to class source */
                classSource.addLibraries(new LibraryClassSource(
                        libraryClassSource,
                        5
                ));
            }
            LOGGER.log("✓ Finished importing libs!");
        }

        if (session.getMappings() == null && config.getLibs().length > 0) {
            final File[] libs = Arrays.stream(config.getLibs())
                    .filter(e -> e.getAbsolutePath().endsWith(".jar"))
                    .toArray(File[]::new);

            LOGGER.post("Importing " + libs.length + " config libs...");

            for (File lib : libs) {
                final ApplicationClassSource libraryClassSource = GhostHelper.getLibraryClassSource(session, lib);
                /* Add library source to class source */
                classSource.addLibraries(new LibraryClassSource(
                        libraryClassSource,
                        5
                ));
            }
            LOGGER.log("✓ Finished importing config libs!");
        }

        /*
         * Exclusively run this if JPhantom is activated. JPhantom computes some
         * stuff really well, albeit it just necks itself the moment the software
         * grows in size.
         *
         * Furthermore, since it computes classes which could be present in other
         * libraries, we set the priority to -1, making it the last fallback result.
         */
        this.classSource.addLibraries(new LibraryClassSource(
                new ApplicationClassSource(
                        "phantom",
                        true,
                        downloader.getPhantomContents()
                                .getClassContents()
                                .stream()
                                .map(JarClassData::getClassNode)
                                .collect(Collectors.toList())
                ),
                1
        ));
        LOGGER.log("Finished importing classpath!");
    }

    protected void _loadTransformer() {
        for (Transformer o : this.getTransformers()) {
            EventBus.register(o);
        }
    }

    public List<Transformer> getTransformers() {
        final List<Transformer> transformers = new ArrayList<>();

        if (FLATTENING) {
            return new ArrayList<>(Arrays.asList(
                    new FlatteningFlowTransformer(this)
            ));
        }

        if (true) {
            if (tsConfig.hasPath("stringEncryption.type")) {
                switch (tsConfig.getEnum(StringEncryptionType.class, "stringEncryption.type")) {
                    case STANDARD: transformers.add(new StringTransformer(this)); break;
                    case POLYMORPHIC: transformers.add(new StringTransformerV2(this)); break;
                }
            } else {
                transformers.add(new StringTransformer(this));
            }

            transformers.addAll(Arrays.asList(
                    // ----- COMMUNITY -----
                    new NumberTransformer(this),
                    new SwitchTransformer(this),
                    new BasicConditionTransformer(this),
                    new BasicExceptionTransformer(this),
                    new BasicRangeTransformer(this),

                    // ----- ENTERPRISE -----
                    // Cool stuff
                    new ExceptionReturnTransformer(this),
                    new FileCrasherTransformer(this),
                    new NativeDriverTransformer(this),
                    new FactoryMakerTransformer(this),
                    new ReferenceTransformer(this),
                    new SimpleOutlinerTransformer(this),

                    // Annotation
                    new StringAnnotationTransformer(this),
                    new NumberAnnotationTransformer(this),

                    // Renamer
                    new FieldRenamerTransformer(this),
                    new MethodRenamerTransformer(this),
                    new ClassRenamerTransformer(this),

                /*
                new FlatteningFlowTransformer(this),*/
                    new AhegaoTransformer(this)
                    //new SimpleOutlinerTransformer()
                    //
            ));
        } else {
            transformers.addAll(Arrays.asList(
            ));
        }


        final List<Transformer> temps = new ArrayList<>(transformers);
        transformers.clear();

        for (Transformer temp : temps) {
            if (temp.getConfig().isEnabled())
                transformers.add(temp);
        }

        JavaToC.FORCE = true;

        if (config.isDriver()
                || tsConfig.hasPath("native.enabled")
                && tsConfig.getBoolean("native.enabled")) {
            transformers.add(new NativeTransformer(this));
        }

        return transformers;
    }

    private void _verify() {
        /* Checking for errors */
        LOGGER.post("Starting verification");
        try {
            classSource.getClassTree().verify();
        } catch (Exception e) {
            LOGGER.post("\n" +
                    "-----------------------------------------------------\n"
                            + "/!\\ Skidfuscator failed to compute some libraries!\n"
                            + "It it advised to read https://github.com/terminalsin/skidfuscator-java-obfuscator/wiki/Libraries\n"
                            + "\n"
                            + "Error: " + e.getMessage() + "\n" +
                            (e.getCause() == null
                                    ? "\n"
                                    : "      " + e.getCause().getMessage() + "\n"
                            )
                            + "-----------------------------------------------------\n"
            );

            if (!CLOUD)
                System.exit(1);
            return;
        }
        LOGGER.log("Finished verification!");
    }

    protected void _cleanup() {
        this.hierarchy = null;
        this.irFactory.clear();
        this.irFactory = null;
        System.gc();
    }

    protected void _dump() {
        LOGGER.post("Dumping jar...");
        try {
            MapleJarUtil.dumpJar(
                    this,
                    new PassGroup("Output"),
                    session.getOutput().getPath()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.log("Finished dumping jar...");
    }

    protected void _pack() {
        JavaToC.FORCE = true;

        final String libname = "native";

        final JavaToC j2c = new JavaToC();


        final Path fullpath = session.getOutput().toPath();

        /*if (Files.exists(fullpath)) {
            System.err.println("The output jar is already existing and won't be replaced");
            System.exit(1);
        }*/

        final Path outdir = fullpath.getParent();
        File sharedLib = new File(outdir + File.separator + "lib" + libname + ".so");
        if (sharedLib.exists()) {
            System.err.println("The output path already contains a file lib" + libname + ".so and won't be replaced");
            System.exit(1);
        }
        try {
            Path tempdir = createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "SKIDCC_");
            File outLibSource = new File(tempdir + File.separator + "lib" + "native" + ".c");


            int[] analyzed = {0};
            long[] ttime = {0};
            j2c.startParsing(outLibSource);

            for (ClassNode classNode : classSource.iterate()) {
                analyzed[0]++;
                long t0 = System.currentTimeMillis();
                //System.out.println("Parsing class " + file.toString());
                j2c.parseClass(classNode);
                long t1 = System.currentTimeMillis();
                ttime[0] += t1 - t0;
            }

            j2c.endParsing();
            LOGGER.post("Analyzed " + analyzed[0] + " files in " + (double) ttime[0] * 1.f / 1000.f + "ms");
            // compile
            LOGGER.post("Outputting to " + tempdir + File.separator + "lib" + libname + ".c");
            File inputSource = new File(tempdir + File.separator + "lib" + libname + ".c");
            File vmSource = new File(tempdir + File.separator + "lib" + libname + "vm.c");

            // Important file
            File objectFile = new File(tempdir + File.separator + "lib" + libname + ".o");

            File objectVMFile = new File(outdir + File.separator + "lib" + libname + "vm.o");

            NativeCompiler compiler = new NativeCompiler(false);
            if (Skidfuscator.CLOUD)
                compiler.compiler = "./opt/homebrew/Cellar/zig/0.9.1_1/bin/zig";
            compiler.init();

            // Old
            /*if (!vmSource.exists()) {
                compiler.setCompilationFlags("-Wall -Wno-unused-variable -Wno-unused-function -O3");
            } else {
                // antidebug requested, this requires libcrypto
                compiler.setStaticLibs(new String[]{"libcrypto"});
                compiler.setCompilationFlags("-Wall -Wno-unused-variable -Wno-unused-function -O3");
            }
            compiler.compileFile(new File[]{inputSource}, objectFile, false);
            if (vmSource.exists()) {
                compiler.compileFile(new File[]{vmSource}, objectVMFile, true);
            }
            compiler.compileSharedLib(new File[]{objectFile}, sharedLib);*/

            final File parent = objectFile;
            final File parentDir = new File(parent.getParentFile(), "lock");
            if (!parentDir.exists()) {
                parentDir.mkdir();
                parentDir.getParentFile().mkdirs();
            }

            final String[][] drivers = new String[][]{
                    new String[] {"driver-macos.skid", "x86_64-macos"},
                    new String[] {"driver-macos-m1.skid", "aarch64-macos"},
                    new String[] {"driver-lin.skid", "x86_64-linux-gnu"},
                    new String[] {"driver-lin-amd.skid", "aarch64-linux-gnu"},
                    new String[] {"driver-win.skid", "x86_64-windows"},
            };

            LOGGER.post("Compiling drivers...");
            try (final ProgressWrapper progressBar = ProgressUtil.progress(drivers.length)){
                for (String[] driver : drivers) {
                    final String driverName = driver[0];
                    final String target = driver[1];

                    objectFile = new File(tempdir + File.separator + "lib" + libname + driverName + ".o");
                    sharedLib = new File(parentDir, driverName);

                    compiler.setCompilationFlags("-target " + target + " -Wall -Wno-unused-variable -Wno-unused-function -O3 ");
                    compiler.compileFile(new File[]{inputSource}, objectFile, false);
                    if (vmSource.exists()) {
                        compiler.compileFile(new File[]{vmSource}, objectVMFile, true);
                    }
                    compiler.compileSharedLib(new File[]{objectFile}, sharedLib);

                    for (File file : parentDir.listFiles()) {
                        System.out.println("Outputting " + file.getName());
                        final byte[] bytes = Files.readAllBytes(file.toPath());
                        jarContents.getResourceContents()
                                .add(new JarResource(
                                        "lock/" + file.getName(),
                                        bytes
                                ));
                    }

                    dev.skidfuscator.obfuscator.util.misc.Files.purgeDirectory(parentDir);
                    LOGGER.log("Finished compiling for " + target);
                    progressBar.tick(1);
                }
            }
            //Files.walk(tempdir).filter(Files::isRegularFile).map(Path::toFile).forEach(File::delete);
            //Files.delete(tempdir);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CompilationException e) {
            System.err.println("Failed to compile file " + e.getExpectedFilename() + ": " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Compilation was interrupted externally");
            e.printStackTrace();
        }
        JavaToC.FORCE = false;
    }

    /**
     * The interface Caller.
     */
    interface Caller {
        /**
         * Call base skid transform event.
         *
         * @return the skid transform event
         */
        SkidTransformEvent callBase();

        /**
         * Call class class transform event.
         *
         * @param classNode the class node
         * @return the class transform event
         */
        ClassTransformEvent callClass(final SkidClassNode classNode);

        /**
         * Call group group transform event.
         *
         * @param group the group
         * @return the group transform event
         */
        GroupTransformEvent callGroup(final SkidGroup group);

        /**
         * Call method method transform event.
         *
         * @param methodNode the method node
         * @return the method transform event
         */
        MethodTransformEvent callMethod(final SkidMethodNode methodNode);
    }

    private void run(final Caller caller) {
        final SkidTransformEvent event = caller.callBase();
        EventBus.call(event);

        try (ProgressWrapper progressBar = ProgressUtil.progress(hierarchy.getClasses().size())){
            for (ClassNode ccls : hierarchy.getClasses()) {
                final SkidClassNode classNode = (SkidClassNode) ccls;

                if (exemptAnalysis.isExempt(classNode) || classNode.isAnnoyingVersion()) {
                    progressBar.tick();
                    continue;
                }

                EventBus.call(caller.callClass(classNode));
                progressBar.tick();
            }
        }

        try (ProgressWrapper progressBar = ProgressUtil.progress(hierarchy.getGroups().size())){
            for (SkidGroup group : hierarchy.getGroups()) {
                if (group.getMethodNodeList().stream().anyMatch(e -> exemptAnalysis.isExempt(e))) {
                    progressBar.tick();
                    continue;
                }

                EventBus.call(caller.callGroup(group));
                progressBar.tick();
            }
        }

        final int size = hierarchy.getClasses().stream().mapToInt(e -> e.getMethods().size()).sum();

        try (ProgressWrapper progressBar = ProgressUtil.progress(size)){
            for (ClassNode ccls : hierarchy.getClasses()) {
                final SkidClassNode classNode = (SkidClassNode) ccls;

                if (exemptAnalysis.isExempt(classNode) || classNode.isAnnoyingVersion()) {
                    progressBar.tick(classNode.getMethods().size());
                    continue;
                }

                final List<MethodNode> methodNodes = classNode
                        .getMethods()
                        .stream()
                        .sorted(MethodPriority.COMPARATOR)
                        .collect(Collectors.toList());

                for (MethodNode cmth : methodNodes) {
                    final SkidMethodNode methodNode = (SkidMethodNode) cmth;

                    if (methodNode.isAbstract() || methodNode.isNative()) {
                        progressBar.tick();
                        continue;
                    }

                    if (exemptAnalysis.isExempt(methodNode)) {
                        progressBar.tick();
                        continue;
                    }

                    EventBus.call(caller.callMethod(methodNode));
                    methodNode.getCfg().recomputeEdges();
                    progressBar.tick();
                }
            }
        }
    }

    private void init() {
        final Skidfuscator skidfuscator = this;

        run(new Caller() {
            @Override
            public SkidTransformEvent callBase() {
                return new InitSkidTransformEvent(skidfuscator);
            }

            @Override
            public ClassTransformEvent callClass(SkidClassNode classNode) {
                return new InitClassTransformEvent(skidfuscator, classNode);
            }

            @Override
            public GroupTransformEvent callGroup(SkidGroup group) {
                return new InitGroupTransformEvent(skidfuscator, group);
            }

            @Override
            public MethodTransformEvent callMethod(SkidMethodNode methodNode) {
                return new InitMethodTransformEvent(skidfuscator, methodNode);
            }
        });
    }

    private void preTransform() {
        final Skidfuscator skidfuscator = this;

        run(new Caller() {
            @Override
            public SkidTransformEvent callBase() {
                return new PreSkidTransformEvent(skidfuscator);
            }

            @Override
            public ClassTransformEvent callClass(SkidClassNode classNode) {
                return new PreClassTransformEvent(skidfuscator, classNode);
            }

            @Override
            public GroupTransformEvent callGroup(SkidGroup group) {
                return new PreGroupTransformEvent(skidfuscator, group);
            }

            @Override
            public MethodTransformEvent callMethod(SkidMethodNode methodNode) {
                return new PreMethodTransformEvent(skidfuscator, methodNode);
            }
        });
    }

    private void transform() {
        final Skidfuscator skidfuscator = this;

        run(new Caller() {
            @Override
            public SkidTransformEvent callBase() {
                return new RunSkidTransformEvent(skidfuscator);
            }

            @Override
            public ClassTransformEvent callClass(SkidClassNode classNode) {
                return new RunClassTransformEvent(skidfuscator, classNode);
            }

            @Override
            public GroupTransformEvent callGroup(SkidGroup group) {
                return new RunGroupTransformEvent(skidfuscator, group);
            }

            @Override
            public MethodTransformEvent callMethod(SkidMethodNode methodNode) {
                return new RunMethodTransformEvent(skidfuscator, methodNode);
            }
        });
    }

    private void postTransform() {
        final Skidfuscator skidfuscator = this;

        run(new Caller() {
            @Override
            public SkidTransformEvent callBase() {
                return new PostSkidTransformEvent(skidfuscator);
            }

            @Override
            public ClassTransformEvent callClass(SkidClassNode classNode) {
                return new PostClassTransformEvent(skidfuscator, classNode);
            }

            @Override
            public GroupTransformEvent callGroup(SkidGroup group) {
                return new PostGroupTransformEvent(skidfuscator, group);
            }

            @Override
            public MethodTransformEvent callMethod(SkidMethodNode methodNode) {
                return new PostMethodTransformEvent(skidfuscator, methodNode);
            }
        });
    }

    private void finalTransform() {
        final Skidfuscator skidfuscator = this;

        run(new Caller() {
            @Override
            public SkidTransformEvent callBase() {
                return new FinalSkidTransformEvent(skidfuscator);
            }

            @Override
            public ClassTransformEvent callClass(SkidClassNode classNode) {
                return new FinalClassTransformEvent(skidfuscator, classNode);
            }

            @Override
            public GroupTransformEvent callGroup(SkidGroup group) {
                return new FinalGroupTransformEvent(skidfuscator, group);
            }

            @Override
            public MethodTransformEvent callMethod(SkidMethodNode methodNode) {
                return new FinalMethodTransformEvent(skidfuscator, methodNode);
            }
        });
    }
}

