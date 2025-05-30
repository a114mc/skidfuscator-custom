package testclasses.mangling;

import dev.skidfuscator.annotations.NativeObfuscation;

public class OverloadedMethodsLong {
    public OverloadedMethodsLong() {

    }

    @NativeObfuscation
    public static int add(int a, double b) {
        return a + (int) b;
    }

    @NativeObfuscation
    public static long add(long a, int b) {
        return a + b;
    }
}
