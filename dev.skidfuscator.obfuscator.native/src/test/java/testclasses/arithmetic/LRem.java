package testclasses.arithmetic; //when changing this, remember to change also the variable in eu.fbk.hardening.helpers

import dev.skidfuscator.annotations.NativeObfuscation;

public class LRem {
    public LRem() {

    }

    @NativeObfuscation
    public long exec(long a, long b) {
        return a % b;
    }

}
