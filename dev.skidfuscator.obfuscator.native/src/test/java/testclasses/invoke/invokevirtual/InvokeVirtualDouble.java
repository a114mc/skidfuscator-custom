package testclasses.invoke.invokevirtual;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeVirtualDouble {
    public InvokeVirtualDouble() {

    }

    @NativeObfuscation
    public double add(double a, double b) {
        return normalAdd(a, b);
    }

    public double normalAdd(double a, double b) {
        return a + b;
    }
}