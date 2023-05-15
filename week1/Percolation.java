package week1;

import java.util.Arrays;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] grid;
    private int n;
    private int openSites = 0;
    private int area;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Invalid grid size given");
        }
        n = N;
        area = n * n;
        grid = new boolean[area];
        Arrays.fill(grid, false);
        uf = new WeightedQuickUnionUF(area + 2);
    }

    public void open(int row, int col) {
        if (!this.isOpen(row, col)) {
            openSites++;
            int idx = this.convertCoorToIdx(row, col);
            grid[idx] = true;
            if (idx < n) {
                this.uf.union(idx + 1, 0);
            } else if (idx >= this.area - n) {
                this.uf.union(idx + 1, this.area + 1);
            }
            this.connectAdjSites(row, col);
        }
    }

    public boolean isOpen(int row, int col) {
        int idx = this.convertCoorToIdx(row, col);
        return grid[idx];
    }

    public boolean isFull(int row, int col) {
        int idx = this.convertCoorToIdx(row, col);
        return this.uf.find(idx + 1) == this.uf.find(0);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return this.uf.find(this.area + 1) == this.uf.find(0);
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(5);

        // Not percolated when all are closed
        System.out.println(perc.percolates());

        // Open bl corner
        perc.open(5, 1);
        System.out.println(perc.percolates());

        perc.open(2, 3);
        System.out.println(perc.percolates());

        perc.open(3, 3);
        System.out.println(perc.percolates());

        perc.open(1, 4);
        System.out.println(perc.percolates());

        perc.open(4, 2);
        System.out.println(perc.percolates());

        perc.open(2, 4);
        System.out.println(perc.percolates());

        perc.open(4, 3);
        System.out.println(perc.percolates());

        perc.open(4, 1);
        System.out.println(perc.percolates());
    }

    private void connectAdjSites(int row, int col) {
        int idx = this.convertCoorToIdx(row, col);
        if (row > 1) {
            int lIdx = this.convertCoorToIdx(row - 1, col);
            if (grid[lIdx]) {
                this.uf.union(idx + 1, lIdx + 1);
            }
        }

        if (row < n) {
            int rIdx = this.convertCoorToIdx(row + 1, col);
            if (grid[rIdx]) {
                this.uf.union(idx + 1, rIdx + 1);
            }
        }

        if (col > 1) {
            int tIdx = this.convertCoorToIdx(row, col - 1);
            if (grid[tIdx]) {
                this.uf.union(idx + 1, tIdx + 1);
            }
        }

        if (col < n) {
            int bIdx = this.convertCoorToIdx(row, col + 1);
            if (grid[bIdx]) {
                this.uf.union(idx + 1, bIdx + 1);
            }
        }
    }

    private int convertCoorToIdx(int row, int col) {
        this.checkRange(row, col);
        // Subtracting col and row by 1 because they are being accessed based on 1 index being start
        return (col - 1) + ((row - 1) * n);
    }

    private void checkRange(int row, int col) {
        if (row - 1 < 0 || row - 1 >= n || col - 1 < 0 || col - 1 >= n) {
            throw new IllegalArgumentException("Invalid range given");
        }
    }
}