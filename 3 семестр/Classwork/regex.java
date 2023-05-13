package Classwork;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {

    public static void main(String[] args) {
        var s = "Help me, please";
        var pattern = "[a-z]+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(s);

        while (m.find()) {
            System.out.print(s.substring(m.start(), m.end()) + "*");
        }

    }
}

