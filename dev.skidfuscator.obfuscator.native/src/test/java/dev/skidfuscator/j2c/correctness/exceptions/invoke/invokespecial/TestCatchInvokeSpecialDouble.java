package dev.skidfuscator.j2c.correctness.exceptions.invoke.invokespecial;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestCatchInvokeSpecialDouble extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.exceptions.invoke.invokespecial.CatchInvokeSpecialDouble.class;
    private String[] methodTest = {"add"};
    private Class[][] methodParam = {new Class[]{double.class, double.class}};
    private Object[][] methodArgs = {new Object[]{3.14159265359, 2.7182818284}};

    @Override
    public Class<?> getTestClass() {
        return className;
    }

    @Override
    public String[] getTestMethodName() {
        return methodTest;
    }

    @Override
    public Class<?>[][] getTestMethodParams() {
        return methodParam;
    }

    @Override
    public Object[][] getTestMethodArgs() {
        return methodArgs;
    }
}
