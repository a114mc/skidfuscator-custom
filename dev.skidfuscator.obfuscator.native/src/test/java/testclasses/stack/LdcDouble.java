package testclasses.stack;


import dev.skidfuscator.annotations.NativeObfuscation;

public class LdcDouble {
    public LdcDouble() {

    }

    @NativeObfuscation
    public double exec() {
        double a = 3.5;
        double b = -0.5;
        return a + b;
    }
}
