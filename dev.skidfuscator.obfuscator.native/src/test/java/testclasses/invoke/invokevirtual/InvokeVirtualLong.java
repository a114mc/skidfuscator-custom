package testclasses.invoke.invokevirtual;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeVirtualLong {
    public InvokeVirtualLong() {

    }

    @NativeObfuscation
    public long add(long a, long b) {
        return normalAdd(a, b);
    }

    public long normalAdd(long a, long b) {
        return a + b;
    }
}