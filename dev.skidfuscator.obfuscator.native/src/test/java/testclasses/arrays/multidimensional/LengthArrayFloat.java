package testclasses.arrays.multidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class LengthArrayFloat {
    private float[][] array;

    public LengthArrayFloat() {
        this.array = new float[3][2];
    }

    @NativeObfuscation
    public int getLen() {
        return this.array.length;
    }

}
