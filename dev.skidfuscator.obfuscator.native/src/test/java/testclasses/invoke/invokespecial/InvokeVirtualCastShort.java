package testclasses.invoke.invokespecial;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeVirtualCastShort {
    public InvokeVirtualCastShort() {

    }

    @NativeObfuscation
    public boolean add(short a, short b) {
        return normalAdd(a, b) == (short) (a + b);
    }

    public short normalAdd(short a, short b) {
        return (short) (a + b);
    }
}