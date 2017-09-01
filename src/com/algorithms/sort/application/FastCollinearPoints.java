package com.algorithms.sort.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algorithms.sort.MergeSort;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

//n^2
public class FastCollinearPoints {
	
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
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	    System.out.println("done");
	}
	
	
	
	
	
	
	private List<LineSegment> lineSegments = new ArrayList<>();
	private Double[] slope;
	private Map<Double,List<Point>> indexOfPoints; 
	
	public FastCollinearPoints(Point[] points) {
		int n = points.length;
		slope = new Double[n];
		for (int i = 0; i < n; i++) { //n
			indexOfPoints = new HashMap<>(); 
			for (int j = i + 1; j < n; j++) { //n
				slope[j] = points[i].slopeTo(points[j]);
				if (indexOfPoints.get(slope[j]) == null)
					indexOfPoints.put(slope[j], new ArrayList<>());
				indexOfPoints.get(slope[j]).add(points[j]);
			}
			for (List<Point> temp : indexOfPoints.values()) {
				if (temp.size() < 3) continue;
				lineSegments.add(BruteCollinearPoints.getMostDistantLineSegment(temp.toArray(new Point[temp.size()])));
			}
		}
	}

	public int numberOfSegments() {
		return lineSegments.size();
	}

	public LineSegment[] segments() {
		return lineSegments.toArray(new LineSegment[lineSegments.size()]);
	}
}