package testclasses.exceptions.negativesize.unidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class ThrowNegativeArraySizeChar {
    private char[] array;

    public ThrowNegativeArraySizeChar() {

    }

    @NativeObfuscation
    public int exec() {
        this.array = new char[-1];
        return 0;
    }
}
