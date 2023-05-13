package homework;

import java.util.Scanner;

public class Presnukhin_PI21_2_N24 {
    public static double pow(double x, int n) {
        double result = x;
        for (int i=1; i < n; i++) {
            result *= x;
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter x >> ");
        double x = sc.nextDouble();

        int accuracy = 100;
        int sign = 1;
        double result = 0;

        for (int i=1; i < accuracy; i++) {
            result += sign * pow(x, 2*i) / i / (2 * i - 1);
            sign *= -1;

        }

        double control = (2 * x * Math.atan(x) - 2 * Math.log(Math.sqrt(1 + x * x)));

        System.out.println("Result: " + result);
        System.out.println("Control: " + control);
        System.out.print("Difference = " + (result-control));
    }
}
