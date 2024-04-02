package dev.skidfuscator.obfuscator.command;

import com.google.common.io.Files;
import dev.skidfuscator.migration.ExemptToConfigMigration;
import dev.skidfuscator.obfuscator.Skidfuscator;
import dev.skidfuscator.obfuscator.SkidfuscatorSession;
import dev.skidfuscator.obfuscator.util.ConsoleColors;
import dev.skidfuscator.obfuscator.util.IOUtil;
import dev.skidfuscator.obfuscator.util.MiscUtil;
import io.cryptolens.methods.Helpers;
import io.cryptolens.methods.Key;
import io.cryptolens.models.ActivateModel;
import io.cryptolens.models.LicenseKey;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * @author Ghast
 * @since 06/03/2021
 * SkidfuscatorV2 © 2021
 */

@CommandLine.Command(
        aliases = "obfuscate",
        mixinStandardHelpOptions = true,
        version = "obfuscate 1.0.0",
    description = "Obfuscates and runs a specific jar"
)
public class ObfuscateCommand implements Callable<Integer> {
    @CommandLine.Parameters(
            index = "0",
            description = "The file which will be obfuscated."
    )
    private File input;

    @CommandLine.Option(
            names = {"-rt", "--runtime"},
            description = "Path to the runtime jar"
    )
    private File runtime;

    @CommandLine.Option(
            names = {"-li", "--libs"},
            description = "Path to the libs folder"
    )
    private File libFolder;

    @CommandLine.Option(
            names = {"-ex", "--exempt"},
            description = "Path to the exempt file"
    )
    private File exempt;

    @CommandLine.Option(
            names = {"-o", "--output"},
            description = "Path to the output jar location"
    )
    private File output;

    @CommandLine.Option(
            names = {"-cfg", "--config"},
            description = "Path to the config file"
    )
    private File config;

    @CommandLine.Option(
            names = {"-ph", "--phantom"},
            description = "Declare if phantom computation should be used"
    )
    private boolean phantom;

    @CommandLine.Option(
            names = {"-fuckit", "--fuckit"},
            description = "Do not use!"
    )
    private boolean fuckit;

    @CommandLine.Option(
            names = {"-notrack", "--notrack"},
            description = "If you do not wish to be part of analytics!"
    )
    private boolean notrack;

    @CommandLine.Option(
            names = {"-re", "--renamer"},
            description = "Enables renamer for the obfuscation"
    )
    private boolean renamer;

