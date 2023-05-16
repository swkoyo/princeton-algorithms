package week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class SelectionSort {
    public static void sort(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int idx = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[idx])) {
                    idx = j;
                }
            }
            exch(a, i, idx);
        }
    }

    private static boolean less(int v, int w) {
        return v < w;
    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        int n = 30;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(200);
        }

        for (int i : a) {
            StdOut.print(i + " ");
        }

        StdOut.println();
        SelectionSort.sort(a);

        for (int i : a) {
            StdOut.print(i + " ");
        }

        StdOut.println();
    }
}