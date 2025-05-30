package testclasses.exceptions.outofbounds.arrayload;

import dev.skidfuscator.annotations.NativeObfuscation;

public class ThrowArrayLoadOutOfBoundsShort {
    private short[] array;

    public ThrowArrayLoadOutOfBoundsShort() {
        this.array = new short[2];
    }

    @NativeObfuscation
    public short exec() {
        return this.array[2];
    }
}
