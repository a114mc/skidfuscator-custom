package testclasses.exceptions.invoke.invokestatic;

import dev.skidfuscator.annotations.NativeObfuscation;

public class ThrowInvokeStaticLong {
    public ThrowInvokeStaticLong() {

    }

    public static long normalDiv(int a, int b) {
        return a / b;
    }

    @NativeObfuscation
    public int div(int a) {
        return (int) normalDiv(a, 0);
    }
}
