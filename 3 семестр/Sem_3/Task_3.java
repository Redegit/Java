package Sem_3;

import java.util.Scanner;

public class Task_3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter matrix size >> ");
        int size = sc.nextInt();

        int[][] m = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                m[i][j] = (int) (Math.random() * 10);
            }

        printMatrix(m);

        System.out.println("---------------------");
        for (int[] row : m) {
            int min = Integer.MAX_VALUE;
            int ind = 0;
            for (int i = 0; i < size; i++) {
                if (row[i] < min) {
                    min = row[i];
                    ind = i;
                }
            }
            for (int i = 0; i < size; i++) {
                row[i] += ind;
            }
            System.out.println("Index of min = " + ind);

        }
        System.out.println("---------------------");
        printMatrix(m);

    }

    public static void printMatrix(int[][] m) {
        for (int[] row : m) {
            for (int j: row)
                System.out.print(j + "\t");
            System.out.println();
        }
    }
}
