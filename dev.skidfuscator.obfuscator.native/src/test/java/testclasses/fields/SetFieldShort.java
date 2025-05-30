package testclasses.fields;

import dev.skidfuscator.annotations.NativeObfuscation;

public class SetFieldShort {
    private short fieldS;

    public SetFieldShort() {
        this.fieldS = 3000;
        this.setField((short) -3000);
    }

    public short getField() {
        return this.fieldS;
    }

    @NativeObfuscation
    public void setField(short value) {
        this.fieldS = value;
    }
}
