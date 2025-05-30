package dev.skidfuscator.j2c.correctness.mangling;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;
import testclasses.mangling.OverloadedMethodsMultiArrays;

public class TestOverloadedMethodsMultiArrays extends AbstractTestCorrectnessTemplate {
    private Class<?> className = OverloadedMethodsMultiArrays.class;
    private String[] methodTest = {"add", "add"};
    private Class[][] methodParam = {new Class[]{int.class, double.class}, new Class[]{String[][].class,
            int[][].class}};
    private Object[][] methodArgs = {new Object[]{15, 21}, new Object[]{new String[][]{null, new String[]{"15"}},
            new int[][]{null, new int[]{36}}}};

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
