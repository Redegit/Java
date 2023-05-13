package Classwork;

import java.util.Locale;

public class palindrome {

    public static void main(String[] args) {
        String s = "Абтба";
        System.out.println(IsPali(s));


    }

    public static boolean IsPali(String s) {
        boolean result = true;
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.toLowerCase().charAt(i) != s.toLowerCase().charAt(len - i - 1)) {
                result = false;
            }
        }
        return result;
    }
}
