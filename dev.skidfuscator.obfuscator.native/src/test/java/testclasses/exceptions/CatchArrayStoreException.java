package testclasses.exceptions;

import dev.skidfuscator.annotations.NativeObfuscation;

public class CatchArrayStoreException {
    private Object[] array;

    public CatchArrayStoreException() {
        this.array = new String[2];
        this.array[0] = "hello";
        this.array[1] = "world";
    }

    @NativeObfuscation
    public int exec() {
        int res = 0;
        try {
            this.array[1] = 1;
            res += 1000;
        } catch (ArrayStoreException e) {
            res++;
        }

        try {
            this.array[1] = 1;
            res += 1000;
        } catch (RuntimeException e) {
            res++;
        }

        try {
            this.array[1] = 1;
            res += 1000;
        } catch (Exception e) {
            res++;
        }

        try {
            this.array[1] = 1;
            res += 1000;
        } catch (Throwable e) {
            res++;
        }
        return res;
    }
}
