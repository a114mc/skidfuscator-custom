package testclasses.exceptions.negativesize.unidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class ThrowNegativeArraySizeBoolean {
    private boolean[] array;

    public ThrowNegativeArraySizeBoolean() {

    }

    @NativeObfuscation
    public int exec() {
        this.array = new boolean[-1];
        return 0;
    }
}
