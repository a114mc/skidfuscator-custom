package testclasses.arithmetic; //when changing this, remember to change also the variable in eu.fbk.hardening.helpers

import dev.skidfuscator.annotations.NativeObfuscation;

public class DAdd {
    public DAdd() {

    }

    @NativeObfuscation
    public double exec(double a, double b) {
        return a + b;
    }

}
