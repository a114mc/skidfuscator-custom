package testclasses.invoke.invokestatic;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeStaticBoolean {
    public InvokeStaticBoolean() {

    }

    public static boolean normalAnd(boolean a, boolean b) {
        return a && b;
    }

    @NativeObfuscation
    public boolean and(boolean a, boolean b) {
        return normalAnd(a, b);
    }
}