package Classwork;

import java.util.Scanner;

public class RadToGrad {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter value = ");
        double rad=sc.nextDouble();
        double pi=Math.PI;
        System.out.println(Math.floor(rad/pi*180)); // пол
        System.out.println(Math.ceil(rad/pi*180)); // потолок
        System.out.println(Math.round(rad/pi*180)); // округление
        System.out.println(rad/pi*180);
    }
}
