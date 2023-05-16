package week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class KnuthShuffle {
    public static void shuffle(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = StdRandom.uniformInt(i + 1);
            if (i != r) {
                int swap = a[i];
                a[i] = a[r];
                a[r] = swap;
            }
        }
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
        KnuthShuffle.shuffle(a);

        for (int i : a) {
            StdOut.print(i + " ");
        }

        StdOut.println();
    }
}
