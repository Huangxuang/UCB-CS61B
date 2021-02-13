package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    int numberOfExperiment;
    double[] testResults;
    int size;

    /** 1:Perform total T times of experiment
     * 2: Store the results to a array
     * */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 1) {
            throw new IllegalArgumentException("Size must be larger than 0 !");
        }
        numberOfExperiment = T;
        size = N;
        testResults = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation per = pf.make(N);
            testResults[i] = performExperiment(per);
        }

    }

    /** 1: Random choose a x, y from [0,N) and open it
     * 2: keep opening until percolates
     * 3: calculate the fraction of this experiment
     * */
    private double performExperiment(Percolation per) {
        while (!per.percolates()) {
            int col = StdRandom.uniform(size);
            int row = StdRandom.uniform(size);
            per.open(row, col);
        }
        double totalOpen = per.numberOfOpenSites();
        System.out.println(totalOpen);
        double fraction = totalOpen / (size * size);
        System.out.println(fraction);
        return fraction;

    }


    public double mean() {
        return StdStats.mean(testResults);
    }

    public double stddev() {
        return StdStats.stddev(testResults);
    }

    public double confidenceLow() {
        return (mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(numberOfExperiment));
    }

    public double confidenceHigh() {
        return (mean() + 1.96 * Math.sqrt(stddev()) / Math.sqrt(numberOfExperiment));
    }


    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats per = new PercolationStats(200, 1000, pf);
        System.out.println(per.mean());
       // per.open(0,1);
        //per.open(1,1);
        System.out.println("unit ");
        //System.out.println (per.isFull(1,1));
    }


}
