package testclasses.fields;

import dev.skidfuscator.annotations.NativeObfuscation;

public class GetStaticInt {
    private static int fieldI;

    public GetStaticInt() {
        GetStaticInt.fieldI = 1000000;
    }

    @NativeObfuscation
    public int getStatic() {
        return GetStaticInt.fieldI;
    }
}
