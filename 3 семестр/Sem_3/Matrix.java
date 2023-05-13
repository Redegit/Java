package Sem_3;

public class Matrix {

    public static void main(String[] args) {
        int size = 10;

        int[][] m = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                m[i][j] = (int) (Math.random() * 10);
//                m[i][j] = (i + 1) * (j + 1);
            }
        printMatrix(m);
        m = transpose(m);
        System.out.println("-------------------------------------");
        printMatrix(m);
    }

    public static void printMatrix(int[][] m) {
        for (int[] row : m) {
            for (int j: row)
                System.out.print(j + "\t");
            System.out.println();
        }
    }

    public static int[][] transpose(int[][] m) {
        for (int i = 1; i < m.length - 1; i++)
            for (int j = i + 1; j < m[i].length; j++) {
                int a = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = a;
            }
        return m;
    }
}
