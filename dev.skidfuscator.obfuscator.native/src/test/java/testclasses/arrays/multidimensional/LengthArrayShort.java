package testclasses.arrays.multidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class LengthArrayShort {
    private short[][] array;

    public LengthArrayShort() {
        this.array = new short[3][2];
    }

    @NativeObfuscation
    public int getLen() {
        return this.array.length;
    }

}
