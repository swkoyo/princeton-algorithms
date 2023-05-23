package week4;

public class MaxPQ {
    private int[] pq;
    private int N;

    public MaxPQ(int capacity) {
        pq = new int[capacity + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(int key) {
        pq[++N] = key;
        swim(N);
    }

    public int delMax() {
        int max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = 0;
        return max;
    }

    private void swim(int k) {
        while (k > 1 && pq[k / 2] < pq[k]) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && pq[j] < pq[j + 1]) {
                j++;
            }
            if (pq[k] > pq[j]) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
}
