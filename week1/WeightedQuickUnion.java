package week1;

import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnion {
    private int[] id;
    private int[] sz;

    public WeightedQuickUnion(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j) {
            return;
        }

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
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
