package testclasses.exceptions.nullpointer.setfield;

import dev.skidfuscator.annotations.NativeObfuscation;
import testclasses.exceptions.nullpointer.SupportClassInt;

public class ThrowNullPointerExceptionSetFieldInt {
    public ThrowNullPointerExceptionSetFieldInt() {

    }

    @NativeObfuscation
    public int exec(SupportClassInt obj) {
        obj.a = 0;
        return 0;
    }
}
