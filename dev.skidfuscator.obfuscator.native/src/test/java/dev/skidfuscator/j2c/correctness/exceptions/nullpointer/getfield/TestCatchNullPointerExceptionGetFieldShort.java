package dev.skidfuscator.j2c.correctness.exceptions.nullpointer.getfield;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;
import testclasses.exceptions.nullpointer.getfield.CatchNullPointerExceptionGetFieldShort;

public class TestCatchNullPointerExceptionGetFieldShort extends AbstractTestCorrectnessTemplate {
    private Class<?> className = CatchNullPointerExceptionGetFieldShort.class;
    private String[] methodTest = {"exec"};
    private Class[][] methodParam = {new Class[]{testclasses.exceptions.nullpointer.SupportClassShort.class}};
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
