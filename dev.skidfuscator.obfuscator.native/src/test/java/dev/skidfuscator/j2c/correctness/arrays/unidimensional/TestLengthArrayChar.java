package dev.skidfuscator.j2c.correctness.arrays.unidimensional;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestLengthArrayChar extends AbstractTestCorrectnessTemplate {
    private Class<?> className = testclasses.arrays.unidimensional.LengthArrayChar.class;
    private String[] methodTest = {"getLen"};
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
