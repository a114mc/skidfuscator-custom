package dev.skidfuscator.j2c.correctness.exceptions.nullpointer.setfield;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestThrowNullPointerExceptionSetFieldLong extends AbstractTestCorrectnessTemplate {
    private Class<?> className =
            testclasses.exceptions.nullpointer.setfield.ThrowNullPointerExceptionSetFieldLong.class;
    private String[] methodTest = {"exec"};
    private Class[][] methodParam = {new Class[]{testclasses.exceptions.nullpointer.SupportClassLong.class}};
    private Object[][] methodArgs = {new Object[]{null}};

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
