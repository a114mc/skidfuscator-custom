package testclasses.exceptions.invoke.invokeinterface;

import dev.skidfuscator.annotations.NativeObfuscation;

public class CatchInvokeInterfaceBoolean {
    public CatchInvokeInterfaceBoolean() {

    }

    @NativeObfuscation
    public int div(DivisionInterface interf, boolean a) {
        int res = 0;
        try {
            res += interf.div(a) ? 1 : 0;
            res++;
        } catch (Exception e) {
            res += 1000;
        }
        return res;
    }
}
