package dev.skidfuscator.j2c.correctness.exceptions.nullpointer.arraylength;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;
import testclasses.exceptions.nullpointer.SupportClassByte;

public class TestThrowNullPointerExceptionArrayLengthByte extends AbstractTestCorrectnessTemplate {
    private Class<?> className =
            testclasses.exceptions.nullpointer.arraylength.ThrowNullPointerExceptionArrayLengthByte.class;
    private String[] methodTest = {"exec"};
    private Class[][] methodParam = {new Class[]{SupportClassByte.class}};
    private Object[][] methodArgs = {new Object[]{new SupportClassByte()}};

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
