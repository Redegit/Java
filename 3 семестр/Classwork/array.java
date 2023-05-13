import java.util.Arrays;

public class array {
    public static void main(String[] args) {
        int[] m = new int[10];
        init(m);
        int[] b = m.clone();
        Arrays.sort(b);
        print(m);
        System.out.println("Min = " + min(m));
        buble(m);
        print(m);
        print(b);
    }
    public static void init(int[] m) {
        for(int i = 0; i < m.length; i++) {
            m[i] = (int)(Math.random() * 100);
        }
    }
    public static void print(int[] m) {
        for(int p:m)System.out.print(p + " ");
        System.out.println();
    }
    public static int min(int[] m) {
        int  minv = Integer.MAX_VALUE;
        for(int p : m) {
            if (p < minv) minv = p;
        }
        return minv;
    }
    public static void buble(int[] m) {
        boolean f = true;
        while (f) {
            f = false;
            for(int i = 0; i < m.length - 1; i++) {
                if(m[i] > m[i + 1]) {
                    int t = m[i];
                    m[i] = m[i + 1];
                    m[i + 1] = t;
                    f = true;
                }
            }
        }
    }
}
