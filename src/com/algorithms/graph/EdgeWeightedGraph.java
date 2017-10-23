package com.algorithms.graph;

import java.util.HashSet;
import java.util.Set;

import com.algorithms.elementary.ArrayBag;
import com.algorithms.elementary.Bag;

public class EdgeWeightedGraph {
	private final int size; // number of vertices
	private int edgeNum;
	private Bag<Edge>[] adj;
	
	public EdgeWeightedGraph(int size) {
		this.size = size;
		adj = new ArrayBag[size];
		for (int i = 0; i < adj.length; i++) 
			adj[i] = new ArrayBag<>();
	}
	
	//同一个点会加两遍，比如(1, 1)
	public void addEdge(Edge e) {
		int v = e.either(), w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		edgeNum++;
	}
	
	public Iterable<Edge> adj(int v) { //vertices adjacent to v
		return adj[v];
	}
	
	public int vertices() { //number of vertices(vertex)
		return size;
	}
	
	public Iterable<Edge> edges() { // all edges in the graph
		Set<Edge> set = new HashSet<>();
		for (Bag<Edge> bag : adj) {
			for (Edge edge : bag) {
				set.add(edge);
			}
		}
		return set;
	}
}
