package testclasses.exceptions.nullpointer.getfield;

import dev.skidfuscator.annotations.NativeObfuscation;
import testclasses.exceptions.nullpointer.SupportClassFloat;

public class CatchNullPointerExceptionGetFieldFloat {

    public CatchNullPointerExceptionGetFieldFloat() {

    }

    @NativeObfuscation
    public int exec(SupportClassFloat obj) {
        int res = 0;
        try {
            res += (int) obj.a;
            res += 1000;
        } catch (NullPointerException e) {
            res++;
        }

        try {
            res += (int) obj.a;
            res += 1000;
        } catch (RuntimeException e) {
            res++;
        }

        try {
            res += (int) obj.a;
            res += 1000;
        } catch (Exception e) {
            res++;
        }

        try {
            res += (int) obj.a;
            res += 1000;
        } catch (Throwable e) {
            res++;
        }

        return res;
    }
}
