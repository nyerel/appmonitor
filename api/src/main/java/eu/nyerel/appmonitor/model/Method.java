package eu.nyerel.appmonitor.model;

import eu.nyerel.appmonitor.util.Validate;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
public class Method {

    private final Class sourceClass;
    private final String name;

    public Method(Class sourceClass, String name) {
        Validate.notNull(sourceClass);
        Validate.notEmpty(name);
        this.sourceClass = sourceClass;
        this.name = name;
    }

    public Class getSourceClass() {
        return sourceClass;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return sourceClass.getName() + "#" + name;
    }

}
