package testclasses.arithmetic; //when changing this, remember to change also the variable in eu.fbk.hardening.helpers

import dev.skidfuscator.annotations.NativeObfuscation;

public class IUShr {
    public IUShr() {

    }

    @NativeObfuscation
    public int exec(int a, int b) {
        return a >>> b;
    }

}
