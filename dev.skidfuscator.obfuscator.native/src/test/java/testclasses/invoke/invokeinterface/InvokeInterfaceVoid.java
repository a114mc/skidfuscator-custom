package testclasses.invoke.invokeinterface;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeInterfaceVoid {
    public InvokeInterfaceVoid() {

    }

    @NativeObfuscation
    public void print(AdderInterface interf, int a, int b) {
        interf.print(a, b);
    }
}