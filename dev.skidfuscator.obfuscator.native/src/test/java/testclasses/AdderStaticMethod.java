package testclasses;

import dev.skidfuscator.annotations.NativeObfuscation;

public class AdderStaticMethod {
    public AdderStaticMethod() {

    }

    @NativeObfuscation
    public static int add(int a, int b) {
        return a + b;
    }
}
