package dev.skidfuscator.j2c.correctness.casts;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestCastInt2Long extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.casts.CastInt2Long.class;
    private String[] methodTest = {"exec"};
    private Class[][] methodParam = {new Class[]{int.class}};
    private Object[][] methodArgs = {new Object[]{1000000}};


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
