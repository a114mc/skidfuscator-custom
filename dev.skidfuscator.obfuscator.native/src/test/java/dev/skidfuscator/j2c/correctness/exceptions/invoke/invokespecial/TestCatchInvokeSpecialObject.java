package dev.skidfuscator.j2c.correctness.exceptions.invoke.invokespecial;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestCatchInvokeSpecialObject extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.exceptions.invoke.invokespecial.CatchInvokeSpecialObject.class;
    private String[] methodTest = {"concatenate"};
    private Class[][] methodParam = {new Class[]{String.class, char.class}};
    private Object[][] methodArgs = {new Object[]{"hello world", '!'}};

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
