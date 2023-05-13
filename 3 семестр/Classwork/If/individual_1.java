package Classwork.If;

import java.util.Scanner;

public class individual_1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("input x >> ");
        double x=sc.nextDouble();
        System.out.print("input y >> ");
        double y=sc.nextDouble();

        if (x*y > 30) {
            double result1 = 2/Math.tan(x);
            double result2 = Math.tan(x);
            System.out.println(result1);
            System.out.print(result2);
        }
        else {
            double result = x/2;
            System.out.print(result);
        }

    }
}
