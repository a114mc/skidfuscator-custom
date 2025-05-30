package testclasses.arrays.unidimensional;

import dev.skidfuscator.annotations.NativeObfuscation;

import java.util.ArrayList;

public class StoreArrayChar {
    private char[] array;

    public StoreArrayChar() {
        this.array = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    }

    @NativeObfuscation
    public void setVal(int i, char val) {
        this.array[i] = val;
    }

    public ArrayList<Character> test() {
        ArrayList<Character> retval = new ArrayList<>();
        int i = 4;
        this.setVal(i, '光');
        //assert that also the rest of the array is unchanged
        for (int j = 0; j < this.array.length; j++)
            retval.add(array[j]);
        return retval;
    }

}
