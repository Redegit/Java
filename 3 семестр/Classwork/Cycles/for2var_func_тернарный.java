package Classwork.Cycles;

public class for2var_func_тернарный {
    public static void main(String[] args) {
        int i = 456;
        String bin = dec2bin(i);
        System.out.println(bin);
        System.out.print(bin2dec(bin));
    }

    public static String dec2bin(int x) {
        String s = "";
        while (x > 0) {
            s = (x % 2) + s;
            x = x / 2;
        }
        return s;
    }

    public static int bin2dec(String s) {
        int x = 0;
        for (int j = 0, l = s.length() - 1; l >= 0; j++, l--) {
            x += s.charAt(j) == '1' ? Math.pow(2, l) : 0;
        }
        return x;
    }
}
