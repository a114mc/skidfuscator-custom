package testclasses.exceptions.negativesize.multidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class ThrowNegativeArraySizeLong {
    private long[][][] array;

    public ThrowNegativeArraySizeLong() {

    }

    @NativeObfuscation
    public int exec() {
        this.array = new long[-1][1][1];
        return 0;
    }
}
