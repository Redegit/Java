package Sem_3;

public class Find_rep_el {

    public static void main(String[] args) {
        int length = 10;

        int[] a = new int[length];
        for (int i = 0; i < length; i++)
            a[i] = (int) (Math.random() * 10);

        print(a);

        int[] nm = new int[length];
        for (int i = 0; i < length; i++)
            nm[a[i]]++;

        print(nm);

        int count = 0;
        for (int i = 0; i < length; i++)
            if (nm[i] >= 2)
                count++;

        int[] res = new int[count];
        int ind = 0;
        for (int i = 0; i < length; i++)
            if (nm[i] >= 2) res[ind++] = i;

        print(res);

    }


    public static void print(int[] m) {
        for (int p : m) System.out.print(p + " ");
        System.out.println();
    }

}