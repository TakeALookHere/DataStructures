package com.miskevich.datastructures.algorithms.lecture1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int range;
    private int[] grid;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private static final int EMPTY_OPEN = 1;
    private int openCount = 0;
    private int topSiteIndex;
    private int bottomSiteIndex;
    // and which sites are connected to which other sites

    // create range-by-range grid, with all sites blocked
    public Percolation(int range) {
        if (range <= 0) {
            throw new IllegalArgumentException("Number of sites in the grid has to be > 0");
        }

        this.range = range;
        this.grid = new int[(int) (Math.pow(range, 2))];
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(grid.length + 2);
        this.topSiteIndex = (int) Math.pow(range, 2);
        this.bottomSiteIndex = (int) Math.pow(range, 2) + 1;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        //First, it should validate the indices of the site that it receives
        validateIndices(row, col);
        if (!isOpen(row, col)) {
            //Second, it should somehow mark the site as open
            int siteIndex = xyToID(row, col);
            grid[siteIndex] = EMPTY_OPEN;
            openCount++;

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
        if (row == 1) {
            weightedQuickUnionUF.union(topSiteIndex, siteIndex);
        }
        if (row == range) {
            weightedQuickUnionUF.union(bottomSiteIndex, siteIndex);
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        int siteIndex = xyToID(row, col);
        return grid[siteIndex] == EMPTY_OPEN;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        int siteIndex = xyToID(row, col);
        return weightedQuickUnionUF.connected(topSiteIndex, siteIndex);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(topSiteIndex, bottomSiteIndex);
    }

    private int xyToID(int row, int col) {
        return ((row - 1) * range) + (col - 1);
    }

    private void validateIndices(int row, int col) {
        if (row < 1 || row > range || col < 1 || col > range) {
            throw new IllegalArgumentException("Indices must be in a range " + range);
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(1, 3);
        percolation.open(2, 3);
        percolation.open(3, 3);
        percolation.open(4, 3);

        System.out.println(percolation.weightedQuickUnionUF.connected(2, 7));
        System.out.println(percolation.percolates());
    }
}
