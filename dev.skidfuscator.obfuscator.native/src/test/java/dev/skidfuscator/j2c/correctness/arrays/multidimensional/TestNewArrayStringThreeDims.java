package dev.skidfuscator.j2c.correctness.arrays.multidimensional;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestNewArrayStringThreeDims extends AbstractTestCorrectnessTemplate {
    private Class<?> className = testclasses.arrays.multidimensional.NewArrayObjectThreeDims.class;
    private String[] methodTest = {"test"};
    private Class[][] methodParam = {new Class[]{}};
    private Object[][] methodArgs = {new Object[]{}};

    @Override
    public String[] getAnnotatedMethodName() {
        return new String[]{"getArray"};
    }

    @Override
    public Class<?>[][] getAnnotatedMethodParams() {
        return new Class[][]{new Class[]{}};
    }

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
