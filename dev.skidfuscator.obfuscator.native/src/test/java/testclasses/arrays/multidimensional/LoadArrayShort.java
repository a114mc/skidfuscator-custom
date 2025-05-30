package testclasses.arrays.multidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class LoadArrayShort {
    private short[][] array;

    public LoadArrayShort() {
        this.array = new short[2][3];
        this.array[0] = new short[]{1000, 1001, 1002};
        this.array[1] = new short[]{10000, 10001, 10002};
    }

    @NativeObfuscation
    public short getVal() {
        return this.array[1][2];
    }

}
