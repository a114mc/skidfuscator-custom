package dev.skidfuscator.j2c.correctness.exceptions.negativesize.unidimensional;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;
import testclasses.exceptions.negativesize.unidimensional.ThrowNegativeArraySizeInt;

public class TestThrowNegativeArraySizeInt extends AbstractTestCorrectnessTemplate {
    private Class<?> className = ThrowNegativeArraySizeInt.class;
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
