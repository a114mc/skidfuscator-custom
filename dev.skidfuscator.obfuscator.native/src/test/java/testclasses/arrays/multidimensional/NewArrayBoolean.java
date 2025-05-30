package testclasses.arrays.multidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

import java.util.ArrayList;

public class NewArrayBoolean {
    public NewArrayBoolean() {

    }

    @NativeObfuscation
    public boolean[][] getArray() {
        return new boolean[2][3];
    }

    public ArrayList<ArrayList<Boolean>> test() {
        ArrayList<ArrayList<Boolean>> res = new ArrayList<>(2);
        boolean[][] native_array = this.getArray();
        for (int i = 0; i < native_array.length; i++) {
            ArrayList<Boolean> current = new ArrayList<>(3);
            res.add(current);
        }
        return res;
    }

}
