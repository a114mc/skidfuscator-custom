package testclasses.invoke.invokevirtual;

import dev.skidfuscator.annotations.NativeObfuscation;

public class InvokeVirtualChar {
    public InvokeVirtualChar() {

    }

    @NativeObfuscation
    public char add(char a, char b) {
        return normalAdd(a, b);
    }

    public char normalAdd(char a, char b) {
        return (char) (a + b);
    }
}