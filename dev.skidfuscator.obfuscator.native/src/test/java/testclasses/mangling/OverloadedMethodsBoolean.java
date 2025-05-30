package testclasses.mangling;

import dev.skidfuscator.annotations.NativeObfuscation;

public class OverloadedMethodsBoolean {
    public OverloadedMethodsBoolean() {

    }

    @NativeObfuscation
    public static int add(int a, double b) {
        return a + (int) b;
    }

    @NativeObfuscation
    public static boolean add(boolean a, boolean b) {
        return a && b;
    }
}
