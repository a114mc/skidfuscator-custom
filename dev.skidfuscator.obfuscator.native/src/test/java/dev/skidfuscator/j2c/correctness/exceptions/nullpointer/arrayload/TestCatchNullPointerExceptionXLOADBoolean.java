package dev.skidfuscator.j2c.correctness.exceptions.nullpointer.arrayload;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;
import testclasses.exceptions.nullpointer.SupportClassBoolean;

public class TestCatchNullPointerExceptionXLOADBoolean extends AbstractTestCorrectnessTemplate {
    private Class<?> className =
            testclasses.exceptions.nullpointer.arrayload.CatchNullPointerExceptionXLOADBoolean.class;
    private String[] methodTest = {"exec"};
    private Class[][] methodParam = {new Class[]{SupportClassBoolean.class}};
    private Object[][] methodArgs = {new Object[]{new SupportClassBoolean()}};

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
