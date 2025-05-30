package testclasses.exceptions.outofbounds.arrayload;

import dev.skidfuscator.annotations.NativeObfuscation;

public class ThrowArrayLoadOutOfBoundsBoolean {
    private boolean[] array;

    public ThrowArrayLoadOutOfBoundsBoolean() {
        this.array = new boolean[2];
    }

    @NativeObfuscation
    public boolean exec() {
        return this.array[2];
    }
}
