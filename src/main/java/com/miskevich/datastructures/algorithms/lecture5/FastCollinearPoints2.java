package com.miskevich.datastructures.algorithms.lecture5;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints2 {

    private LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints2(Point[] points) {
        List<LineSegment> lineSegments = new ArrayList<>();
        checkGivenPoints(points);
        Point[] clone = Arrays.copyOf(points, points.length);
        Arrays.sort(clone);
        for (int p = 0; p < clone.length; p++) {
            Arrays.sort(clone, clone[p].slopeOrder());
            //if(p == clone.length - 1){
            System.out.println("Sorted:");
            System.out.println(Arrays.toString(clone));
            System.out.println("******************");
            //}

            List<Point> collinearPoints = new ArrayList<>();
            for (int q = 0; q < clone.length - 1; q++) {
                if (p == q) {
                    continue;
                }
                System.out.println("point p: " + clone[p]);
                System.out.println("point q: " + clone[q]);
                if (collinearPoints.isEmpty()) {
                    collinearPoints.add(clone[q]);
                    System.out.println("Colin q: ");
                } else if (clone[p].slopeTo(clone[q - 1]) == clone[p].slopeTo(clone[q])) {
                    System.out.println("clone[q - 1]: " + clone[q - 1]);
                    collinearPoints.add(clone[q]);
                    System.out.println("collinearPoints: " + collinearPoints);
                    if (collinearPoints.size() > 2) {
                        lineSegments.add(new LineSegment(clone[p], clone[q]));
                    }
                }
//                if (collinearPoints.size() > 2) {
//                    lineSegments.add(new com.miskevich.datastructures.algorithms.lecture5.LineSegment(clone[p], clone[q]));
//                }
            }
            System.out.println("lineSegments: " + lineSegments);
        }

        segments = new LineSegment[lineSegments.size()];
        lineSegments.toArray(segments);
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] copy = new LineSegment[segments.length];
        System.arraycopy(segments, 0, copy, 0, segments.length);
        return copy;
    }

    private void checkGivenPoints(Point[] points) {
        if (null == points) {
            throw new IllegalArgumentException("Points can't be NULL");
        }
        for (int i = 0; i < points.length; i++) {
            if (null == points[i]) {
                throw new IllegalArgumentException("com.miskevich.datastructures.algorithms.lecture5.Point is NULL");
            }
            for (int j = i + 1; j < points.length; j++) {
                if (null == points[j]) {
                    throw new IllegalArgumentException("com.miskevich.datastructures.algorithms.lecture5.Point is NULL");
                }
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicates in the given points");
                }
            }
        }
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }


        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        FastCollinearPoints2 collinear = new FastCollinearPoints2(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
