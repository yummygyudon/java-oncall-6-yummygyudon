package oncall.global.channel;

public abstract class Printer {

    public static void print(String message) {
        System.out.print(message);
    }
    public static void printLine(String message) {
        System.out.println(message);
    }
    public static void printBlankLine() {
        System.out.println();
    }

}
