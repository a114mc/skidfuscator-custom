package testclasses.conditionals;

import dev.skidfuscator.annotations.NativeObfuscation;

public class Ifacmpeq {
    public Ifacmpeq() {

    }

    @NativeObfuscation
    public boolean exec(Object value0, Object value1) {
        return value0 != value1;
    }

}
