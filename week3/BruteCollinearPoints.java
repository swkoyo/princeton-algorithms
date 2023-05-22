package week3;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    private LineSegment[] segments;
    private int segCap = 0;
    private int segLen = 0;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        int n = points.length;

        for (int i = 0; i < n - 1; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            for (int j = i + 1; j < n; j++) {
                if (points[j] == null || points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }

        Point[] pointsClone = points.clone();
        Arrays.sort(pointsClone);

        segments = new LineSegment[segCap];

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                double slopeIJ = pointsClone[i].slopeTo(pointsClone[j]);
                for (int k = j + 1; k < n - 1; k++) {
                    double slopeJK = pointsClone[j].slopeTo(pointsClone[k]);
                    if (slopeIJ == slopeJK) {
                        for (int l = k + 1; l < n; l++) {
                            double slopeKL = pointsClone[k].slopeTo(pointsClone[l]);
                            if (slopeJK == slopeKL) {
                                incrementSegCap();
                                segments[segLen++] = new LineSegment(pointsClone[i], pointsClone[l]);
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return segLen;
    }

    public LineSegment[] segments() {
        return segments;
    }

    private void incrementSegCap() {
        LineSegment[] temp = new LineSegment[segCap + 1];
        for (int i = 0; i < segLen; i++) {
            temp[i] = segments[i];
        }
        segments = temp;
        segCap++;
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

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
