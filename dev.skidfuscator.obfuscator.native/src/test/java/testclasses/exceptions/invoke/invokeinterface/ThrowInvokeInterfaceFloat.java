package testclasses.exceptions.invoke.invokeinterface;

import dev.skidfuscator.annotations.NativeObfuscation;

public class ThrowInvokeInterfaceFloat {
    public ThrowInvokeInterfaceFloat() {

    }

    @NativeObfuscation
    public float div(DivisionInterface interf, float a) {
        return interf.div(a);
    }
}
