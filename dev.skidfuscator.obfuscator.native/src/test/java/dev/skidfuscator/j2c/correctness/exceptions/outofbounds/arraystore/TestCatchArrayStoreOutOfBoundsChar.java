package dev.skidfuscator.j2c.correctness.exceptions.outofbounds.arraystore;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestCatchArrayStoreOutOfBoundsChar extends AbstractTestCorrectnessTemplate {
    private Class<?> className = testclasses.exceptions.outofbounds.arraystore.CatchArrayStoreOutOfBoundsChar.class;
    private String[] methodTest = {"exec"};
    private Class[][] methodParam = {new Class[]{}};
    private Object[][] methodArgs = {new Object[]{}};

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
