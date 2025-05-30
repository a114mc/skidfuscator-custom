package testclasses.arrays.multidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

import java.util.ArrayList;

public class NewArrayObjectThreeDims {
    public NewArrayObjectThreeDims() {

    }

    @NativeObfuscation
    public String[][][] getArray() {
        return new String[2][3][4];
    }

    public ArrayList<ArrayList<ArrayList<String>>> test() {
        ArrayList<ArrayList<ArrayList<String>>> res = new ArrayList<>(2);
        String[][][] native_array = this.getArray();
        for (int i = 0; i < native_array.length; i++) {
            ArrayList<ArrayList<String>> current = new ArrayList<>(3);
            for (int j = 0; j < native_array[i].length; j++) {
                current.add(new ArrayList<String>(4));
            }
            res.add(current);
        }
        return res;
    }

}
