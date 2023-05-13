package Classwork;

import java.util.Scanner;

public class CelsToFar {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter temp. in C: ");
        double temp_c=sc.nextDouble();
        double temp_f=9.0/5*temp_c+32;
        System.out.printf("Temp. in F: %.2f", temp_f);

    }
}
