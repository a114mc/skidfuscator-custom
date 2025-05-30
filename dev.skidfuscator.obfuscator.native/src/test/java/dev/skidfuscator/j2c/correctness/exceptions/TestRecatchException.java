package dev.skidfuscator.j2c.correctness.exceptions;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

/**
 * Tests that the handleSystemException method of MethodBytecodeExtractor.java is able to support statements such as
 * catch(NullPointerException e)
 * { try{throw new NullPointerException; }
 * catch(NullPointerException e){}}
 */
public class TestRecatchException extends AbstractTestCorrectnessTemplate {
    private Class<?> className = testclasses.exceptions.RecatchException.class;
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
