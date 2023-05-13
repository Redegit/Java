package Classwork.Cycles;

import java.util.Scanner;

public class Sin_while {
    public static void main(String[] args) {
        System.out.print("Enter value: ");
        Scanner sc=new Scanner(System.in);
        double x = sc.nextDouble();
        double s, t, eps;
        int k =1;
        s = x;
        t = x;
        eps = 1e-10;
        while (Math.abs(t)>eps) {
            t *= -x*x/(2*k+1);
            s += t;
            k++;
        }
        System.out.println(s);
        System.out.print(Math.sin(x));
    }


}
