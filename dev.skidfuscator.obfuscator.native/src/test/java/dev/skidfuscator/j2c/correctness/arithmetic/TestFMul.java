package dev.skidfuscator.j2c.correctness.arithmetic;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestFMul extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.arithmetic.FMul.class;
    private String[] methodTest = {"exec"};
    private Class[][] methodParam = {new Class[]{float.class, float.class}};
    private Object[][] methodArgs = {new Object[]{3.5f, 0.5f}};


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
