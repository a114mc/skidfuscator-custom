package testclasses.exceptions.nullpointer.getfield;

import dev.skidfuscator.annotations.NativeObfuscation;
import testclasses.exceptions.nullpointer.SupportClassInt;

public class CatchNullPointerExceptionGetFieldInt {

    public CatchNullPointerExceptionGetFieldInt() {

    }

    @NativeObfuscation
    public int exec(SupportClassInt obj) {
        int res = 0;
        try {
            res += obj.a;
            res += 1000;
        } catch (NullPointerException e) {
            res++;
        }

        try {
            res += obj.a;
            res += 1000;
        } catch (RuntimeException e) {
            res++;
        }

        try {
            res += obj.a;
            res += 1000;
        } catch (Exception e) {
            res++;
        }

        try {
            res += obj.a;
            res += 1000;
        } catch (Throwable e) {
            res++;
        }

        return res;
    }
}
