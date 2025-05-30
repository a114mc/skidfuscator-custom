package testclasses.invoke.invokestatic;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeStaticChar {
    public InvokeStaticChar() {

    }

    public static char normalAdd(char a, char b) {
        return (char) (a + b);
    }

    @NativeObfuscation
    public char add(char a, char b) {
        return normalAdd(a, b);
    }
}