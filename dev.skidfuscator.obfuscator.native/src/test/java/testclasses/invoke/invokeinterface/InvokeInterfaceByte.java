package testclasses.invoke.invokeinterface;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeInterfaceByte {
    public InvokeInterfaceByte() {

    }

    @NativeObfuscation
    public byte add(AdderInterface interf, byte a, byte b) {
        return interf.add(a, b);
    }
}