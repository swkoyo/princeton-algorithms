package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;

public class PercolationStats {
    private double[] thresholds;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        this.thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            int openSites = 0;
            while (!perc.percolates()) {
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;
                perc.open(row, col);
                openSites = perc.numberOfOpenSites();
            }
            thresholds[i] = (double) openSites / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(this.thresholds);
    }

    public double stddev() {
        return StdStats.stddev(this.thresholds);
    }

    public double confidenceLo() {
        double m = this.mean();
        double sd = this.stddev();
        return m - ((1.96 * sd)) / Math.sqrt(this.thresholds.length);
    }

    public double confidenceHi() {
        double m = this.mean();
        double sd = this.stddev();
        return m + ((1.96 * sd)) / Math.sqrt(this.thresholds.length);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        double mean = ps.mean();
        double stddev = ps.stddev();
        double cl = ps.confidenceLo();
        double ch = ps.confidenceHi();

        System.out.printf("mean                    = %f\n", mean);
        System.out.printf("stddev                  = %f\n", stddev);
        System.out.printf("95%% confidence interval = [%f, %f]\n", cl, ch);
    }
}
