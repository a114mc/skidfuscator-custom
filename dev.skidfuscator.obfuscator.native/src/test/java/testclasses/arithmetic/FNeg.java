package testclasses.arithmetic; //when changing this, remember to change also the variable in eu.fbk.hardening.helpers

import dev.skidfuscator.annotations.NativeObfuscation;

public class FNeg {
    public FNeg() {

    }

    @NativeObfuscation
    public float exec(float a) {
        return -a;
    }

}
