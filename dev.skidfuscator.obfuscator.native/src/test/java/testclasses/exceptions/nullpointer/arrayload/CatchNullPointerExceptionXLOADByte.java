package testclasses.exceptions.nullpointer.arrayload;

import dev.skidfuscator.annotations.NativeObfuscation;
import testclasses.exceptions.nullpointer.SupportClassByte;

public class CatchNullPointerExceptionXLOADByte {

    public CatchNullPointerExceptionXLOADByte() {

    }

    @NativeObfuscation
    public int exec(SupportClassByte obj) {

        int res = 0;
        try {
            res += obj.b[1];
            res += 1000;
        } catch (NullPointerException e) {
            res++;
        }

        try {
            res += obj.b[1];
            res += 1000;
        } catch (RuntimeException e) {
            res++;
        }

        try {
            res += obj.b[1];
            res += 1000;
        } catch (Exception e) {
            res++;
        }

        try {
            res += obj.b[1];
            res += 1000;
        } catch (Throwable e) {
            res++;
        }

        return res;
    }
}
