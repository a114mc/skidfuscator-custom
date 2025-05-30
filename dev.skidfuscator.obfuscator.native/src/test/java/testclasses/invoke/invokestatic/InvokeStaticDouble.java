package testclasses.invoke.invokestatic;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeStaticDouble {
    public InvokeStaticDouble() {

    }

    public static double normalAdd(double a, double b) {
        return a + b;
    }

    @NativeObfuscation
    public double add(double a, double b) {
        return normalAdd(a, b);
    }
}