package testclasses.arrays.multidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class LengthArrayChar {
    private char[][] array;

    public LengthArrayChar() {
        this.array = new char[3][2];
    }

    @NativeObfuscation
    public int getLen() {
        return this.array.length;
    }

}
