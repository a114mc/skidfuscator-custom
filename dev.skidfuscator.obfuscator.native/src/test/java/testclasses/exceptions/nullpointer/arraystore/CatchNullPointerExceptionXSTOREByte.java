package testclasses.exceptions.nullpointer.arraystore;

import dev.skidfuscator.annotations.NativeObfuscation;
import testclasses.exceptions.nullpointer.SupportClassByte;

public class CatchNullPointerExceptionXSTOREByte {

    public CatchNullPointerExceptionXSTOREByte() {

    }

    @NativeObfuscation
    public int exec(SupportClassByte obj) {
        int res = 0;
        try {
            obj.b[1] = 1;
            res += 1000;
        } catch (NullPointerException e) {
            res++;
        }

        try {
            obj.b[1] = 0;
            res += 1000;
        } catch (RuntimeException e) {
            res++;
        }

        try {
            obj.b[1] = 1;
            res += 1000;
        } catch (Exception e) {
            res++;
        }

        try {
            obj.b[1] = 0;
            res += 1000;
        } catch (Throwable e) {
            res++;
        }

        return res;
    }
}
