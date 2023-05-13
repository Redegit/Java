public class Main {

    public static int sum(int a, int b) {
        return a + b;
    }

    public static boolean isPositive(int number) {
        return number > 0;
    }

    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}