    @Override
    public Integer call()  {
        String RSAPubKey = "<RSAKeyValue><Modulus>1Z3E9GI6pGwOinZbndE0Amj4ukOz5jZ/dY6C6mtq7KgMwSsVLAgWoR4W+nFz9lFSBT88JXERfVWtGw38V2bSe3hc5fOnH/eFNNldfjgwMl3RtEDLoZTQenGu8aSJ95JGIwr7d2UmqV0r7oTRezi5u9fCvYXFEuHEvFYz2cJ5uyGbwqQW95+3aZ7bwbF9bFKPuSKajoLSDAMffZ3OdXEyvom+hF5xSgg1A3Px5MtyxiJ2PIx3AIOdB6U181I6faPS+eK5dAwOUEVJNRFO0vAegzsqY8+JMcPglzWWqhtz/f9nTw99SaT0JW9M1WC1yBkubB5xZiBRhqECExLNU0/e7w==</Modulus><Exponent>AQAB</Exponent></RSAKeyValue>";
        String auth = "WyI0MDMxNDY0NyIsIkJRVlp1ZFI3akhvcmJONTdiMmx4ZFNJTDVCVkNGSHJPcCtxSjlqV3ciXQ==";

        final File licenseFile = new File("LICENSE.skid");

        if (!licenseFile.exists()) {
            // Enter data using BufferReader

            // Reading data using readLine
            final String id;
            try {
                Skidfuscator.LOGGER.post("Please input your license");
                final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("> ");
                id = reader.readLine();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }

            final LicenseKey license = Key.Activate(auth, RSAPubKey,
                    new ActivateModel(
                            19147,  // <--  remember to change this to your Product Id
                            id, // <--  remember to change this to your license key
                            Helpers.GetMachineCode(2)
                    )
            );

            if (license == null || !Helpers.IsOnRightMachine(license, 2)) {
                System.out.println("The license does not work.");
                return 0;
            } else {
                System.out.println("The license is valid!\n" + String.format(
                        " Name: %s\n Company: %s\n Address: %s\n",
                        license.Customer.Name, license.Customer.CompanyName, license.Customer.Email
                ));
                System.out.println("Updates expire: " + license.Expires);
            }

            try {
                Files.write(license.SaveAsString().getBytes(StandardCharsets.UTF_8), licenseFile);
            } catch (IOException e) {
                Skidfuscator.LOGGER.error("Failed to save license...", e);
                return 0;
            }
        } else {
            final String licenseString;

            try {
                licenseString = String.join("", Files.readLines(licenseFile, StandardCharsets.UTF_8));
            } catch (IOException e) {
                Skidfuscator.LOGGER.error("Failed to save license...", e);
                return 0;
            }
            final LicenseKey license = LicenseKey.LoadFromString(RSAPubKey, licenseString, 1);

            if (license == null || !Helpers.IsOnRightMachine(license, 2)) {
                System.out.println("The license does not work.");
                return 0;
            } else {
                System.out.println("The license is valid!\n" + String.format(
                        " Name: %s\n Company: %s\n Address: %s\n",
                        license.Customer.Name, license.Customer.CompanyName, license.Customer.Email
                ));
            }
        }




        /* Total number of processors or cores available to the JVM */
        final String processors =
                String.format("%19.19s", "Processors:")
                        + "   "
                        + String.format(
                                 "%-19.19s",
                            Runtime.getRuntime().availableProcessors() + " cores"
                );

        final long freeMemory = Math.round(Runtime.getRuntime().freeMemory() / 1E6);
        final String memory =
                String.format("%19.19s", "Current Memory:")
                        + "   "
                        + String.format("%-19.19s", freeMemory + "mb");

        final long maxMemory = Math.round(Runtime.getRuntime().maxMemory() / 1E6);
        final String memoryString = (maxMemory == Long.MAX_VALUE
                ? ConsoleColors.GREEN + "no limit"
                : maxMemory + "mb"
        );
        String topMemory =
                String.format("%19.19s", "Max Memory:")
                        + "   "
                        + String.format("%-19.19s",
                            memoryString + (maxMemory > 1500 ? "" : " ⚠️")
                        );

        topMemory = MiscUtil.replaceColor(
                topMemory,
                memoryString,
                maxMemory > 1500 ? ConsoleColors.GREEN_BRIGHT : ConsoleColors.RED_BRIGHT
        );
        // slight fix for thing
        topMemory = topMemory.replace("⚠️", "⚠️ ");

        final String[] logo = new String[] {
                "",
                "  /$$$$$$  /$$       /$$       /$$  /$$$$$$                                           /$$",
                " /$$__  $$| $$      |__/      | $$ /$$__  $$                                         | $$",
                "| $$  \\__/| $$   /$$ /$$  /$$$$$$$| $$  \\__//$$   /$$  /$$$$$$$  /$$$$$$$  /$$$$$$  /$$$$$$    /$$$$$$   /$$$$$$",
                "|  $$$$$$ | $$  /$$/| $$ /$$__  $$| $$$$   | $$  | $$ /$$_____/ /$$_____/ |____  $$|_  $$_/   /$$__  $$ /$$__  $$",
                " \\____  $$| $$$$$$/ | $$| $$  | $$| $$_/   | $$  | $$|  $$$$$$ | $$        /$$$$$$$  | $$    | $$  \\ $$| $$  \\__/",
                " /$$  \\ $$| $$_  $$ | $$| $$  | $$| $$     | $$  | $$ \\____  $$| $$       /$$__  $$  | $$ /$$| $$  | $$| $$",
                "|  $$$$$$/| $$ \\  $$| $$|  $$$$$$$| $$     |  $$$$$$/ /$$$$$$$/|  $$$$$$$|  $$$$$$$  |  $$$$/|  $$$$$$/| $$",
                " \\______/ |__/  \\__/|__/ \\_______/|__/      \\______/ |_______/  \\_______/ \\_______/   \\___/   \\______/ |__/",
                "",
                "                               ┌───────────────────────────────────────────┐",
                "                               │ "             + processors +            " │",
                "                               │ "               + memory +              " │",
                "                               │ "              + topMemory +            " │",
                "                               └───────────────────────────────────────────┘",
                "",
                "                      Author: Ghast     Version: 2.0.6     Today: "
                        + DateFormat.getDateTimeInstance().format(new Date(Instant.now().toEpochMilli())),
                ""
        };

        for (String s : logo) {
            System.out.println(s);
        }

        if (input == null) {
            return -1;
        }

        if (output == null) {
            output = new File(input.getPath() + "-out.jar");
        }

        if (runtime == null) {
            final String home = System.getProperty("java.home");
            runtime = new File(
                    home,
                    MiscUtil.getJavaVersion() > 8
                            ? "jmods"
                            : "lib/rt.jar"
            );
        }

        if (exempt != null) {
            final File converted = new File(exempt.getAbsolutePath()
                    .substring(0, exempt.getAbsolutePath().lastIndexOf("/")) + "/config.hocon");
            final String warning = "\n" + ConsoleColors.YELLOW
                    + "██╗    ██╗ █████╗ ██████╗ ███╗   ██╗██╗███╗   ██╗ ██████╗ \n"
                    + "██║    ██║██╔══██╗██╔══██╗████╗  ██║██║████╗  ██║██╔════╝ \n"
                    + "██║ █╗ ██║███████║██████╔╝██╔██╗ ██║██║██╔██╗ ██║██║  ███╗\n"
                    + "██║███╗██║██╔══██║██╔══██╗██║╚██╗██║██║██║╚██╗██║██║   ██║\n"
                    + "╚███╔███╔╝██║  ██║██║  ██║██║ ╚████║██║██║ ╚████║╚██████╔╝\n"
                    + " ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝╚═╝  ╚═══╝ ╚═════╝ \n"
                    + "\n"
                    + "⚠️  Warning! Skidfuscator has deprecated the exempt file!\n"
                    + ConsoleColors.RESET
                    + "\n  Launching migrator service..."
                    + "\n  Config will be found at " + converted
                    + "\n";
            Skidfuscator.LOGGER.post(warning);
            new ExemptToConfigMigration().migrate(exempt, converted);

            config = converted;
        }

        final File[] libs;
        if (libFolder != null) {
            libs = libFolder.listFiles();
        } else {
            libs = new File[0];
        }

        final SkidfuscatorSession skidInstance = SkidfuscatorSession.builder()
                .input(input)
                .output(output)
                .libs(libs)
                .runtime(runtime)
                .exempt(exempt)
                .phantom(phantom)
                .jmod(MiscUtil.getJavaVersion() > 8)
                .fuckit(fuckit)
                .config(config)
                .renamer(renamer)
                .analytics(!notrack)
                .build();

        final Skidfuscator skidfuscator = new Skidfuscator(skidInstance);
        skidfuscator.run();

        return 0;
    }


}