package week3;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private LineSegment[] segments;
    private int n;
    private int segLen = 0;
    private int segCap = 0;

    public FastCollinearPoints(Point[] points) {
        validatePoints(points);

        segments = new LineSegment[n];
        Point[] pointsClone = points.clone();
        Arrays.sort(pointsClone);

        for (int i = 0; i < n; i++) {
            collinearPoints(pointsClone, pointsClone[i]);
        }
    }

    private void collinearPoints(Point[] points, Point origin) {
        Point[] slopePoints = points.clone();
        Arrays.sort(slopePoints, origin.slopeOrder());

        int idx = 1;

        while (idx < n) {
            int startIdx = idx;
            int j = idx + 1;
            int count = 2;
            double refSlope = origin.slopeTo(slopePoints[idx]);
            while (j < n && refSlope == origin.slopeTo(slopePoints[j])) {
                count++;
                j++;
            }
            if (count >= 4) {
                validateSegment(slopePoints, startIdx, count, origin);
            }
            idx = j;
        }
    }

    private void validateSegment(Point[] points, int startIdx, int count, Point origin) {
        Point[] colPoints = new Point[count];
        colPoints[0] = origin;
        for (int i = 1; i < count; i++) {
            colPoints[i] = points[startIdx + i - 1];
        }
        Arrays.sort(colPoints);
        if (colPoints[0].compareTo(origin) == 0) {
            incrementSegCap();
            segments[segLen++] = new LineSegment(colPoints[0], colPoints[count - 1]);
        }
    }

    private void incrementSegCap() {
        LineSegment[] temp = new LineSegment[segCap + 1];
        for (int i = 0; i < segLen; i++) {
            temp[i] = segments[i];
        }
        segments = temp;
        segCap++;
    }

    private void validatePoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        n = points.length;

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
    }

    public int numberOfSegments() {
        return segLen;
    }

    public LineSegment[] segments() {
        return segments;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
