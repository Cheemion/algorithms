package com.algorithms.graph;

import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.IndexMinPriorityQueue;
import com.algorithms.elementary.Stack;

/**
 * find the shortest path in digraph
 * can be cyclic
 * @author altro
 *
 */
public class DijkstraShortestPath {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private int source;
	private IndexMinPriorityQueue<Double> mpq;
	
	public DijkstraShortestPath(EdgeWeightedDigraph g, int s) {
		
		this.source = s;
		distTo = new double[g.vertices()];
		edgeTo = new DirectedEdge[g.vertices()];
		for (int i = 0; i < distTo.length; i++) 
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		
		mpq.insert(s, 0.0);
		start(g);
	}
	
	private void start(EdgeWeightedDigraph g) {
		while (!mpq.isEmpty()) {
			int v = mpq.delMin();
			for (DirectedEdge e : g.adj(v))
				relax(e);
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
			if (mpq.contains(w)) mpq.decreaseKey(w, distTo[w]);
			else mpq.insert(w, distTo[w]);
		}
	}
}
