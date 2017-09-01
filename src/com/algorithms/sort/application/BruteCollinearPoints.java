package com.algorithms.sort.application;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	
	public static void main(String[] args) {
	    // read the n points from a file
		String path =  "C:\\Users\\altro\\Desktop\\algorithms\\data\\input8.txt";
	    In in = new In(path);
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
	    System.out.println("done");
	}
	
	private List<LineSegment> lineSegments = new ArrayList<>();

	public BruteCollinearPoints(Point[] points) {
		if (points == null)
			throw new IllegalAccessError();
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null)
				throw new IllegalAccessError();
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].compareTo(points[j]) == 0)
					throw new IllegalAccessError();
			}
		}
		/**
		 * N ^ 4
		 */
		for (int p = 0; p < points.length; p++) 
			for (int q = p + 1; q < points.length; q++) 
				for (int r = q + 1; r < points.length; r++) 
					for (int s = r + 1; s < points.length; s++) {
						if (points[p].slopeOrder().compare(points[r], points[s]) != 0) continue;
						if (points[p].slopeOrder().compare(points[r], points[q]) != 0) continue;
						if (points[p].slopeOrder().compare(points[s], points[q]) != 0) continue;
						lineSegments.add(getMostDistantLineSegment(points[p], points[q], points[r], points[s]));
					}
		
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < lineSegments.size(); j++) {
				LineSegment segment = lineSegments.get(j);
				if (points[i].slopeOrder().compare(segment.getP(), segment.getQ()) == 0) {
					lineSegments.set(j, getMostDistantLineSegment(points[i], segment.getP(), segment.getQ()));
				}
			}
		}
		
	}

	public int numberOfSegments() {
		return lineSegments.size();
	}

	public LineSegment[] segments() {
		return lineSegments.toArray(new LineSegment[lineSegments.size()]);
	}
	
	public static LineSegment getMostDistantLineSegment(Point ... points) {
		int p = 0;
		int q = 1;
		double distance = points[p].distanceTo(points[q]);
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (distance < points[i].distanceTo(points[j])) {
					p = i;
					q = j;
					distance = points[i].distanceTo(points[j]);
				}
			}
		}
		return new LineSegment(points[p], points[q]);
	}
}