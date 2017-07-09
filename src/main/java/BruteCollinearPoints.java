import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        checkGivenPoints(points);
        List<LineSegment> lineSegments = new ArrayList<>();

        Point[] clone = Arrays.copyOf(points, points.length);
        Arrays.sort(clone);
//        System.out.println("Sorted");
//        System.out.println(Arrays.toString(clone));
//        System.out.println("***********");
        for (int p = 0; p < clone.length - 3; p++) {
            //System.out.println("point p: " + clone[p]);
            for (int q = p + 1; q < clone.length - 2; q++) {
                double slopeToPQ = clone[p].slopeTo(clone[q]);

                //System.out.println("point q: " + clone[q]);

                for (int r = q + 1; r < clone.length - 1; r++) {
                    //System.out.println("point r: " + clone[r]);
                    if (slopeToPQ != clone[p].slopeTo(clone[r])) {
                        continue;
                    }

                    for (int s = r + 1; s < clone.length; s++) {
                        //System.out.println("point s: " + clone[s]);
                        if (slopeToPQ == clone[p].slopeTo(clone[s])) {
//                            System.out.println("p->q: " + new LineSegment(clone[p], clone[q]));
//                            System.out.println("q->r: " + new LineSegment(clone[q], clone[r]));
//                            System.out.println("Line segment p->s: " + new LineSegment(clone[p], clone[s]));
                            lineSegments.add(new LineSegment(clone[p], clone[s]));

                        }
                    }
                }
            }
        }
        segments = new LineSegment[lineSegments.size()];
        lineSegments.toArray(segments);
    }

    private void checkGivenPoints(Point[] points) {
        if (null == points) {
            throw new IllegalArgumentException("Points can't be NULL");
        }
        for (int i = 0; i < points.length; i++) {
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
        LineSegment[] copy = new LineSegment[segments.length];
        System.arraycopy(segments, 0, copy, 0, segments.length);
        return copy;
    }

    public static void main(String[] args) {

        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(1, 1);
        Point[] points = new Point[]{p1, p2, p3};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        bruteCollinearPoints.numberOfSegments();
    }
}
