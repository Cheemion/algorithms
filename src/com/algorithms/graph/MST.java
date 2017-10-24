package com.algorithms.graph;
/**
 * Minimum spanning tree
 * @author altro
 *
 */
public interface MST {
	
	Iterable<Edge> edges(); 
	
	default double weight() {
		double allWeight = 0;
		for (Edge edge : edges())
			allWeight += edge.weight();
		return allWeight;
	}
}
