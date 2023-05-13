package Classwork.Cycles;

import java.util.Scanner;

public class Sqroot_while {
    public static void main(String[] args) {
        System.out.print("Enter x = ");
        Scanner sc = new Scanner(System.in);
        double x = sc.nextDouble();
        double a0, a1, eps;

        a0 = x / 2;
        a1 = x / 3;
        eps = 1e-9;

        while (Math.abs(a0-a1)>eps) {
            a0 = a1;
            a1 = (a1 + x/a1)/2;
        }
        System.out.println(a1);
        System.out.println(Math.sqrt(x));
    }
}
