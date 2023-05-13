package homework;

import java.util.Scanner;

public class HW3_Presnukhin_PI21_2_Var24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter matrix size = ");
        int size = sc.nextInt();
        int[][] m = new int[size][size];

        int n_negative = 0;
        int sum_negative = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = (int) (Math.random()*20 - 10);
                m[i][j] = value;
                if (i + j >= size) {
                    if (value < 0) {
                        n_negative++;
                        sum_negative += value;
                    }
                }
            }
        }
        print_matrix(m);
        System.out.println("Number of negtives: " + n_negative);
        System.out.println("Sum of negtives: " + sum_negative);

    }

    public static void print_matrix(int[][] m) {
        for (int[] i: m) {
            for (int j: i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }
}
