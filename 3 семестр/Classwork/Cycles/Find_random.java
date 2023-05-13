package Classwork.Cycles;

import java.util.Scanner;

public class Find_random {
    public static void main(String[] args) {
        // задаем случайное значение от 1 до 100
        Scanner sc=new Scanner(System.in);
        int x = (int) (Math.random()*100);
//        System.out.print(x);
        int top = 100;
        int bot = 1;
        while (true) {
            System.out.print("Enter number from " + bot + " to " + top + " >> ");
            int n = sc.nextInt();
            if (n == x) {
                System.out.print("Correct!");
                break;
            }
            else if (n < x) {
                System.out.println("Too small");
                bot = n;
            }
            else {
                System.out.println("Too big");
                top = n;
            }
        }
    }
}
