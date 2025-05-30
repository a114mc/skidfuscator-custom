package testclasses.invoke.invokestatic;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeStaticCastByte {
    public InvokeStaticCastByte() {

    }

    public static byte normalAdd(byte a, byte b) {
        return (byte) (a + b);
    }

    @NativeObfuscation
    public boolean add(byte a, byte b) {
        return normalAdd(a, b) == (byte) (a + b);
    }
}