package testclasses.stack;

import dev.skidfuscator.annotations.NativeObfuscation;

public class Pop2 {
    private boolean val;

    public Pop2() {
        this.val = false;
    }

    @NativeObfuscation
    public boolean exec() {
        this.setVal();
        return this.val;
    }

    private double setVal() {
        this.val = true;
        return 1;
    }
}
