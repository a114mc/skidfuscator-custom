package testclasses.invoke.invokespecial;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeSpecialShort extends InvokeVirtualShort {
    public InvokeSpecialShort() {

    }

    @NativeObfuscation
    @Override
    public short add(short a, short b) {
        return super.normalAdd(a, b);
    }

    //wrong method, I want the one of the superclass to be called -> invokespecial
    public short normalAdd(short a, short b) {
        return (short) (a - b);
    }
}