package week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShellSort {
    public static void sort(int[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - 1);
                }
            }
            h = h / 3;
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


