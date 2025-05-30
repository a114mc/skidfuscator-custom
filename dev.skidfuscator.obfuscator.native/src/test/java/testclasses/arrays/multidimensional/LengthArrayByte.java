package testclasses.arrays.multidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class LengthArrayByte {
    private byte[][] array;

    public LengthArrayByte() {
        this.array = new byte[3][2];
    }

    @NativeObfuscation
    public int getLen() {
        return this.array.length;
    }

}
