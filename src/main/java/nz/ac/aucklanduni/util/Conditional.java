package nz.ac.aucklanduni.util;

public class Conditional {

    public static boolean isStringSet(String string) {
        if (string == null || string.equals("")) {
            return false;
        } else {
             return true;
        }
    }

    public static boolean isSet(Object obj) {
        if (obj == null) {
            return false;
        } else {
            return true;
        }
    }
}
