package testclasses.arrays.unidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class LoadArrayDouble {
    private double[] array;

    public LoadArrayDouble() {
        this.array = new double[]{.1, .2, .3, .4, .5, .6, .7, .8, .9, 1e110};
    }

    @NativeObfuscation
    public double getVal() {
        return this.array[9];
    }

}
