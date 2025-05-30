package testclasses.exceptions.nullpointer;

import dev.skidfuscator.annotations.NativeObfuscation;

public class CatchThrowedNullException {
    public CatchThrowedNullException() {

    }

    @NativeObfuscation
    public int exec() {
        int res = 0;
        try {
            throw null;
        } catch (NullPointerException e) {
            res++;
        }

        try {
            throw null;
        } catch (RuntimeException e) {
            res++;
        }

        try {
            throw null;
        } catch (Exception e) {
            res++;
        }

        try {
            throw null;
        } catch (Throwable e) {
            res++;
        }

        return res;
    }
}
