package dev.skidfuscator.j2c.correctness.invoke.invokestatic;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;

public class TestInvokeStaticBoolean extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.invoke.invokestatic.InvokeStaticBoolean.class;
    private String[] methodTest = {"and", "and", "and", "and"};
    private Class[][] methodParam = {new Class[]{boolean.class, boolean.class}, new Class[]{boolean.class,
            boolean.class},
            new Class[]{boolean.class, boolean.class}, new Class[]{boolean.class, boolean.class}};
    private Object[][] methodArgs = {new Object[]{false, false}, new Object[]{true, false},
            new Object[]{false, true}, new Object[]{true, true}};


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
