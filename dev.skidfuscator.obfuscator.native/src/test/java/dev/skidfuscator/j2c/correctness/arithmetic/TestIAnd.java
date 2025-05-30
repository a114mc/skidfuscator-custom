package dev.skidfuscator.j2c.correctness.arithmetic;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestIAnd extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.arithmetic.IAnd.class;
    private String[] methodTest = {"exec"};
    private Class[][] methodParam = {new Class[]{int.class, int.class}};
    private Object[][] methodArgs = {new Object[]{11234567, 2478945}};

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
