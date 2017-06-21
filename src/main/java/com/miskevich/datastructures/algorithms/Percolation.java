package com.miskevich.datastructures.algorithms;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {

    private int range;
    private int[] grid;
    private int size;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    //which sites are open,
    // and which sites are connected to which other sites

    // create range-by-range grid, with all sites blocked
    public Percolation(int range) {
        if (range <= 0) {
            throw new IllegalArgumentException("Number of sites in the grid has to be > 0");
        }

        this.range = range;
        this.grid = new int[(int) (Math.pow(range, 2))];
        this.size = grid.length;
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(size);
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        //First, it should validate the indices of the site that it receives
        validateIndices(row, col);
        if (!isOpen(row, col)) {
            //Second, it should somehow mark the site as open
            int siteIndex = xyToID(row, col);
            grid[siteIndex] = 1;

            //Third, it should perform some sequence of WeightedQuickUnionUF operations that links the site in question to its open neighbors
            connectNeighbours(row, col, siteIndex);
        }
    }

    private void connectIfOpen(int row, int col, int siteIndex, int neighbour) {
        if (isOpen(row, col)) {
            if (!weightedQuickUnionUF.connected(siteIndex, neighbour)) {
                weightedQuickUnionUF.union(siteIndex, neighbour);
            }
        }
    }

    private void connectNeighbours(int row, int col, int siteIndex) {
        if (row != 1) {
            int top = xyToID(row - 1, col);
            connectIfOpen(row - 1, col, siteIndex, top);
        }
        if (row != range) {
            int bottom = xyToID(row + 1, col);
            connectIfOpen(row + 1, col, siteIndex, bottom);
        }
        if (col != 1) {
            int left = xyToID(row, col - 1);
            connectIfOpen(row, col - 1, siteIndex, left);
        }
        if (col != range) {
            int right = xyToID(row, col + 1);
            connectIfOpen(row, col + 1, siteIndex, right);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        int siteIndex = xyToID(row, col);
        if (grid[siteIndex] == 1) {
            return true;
        }
        return false;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndices(row, col);

        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    public int size() {
        return size;
    }

    private int xyToID(int row, int col) {
        return ((row - 1) * range) + (col - 1);
    }

    private void validateIndices(int row, int col) {
        if (row < 1 || row > range || col < 1 || col > range) {
            throw new IndexOutOfBoundsException("Indices must be in a range " + range);
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(1, 1);
        percolation.open(1, 2);

        System.out.println(percolation.weightedQuickUnionUF.connected(0, 1));
        System.out.println(Arrays.toString(percolation.grid));
    }
}
