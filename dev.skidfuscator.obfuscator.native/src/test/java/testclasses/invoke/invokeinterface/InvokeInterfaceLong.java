package testclasses.invoke.invokeinterface;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeInterfaceLong {
    public InvokeInterfaceLong() {

    }

    @NativeObfuscation
    public long add(AdderInterface interf, long a, long b) {
        return interf.add(a, b);
    }
}