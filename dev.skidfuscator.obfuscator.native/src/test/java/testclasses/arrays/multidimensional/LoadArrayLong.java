package testclasses.arrays.multidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class LoadArrayLong {
    private long[][] array;

    public LoadArrayLong() {
        this.array = new long[2][3];
        this.array[0] = new long[]{10000000000L, 10000000002L, 10000000003L};
        this.array[1] = new long[]{10000000004L, 10000000005L, 10000000006L};
    }

    @NativeObfuscation
    public long getVal() {
        return this.array[1][1];
    }

}
