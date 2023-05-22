
package week3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class BottomUpMergeSort {
    private static int[] aux;

    private static void merge(int[] a, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 2;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
            }
        }
    }

    public static void sort(int[] a) {
        // aux = new int[a.length];
        // int i = 2;
        // while (i <= a.length / 2) {
        //     for (int j = 0; j < a.length; j += i) {
        //         int mid = j + i / 2;
        //         merge(a, j, mid, j + i);
        //     }
        //     i *= 2;
        // }
        // merge(a, 0, a.length / 2 - 1, a.length - 1);

        int N = a.length;
        aux = new int[N];

        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz -1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[] a = new int[n];

        for (int i = 1; i < n; i++) {
            a[i] = StdRandom.uniformInt(301);
            StdOut.print(a[i] + ", ");
        }
        StdOut.println();

        MergeSort.sort(a);

        for (int x : a) {
            StdOut.print(x + ", ");
        }
        StdOut.println();
    }
}

