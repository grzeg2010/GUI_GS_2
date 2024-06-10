package Logika;

public class AbstractMessages {
    // Colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static String divider(String dividerText) { return ANSI_CYAN + "\n------ " + dividerText + " ------" + ANSI_RESET; }
    public static String error(String errorText) { return ANSI_RED + errorText + ANSI_RESET; }
    public static String gray(String grayText) { return ANSI_WHITE + grayText + ANSI_RESET; }
    public static String info(String infoText) { return ANSI_PURPLE + infoText + ANSI_RESET; }
    public static String progress(String progressText) { return ANSI_BLUE + progressText + ANSI_RESET; }
    public static String success(String successText) { return ANSI_GREEN + successText + ANSI_RESET; }
    public static String warning(String warningText) { return ANSI_YELLOW + warningText + ANSI_RESET; }
}
