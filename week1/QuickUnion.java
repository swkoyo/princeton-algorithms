package week1;

import edu.princeton.cs.algs4.StdOut;

public class QuickUnion {
    private int[] id;

    public QuickUnion(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int idx = root(p);
        id[idx] = root(q);
    }

    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    public static void main(String[] args) {
        int n = 20;
        QuickFind qf = new QuickFind(n);

        qf.union(0, 4);
        qf.union(1, 8);
        qf.union(3, 1);
        qf.union(13, 19);
        qf.union(13, 12);
        qf.union(1, 12);

        StdOut.println("1 and 19 should be connected (true): " + qf.connected(3, 8));
        StdOut.println("4 and 9 not connected (false): " + qf.connected(4, 9));
    }
}
