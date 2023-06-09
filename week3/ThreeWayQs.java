package week3;

public class ThreeWayQs {
    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = a[lo];
        int i = lo;
        while (i <= gt) {
            if (a[i] < v) {
                exch(a, lt++, i++);
            } else if (a[i] > v) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
