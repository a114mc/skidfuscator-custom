package dev.skidfuscator.j2c.correctness.exceptions.invoke.invokeinterface;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;
import testclasses.exceptions.invoke.invokeinterface.DivisionInterface;
import testclasses.exceptions.invoke.invokeinterface.ImplementedInterface;

public class TestCatchInvokeInterfaceChar extends AbstractTestCorrectnessTemplate {

    private Class<?> className = testclasses.exceptions.invoke.invokeinterface.CatchInvokeInterfaceChar.class;
    private String[] methodTest = {"div"};
    private Class[][] methodParam = {new Class[]{DivisionInterface.class, char.class}};
    private Object[][] methodArgs = {new Object[]{new ImplementedInterface(), (char) 10}};


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
