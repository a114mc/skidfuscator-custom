package dev.skidfuscator.j2c.correctness.conditionals;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestIficmpeq extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.conditionals.Ificmpeq.class;
    private String[] methodTest = {"exec", "exec", "exec"};
    private Class[][] methodParam = {new Class[]{int.class}, new Class[]{int.class}, new Class[]{int.class}};
    private Object[][] methodArgs = {new Object[]{1000000000}, new Object[]{0}, new Object[]{2000000000}};


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
