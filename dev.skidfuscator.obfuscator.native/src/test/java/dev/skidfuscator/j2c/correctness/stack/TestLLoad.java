package dev.skidfuscator.j2c.correctness.stack;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestLLoad extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.stack.LLoad.class;
    private String[] methodTest = {"exec"};
    private Class[][] methodParam = {new Class[]{long.class}};
    private Object[][] methodArgs = {new Object[]{100000000000L}};


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
