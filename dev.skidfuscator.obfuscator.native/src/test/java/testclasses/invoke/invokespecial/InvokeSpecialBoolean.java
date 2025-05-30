package testclasses.invoke.invokespecial;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeSpecialBoolean extends InvokeVirtualBoolean {
    public InvokeSpecialBoolean() {

    }

    @NativeObfuscation
    @Override
    public boolean and(boolean a, boolean b) {
        return super.normalAnd(a, b);
    }

    //wrong method, I want the one of the superclass to be called -> invokespecial
    public boolean normalAnd(boolean a, boolean b) {
        return false;
    }
}