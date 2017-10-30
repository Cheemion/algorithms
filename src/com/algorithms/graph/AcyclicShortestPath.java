package com.algorithms.graph;

import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.IndexMinPriorityQueue;
import com.algorithms.elementary.Stack;

/**
 * find the shortest path
 * should not be cyclic
 * 
 * in order to get the longest path 
 * the thing we only need to do is negate all the weights in the graph.
 * @author altro
 *
 */
public class AcyclicShortestPath {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private int source;	
	
	public AcyclicShortestPath(EdgeWeightedDigraph g, int s) {
		this.source = s;
		distTo = new double[g.vertices()];
		edgeTo = new DirectedEdge[g.vertices()];
		for (int i = 0; i < distTo.length; i++) 
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		
		EdgeWeightedTopologiclOrder ewto = new EdgeWeightedTopologiclOrder(g);
		if (ewto.isCycle()) throw new RuntimeException("EdgeWeightedDigraph is a cyclic");
		
		for (int v : ewto.topologiclOrder()) 
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
