package com.algorithms.graph;

import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.Queue;
import com.algorithms.elementary.Stack;

/**
 * can not have negative cyclic
 * @author altro
 *
 */
public class BellmanFordQueueBasedShortestPath {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private int source;
	private Queue<Integer> changed;
	
	public static void main(String[] args) {
		
	}
	
	
	public BellmanFordQueueBasedShortestPath(EdgeWeightedDigraph g, int s) {
		this.source = s;
		distTo = new double[g.vertices()];
		edgeTo = new DirectedEdge[g.vertices()];
		changed = new ArrayQueue<>();
		
		for (int i = 0; i < distTo.length; i++) 
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		changed.enqueue(s);
		
		start(g);
	}
	

	private void start(EdgeWeightedDigraph g) {
		while (!changed.isEmpty()) {
			
		}
	}


	public boolean hasPathTo(int v) {
		return distTo[v] != Double.POSITIVE_INFINITY;
	}
	
	public double distTo(int v) {
		return distTo[v];
	}
	
	public Iterable<DirectedEdge> pathTo(int v) {
		Stack<DirectedEdge> path = new ArrayStack<>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
			path.push(e);
		return path;
	}
	
	private void relax(DirectedEdge e) {
		int v = e.from(), w = e.to();
		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}
	}
}
