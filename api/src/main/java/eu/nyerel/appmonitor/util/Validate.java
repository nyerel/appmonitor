package eu.nyerel.appmonitor.util;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
public class Validate {

    public static void notNull(Object o) {
        notNull(o, "The validated object is null");
    }

    public static void notEmpty(String string) {
        notEmpty(string, "The validated string is empty");
    }

    public static void notEmpty(String string, String message) {
        if (string == null || string.length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
