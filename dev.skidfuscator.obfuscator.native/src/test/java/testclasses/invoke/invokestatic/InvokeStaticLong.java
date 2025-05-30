package testclasses.invoke.invokestatic;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeStaticLong {
    public InvokeStaticLong() {

    }

    public static long normalAdd(long a, long b) {
        return a + b;
    }

    @NativeObfuscation
    public long add(long a, long b) {
        return normalAdd(a, b);
    }
}