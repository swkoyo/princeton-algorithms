package week1;

import edu.princeton.cs.algs4.StdOut;

public class QuickFind {
    private int[] id;

    public QuickFind(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public static void main(String[] args) {
        int n = 20;
        QuickFind qf = new QuickFind(n);

        qf.union(0, 4);
        qf.union(1, 8);
        qf.union(3, 1);

        StdOut.println("3 and 8 should be connected (true): " + qf.connected(3, 8));
        StdOut.println("4 and 9 not connected (false): " + qf.connected(4, 9));
    }
}
