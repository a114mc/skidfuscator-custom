package testclasses.exceptions.nullpointer.arraystore;

import dev.skidfuscator.annotations.NativeObfuscation;
import testclasses.exceptions.nullpointer.SupportClassFloat;

public class CatchNullPointerExceptionXSTOREFloat {

    public CatchNullPointerExceptionXSTOREFloat() {

    }

    @NativeObfuscation
    public int exec(SupportClassFloat obj) {
        int res = 0;
        try {
            obj.b[1] = (float) 1;
            res += 1000;
        } catch (NullPointerException e) {
            res++;
        }

        try {
            obj.b[1] = (float) 0;
            res += 1000;
        } catch (RuntimeException e) {
            res++;
        }

        try {
            obj.b[1] = (float) 1;
            res += 1000;
        } catch (Exception e) {
            res++;
        }

        try {
            obj.b[1] = (float) 0;
            res += 1000;
        } catch (Throwable e) {
            res++;
        }

        return res;
    }
}
