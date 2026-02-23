/**
 * Small string utility helpers used by encoders.
 */
public final class Strings {
    private Strings() {}

    public static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }
}