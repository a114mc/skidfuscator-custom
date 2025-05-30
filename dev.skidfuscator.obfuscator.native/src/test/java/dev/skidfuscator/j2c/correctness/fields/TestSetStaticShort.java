package dev.skidfuscator.j2c.correctness.fields;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestSetStaticShort extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.fields.SetStaticShort.class;
    private String[] methodTest = {"getStatic"};
    private Class[][] methodParam = {new Class[]{}};
    private Object[][] methodArgs = {new Object[]{}};
    private String[] annotatedMethod = {"setStatic"};
    private Class[][] annotatedParams = {new Class[]{short.class}};


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

    @Override
    public String[] getAnnotatedMethodName() {
        return annotatedMethod;
    }

    @Override
    public Class<?>[][] getAnnotatedMethodParams() {
        return this.annotatedParams;
    }
}
