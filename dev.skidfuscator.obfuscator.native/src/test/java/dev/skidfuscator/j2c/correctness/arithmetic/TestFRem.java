package dev.skidfuscator.j2c.correctness.arithmetic;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestFRem extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.arithmetic.FRem.class;
    private String[] methodTest = {"exec", "exec"};
    private Class[][] methodParam = {new Class[]{float.class, float.class}, new Class[]{float.class, float.class}};
    private Object[][] methodArgs = {new Object[]{3.5f, 0.5f}, new Object[]{3.5f, 0}};


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
