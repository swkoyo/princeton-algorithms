package week4;

public class UnorderedMaxPQ {
    private int[] pq;
    private int N;

    public UnorderedMaxPQ(int capacity) {
        pq = new int[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(int x) {
        pq[N++] = x;
    }

    public int delMax() {
        int max = 0;
        for (int i = 1; i < N; i++) {
            if (pq[max] < pq[i]) {
                max = i;
            }
        }
        int swap = pq[max];
        pq[max] = pq[N - 1];
        pq[N - 1] = swap;
        return pq[--N];
    }
}
