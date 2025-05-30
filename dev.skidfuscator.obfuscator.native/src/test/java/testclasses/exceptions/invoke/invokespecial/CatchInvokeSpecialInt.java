package testclasses.exceptions.invoke.invokespecial;

import dev.skidfuscator.annotations.NativeObfuscation;

public class CatchInvokeSpecialInt extends InvokeVirtualInt {
    public CatchInvokeSpecialInt() {

    }

    @NativeObfuscation
    @Override
    public int add(int a, int b) {
        int res = 0;
        try {
            super.normalAdd(a, b);
            res += 1000;
        } catch (Exception e) {
            res++;
        }
        return res;
    }

    //wrong method, I want the one of the superclass to be called -> invokespecial
    public int normalAdd(int a, int b) {
        return a - b;
    }
}
