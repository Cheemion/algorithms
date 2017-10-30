package com.algorithms.graph;

import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.Stack;

/**
 * can not have negative cyclic
 * should see @BellmanFordQueueBasedShortestPath this is more efficient
 * @author altro
 *
 */
public class BellmanFordShortestPath {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private int source;
	
	public static void main(String[] args) {
	}
	
	
	public BellmanFordShortestPath(EdgeWeightedDigraph g, int s) {
		
		this.source = s;
		distTo = new double[g.vertices()];
		edgeTo = new DirectedEdge[g.vertices()];
		for (int i = 0; i < distTo.length; i++) 
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		
		for (int pass = 0; pass < g.vertices(); pass++) 
			for (int v = 0; v < g.vertices(); v++) 
				for (DirectedEdge e : g.adj(v))
					relax(e);
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
