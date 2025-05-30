package testclasses.arrays.unidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class LoadArrayByte {
    private byte[] array;

    public LoadArrayByte() {
        this.array = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    }

    @NativeObfuscation
    public byte getVal() {
        return this.array[4];
    }

}
