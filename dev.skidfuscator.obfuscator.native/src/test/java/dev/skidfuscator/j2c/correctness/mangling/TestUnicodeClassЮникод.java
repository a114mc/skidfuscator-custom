package dev.skidfuscator.j2c.correctness.mangling;

import dev.skidfuscator.j2c.helpers.AbstractTestCorrectnessTemplate;
import testclasses.mangling.UnicodeClassЮникод;

/**
 * Tests for multiple methods in the same class
 */
public class TestUnicodeClassЮникод extends AbstractTestCorrectnessTemplate {
    private Class<?> className = UnicodeClassЮникод.class;
    private String[] methodTest = {"add"};
    private Class[][] methodParam = {new Class[]{int.class, int.class}};
    private Object[][] methodArgs = {new Object[]{15, 21}};

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
