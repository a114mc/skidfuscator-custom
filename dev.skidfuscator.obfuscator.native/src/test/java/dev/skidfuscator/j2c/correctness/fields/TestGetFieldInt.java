package dev.skidfuscator.j2c.correctness.fields;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestGetFieldInt extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.fields.GetFieldInt.class;
    private String[] methodTest = {"getField"};
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
