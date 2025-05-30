package testclasses.arrays.unidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

import java.util.ArrayList;

public class NewArrayBoolean {
    public NewArrayBoolean() {

    }

    @NativeObfuscation
    public boolean[] getArray() {
        return new boolean[10];
    }

    public ArrayList<Boolean> test() {
        ArrayList<Boolean> res = new ArrayList<Boolean>(10);
        boolean[] native_array = this.getArray();
        for (int i = 0; i < native_array.length; i++)
            res.add(native_array[i]);
        return res;
    }

}
