package testclasses.invoke.invokeinterface;

import dev.skidfuscator.annotations.NativeObfuscation;

import java.util.ArrayList;

public class InvokeInterfaceIntArray {
    public InvokeInterfaceIntArray() {

    }

    @NativeObfuscation
    public int[] exec(AdderInterface interf) {
        return interf.array();
    }

    public ArrayList<Integer> test() {
        ArrayList<Integer> res = new ArrayList<Integer>(10);
        int[] native_array = this.exec(new ImplementedInterface());
        for (int i = 0; i < native_array.length; i++)
            res.add(native_array[i]);
        return res;
    }
}