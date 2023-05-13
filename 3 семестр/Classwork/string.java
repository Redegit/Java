package Classwork;

public class string {

    public static void main(String[] args) {
        // эффективная замена строк, расширяемая, что позволяет быстро менять строку
        StringBuffer sb = new StringBuffer("A");
        for(int i = 0; i < 1000000; i++) sb.append("A");
        System.out.println(sb);

        // методы String
//      substring(start: , end: )
//      toUpperCase()
//      length()
//      ValueOf(x)
//      concat(str: )
//      charAt(index: )
//      replace(target: , replacement: )
//      split(delimiter: )
    }
}
