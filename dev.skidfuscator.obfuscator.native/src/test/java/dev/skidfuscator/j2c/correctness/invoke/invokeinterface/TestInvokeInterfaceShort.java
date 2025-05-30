package dev.skidfuscator.j2c.correctness.invoke.invokeinterface;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;
import testclasses.invoke.invokeinterface.AdderInterface;
import testclasses.invoke.invokeinterface.ImplementedInterface;

public class TestInvokeInterfaceShort extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.invoke.invokeinterface.InvokeInterfaceShort.class;
    private String[] methodTest = {"add"};
    private Class[][] methodParam = {new Class[]{AdderInterface.class, short.class, short.class}};
    private Object[][] methodArgs = {new Object[]{new ImplementedInterface(), (short) 3000, (short) -2000}};


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
