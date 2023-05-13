package Classwork;

import java.util.Scanner;

public class TriangleArea {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("a = ");
        int a=sc.nextInt();
        System.out.print("b = ");
        int b=sc.nextInt();
        System.out.print("c = ");
        int c=sc.nextInt();

        double p=(a+b+c)/2.0;
        double area = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        System.out.println(area);

    }
}
