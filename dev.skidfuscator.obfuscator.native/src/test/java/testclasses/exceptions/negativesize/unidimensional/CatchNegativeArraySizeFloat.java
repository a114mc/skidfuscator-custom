package testclasses.exceptions.negativesize.unidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

public class CatchNegativeArraySizeFloat {
    private float[] array;

    public CatchNegativeArraySizeFloat() {

    }

    @NativeObfuscation
    public int exec() {
        int res = 0;
        try {
            this.array = new float[-1];
            res += 1000;
        } catch (NegativeArraySizeException e) {
            res++;
        }

        try {
            this.array = new float[-1];
            res += 1000;
        } catch (RuntimeException e) {
            res++;
        }

        try {
            this.array = new float[-1];
            res += 1000;
        } catch (Exception e) {
            res++;
        }

        try {
            this.array = new float[-1];
            res += 1000;
        } catch (Throwable e) {
            res++;
        }

        return res;
    }
}
