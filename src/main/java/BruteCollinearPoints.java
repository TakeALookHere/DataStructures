import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        checkGivenPoints(points);
        //Arrays.sort(points);
        List<LineSegment> lineSegments = new ArrayList<>();
        for (int p = 0; p < points.length; p++) {
            for (int q = p + 1; q < points.length; q++) {
                double slopeToPQ = points[p].slopeTo(points[q]);
                System.out.println("point p: " + points[p]);
                System.out.println("point q: " + points[q]);
                System.out.println("slopePQ: " + slopeToPQ);
                for (int r = q + 1; r < points.length; r++) {
                    System.out.println("point p: " + points[p]);
                    System.out.println("point r: " + points[r]);
                    System.out.println("slopePR: " + points[p].slopeTo(points[r]));
                    if (slopeToPQ != points[p].slopeTo(points[r])) {
                        break;
                    }
                    for (int s = r + 1; s < points.length; s++) {
                        System.out.println("point p: " + points[p]);
                        System.out.println("point s: " + points[s]);
                        System.out.println("slopePS: " + points[p].slopeTo(points[s]));
                        if (slopeToPQ == points[p].slopeTo(points[s])) {
                            lineSegments.add(new LineSegment(points[p], points[s]));
                            System.out.println("Line segment: " + new LineSegment(points[p], points[s]));
                        }
                    }
                }
            }
        }

//        for (int p = 0; p < points.length - 3; p++) {
//            for (int q = p + 1; q < points.length - 2; q++) {
//                double slopeToPQ = points[p].slopeTo(points[q]);
//                System.out.println("point p: " + points[p]);
//                System.out.println("point q: " + points[q]);
//                System.out.println("slopePQ: " + slopeToPQ);
//                for (int r = q + 1; r < points.length - 1; r++) {
//                    System.out.println("point p: " + points[p]);
//                    System.out.println("point r: " + points[r]);
//                    System.out.println("slopePR: " + points[p].slopeTo(points[r]));
//                    if (slopeToPQ != points[p].slopeTo(points[r])) {
//                        break;
//                    }
//                    for (int s = r + 1; s < points.length; s++) {
//                        System.out.println("point p: " + points[p]);
//                        System.out.println("point s: " + points[s]);
//                        System.out.println("slopePS: " + points[p].slopeTo(points[s]));
//                        if (slopeToPQ == points[p].slopeTo(points[s])) {
//                            lineSegments.add(new LineSegment(points[p], points[s]));
//                            System.out.println("Line segment: " + new LineSegment(points[p], points[s]));
//                        }
//                    }
//                }
//            }
//        }
        segments = new LineSegment[lineSegments.size()];
        lineSegments.toArray(segments);
    }

    static void checkGivenPoints(Point[] points) {
        if (null == points) {
            throw new IllegalArgumentException("Points can't be NULL");
        }
        for (int i = 0; i < points.length - 1; i++) {
            if (null == points[i]) {
                throw new IllegalArgumentException("Point is NULL");
            }
            for (int j = i + 1; j < points.length; j++) {
                if (null == points[j]) {
                    throw new IllegalArgumentException("Point is NULL");
                }
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicates in the given points");
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }
}
