package homework;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HW3_Presnukhin_PI21_2_Var24 {

    // Вариант 24: Определить сумму всех целых чисел, встречающихся в заданном тексте.

    public static void main(String[] args) {
        String txt = "/\\ | % # << - 100 1 9 some random words 1 5 1000 + >> @ $ | /\\";

        var pattern = "[0-9]+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(txt);

        int sum = 0;

        System.out.println("Изначальный текст: " + txt.substring(0, Math.min(txt.length(), 100)));
        System.out.print("Только числа из текста: ");

        while (m.find()) {
            String current = txt.substring(m.start(), m.end());
            System.out.print(current + " ");
            sum = sum + Integer.parseInt(current);
        }
        System.out.println();
        System.out.println("Сумма целых чисел в тексте: " + sum);
    }
}
