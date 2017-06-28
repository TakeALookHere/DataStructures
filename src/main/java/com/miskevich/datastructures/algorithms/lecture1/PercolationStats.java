package com.miskevich.datastructures.algorithms.lecture1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private double[] percolationThresholdMean;
    private static final double CONST = 1.96;
    private final int trails;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int range, int trials) {
        if (range <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Range and trails have to be > 0");
        }
        this.trails = trials;
        int trialsCounter = 0;
        this.percolationThresholdMean = new double[trials];
        do {
            double percolationThreshold = startPercolate(range);
            percolationThresholdMean[trialsCounter] = percolationThreshold;
            trialsCounter++;
        }
        while (trialsCounter < trials);
    }

    private double startPercolate(int range) {
        Percolation percolation = new Percolation(range);
        while (!percolation.percolates()) {
            int randomRow = StdRandom.uniform(1, range + 1);
            int randomCol = StdRandom.uniform(1, range + 1);
            percolation.open(randomRow, randomCol);
        }
        return percolation.numberOfOpenSites() / Math.pow(range, 2);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percolationThresholdMean);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolationThresholdMean);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - CONST * stddev()) / Math.sqrt(trails);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + CONST * stddev()) / Math.sqrt(trails);
    }

    // test client (described below)
    public static void main(String[] args) {
        int range = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);
        System.out.println(range + ":" + trails);
        PercolationStats percolationStats = new PercolationStats(range, trails);
        System.out.println("mean  = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
