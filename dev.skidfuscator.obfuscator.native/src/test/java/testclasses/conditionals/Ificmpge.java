package testclasses.conditionals;

import dev.skidfuscator.annotations.NativeObfuscation;

public class Ificmpge {
    public Ificmpge() {

    }

    @NativeObfuscation
    public boolean exec(int value) {
        return value < 1000000000;
    }

}
