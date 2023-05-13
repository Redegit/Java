package Classwork.Cycles;

public class Factorial_for {
    public static void main(String[] args) {
        // вычисление факториала
        long f=1;
        for (int i=2; i<=6; i++)
            f *= i;
        System.out.println(f);

        // таблица умножения
        for (int i=1; i<=10; i++) {
            String s = "";

            for (int j=1; j<=10; j++) {
                s += i*j + "\t";
            }
            System.out.println(s);
        }
    }
}
