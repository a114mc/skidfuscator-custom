package testclasses.exceptions.nullpointer.arraylength;

import dev.skidfuscator.annotations.NativeObfuscation;
import testclasses.exceptions.nullpointer.SupportClassLong;

public class ThrowNullPointerExceptionArrayLengthLong {
    public ThrowNullPointerExceptionArrayLengthLong() {

    }

    @NativeObfuscation
    public int exec(SupportClassLong obj) {
        return obj.b.length;
    }
}
