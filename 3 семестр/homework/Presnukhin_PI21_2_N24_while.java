package homework;

import java.util.Scanner;

public class Presnukhin_PI21_2_N24_while {
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

        double eps = 1e-10;
        int sign = 1;
        double result = 0;
        int i = 1;
        double tmp = x;

        while (Math.abs(tmp)>eps) {
            tmp = sign * pow(x, 2*i) / i / (2 * i - 1);
            result += tmp;
            sign *= -1;
            i++;
        }

        double control = (2 * x * Math.atan(x) - 2 * Math.log(Math.sqrt(1 + x * x)));

        System.out.println("Result: " + result);
        System.out.println("Control: " + control);
    }
}
