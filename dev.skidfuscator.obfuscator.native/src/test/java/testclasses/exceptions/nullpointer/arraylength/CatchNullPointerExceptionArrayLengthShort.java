package testclasses.exceptions.nullpointer.arraylength;

import dev.skidfuscator.annotations.NativeObfuscation;
import testclasses.exceptions.nullpointer.SupportClassShort;

public class CatchNullPointerExceptionArrayLengthShort {

    public CatchNullPointerExceptionArrayLengthShort() {

    }

    @NativeObfuscation
    public int exec(SupportClassShort obj) {
        int res = 0;
        try {
            res = obj.b.length;
            res += 1000;
        } catch (NullPointerException e) {
            res++;
        }

        try {
            res = obj.b.length;
            res += 1000;
        } catch (RuntimeException e) {
            res++;
        }

        try {
            res = obj.b.length;
            res += 1000;
        } catch (Exception e) {
            res++;
        }

        try {
            res = obj.b.length;
            res += 1000;
        } catch (Throwable e) {
            res++;
        }

        return res;
    }
}
