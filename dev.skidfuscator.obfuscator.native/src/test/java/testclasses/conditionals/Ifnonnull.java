package testclasses.conditionals;

import dev.skidfuscator.annotations.NativeObfuscation;

public class Ifnonnull {
    public Ifnonnull() {

    }

    @NativeObfuscation
    public boolean exec(Object value) {
        return value == null;
    }

}
