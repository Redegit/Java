package Classwork.Cycles;

public class For {
    public static void main(String[] args) {
        for (int i=2;i<=100;i++) {
            boolean fl = true;
            for (int j=2;j<((int)Math.sqrt(i));j++) {
                if (i % j == 0) {
                    fl = false;
                    break;
                }
            System.out.println(i);
            }
        }
    }
}
