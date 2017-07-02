import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;

public class FastCollinearPoints {

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        BruteCollinearPoints.checkGivenPoints(points);
        for (int i = 0; i < points.length; i++) {
            Arrays.sort(points, points[i].slopeOrder());
            System.out.println(Arrays.toString(points));
        }
    }

//    // the number of line segments
//    public int numberOfSegments() {
//
//    }
//
//    // the line segments
//    public LineSegment[] segments() {
//
//    }

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

        FastCollinearPoints collinear = new FastCollinearPoints(points);
    }
}
