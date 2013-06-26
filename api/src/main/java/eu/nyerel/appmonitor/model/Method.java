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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Method method = (Method) o;

        if (!name.equals(method.name)) return false;
        if (!sourceClass.equals(method.sourceClass)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sourceClass.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
