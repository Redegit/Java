public class HW2_Presnukhin_PI21_2_Var24 {

    public static void main(String[] args) {
        int length = 10;

        int[] a = new int[length];
        int minv = Integer.MAX_VALUE;
        int maxv = Integer.MIN_VALUE;
        int i_min = 0;
        int i_max = 0;

        for (int i = 0; i < length; i++) {
            a[i] = (int) (Math.random() * 10);
            if (a[i] < minv) {
                minv = a[i];
                i_min = i;
            }
            if (a[i] > maxv) {
                maxv = a[i];
                i_max = i;
            }
        }

        print(a);

        int left, right;
        if (i_min < i_max) {
            left = i_min;
            right = i_max;
        }
        else {
            left = i_max;
            right = i_min;
        }

        int s = 0;
        for (int i = left + 1; i < right; i++) {
            s += a[i];
        }
        System.out.println("Sum of nums between " + minv + " and " + maxv + ": " + s);
    }
    public static void print(int[] m) {
        for (int p : m) System.out.print(p + " ");
        System.out.println();
    }
}
