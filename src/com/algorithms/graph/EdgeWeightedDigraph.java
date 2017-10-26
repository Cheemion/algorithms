package com.algorithms.graph;

import java.util.Arrays;

import com.algorithms.elementary.ArrayBag;
import com.algorithms.elementary.Bag;

public class EdgeWeightedDigraph {
	
	private final int size; // number of vertices
	private int edgeNum;
	private Bag<DirectedEdge>[] adj;
	
	public EdgeWeightedDigraph(int size) {
		this.size = size;
		adj = new ArrayBag[size];
		for (int i = 0; i < adj.length; i++) 
			adj[i] = new ArrayBag<>();
	}
	
	//同一个点会加两遍，比如(1, 1)
	public void addEdge(DirectedEdge e) {
		int v = e.from();
		adj[v].add(e);
		edgeNum++;
	}
	
	public Iterable<DirectedEdge> adj(int v) { //vertices adjacent to v
		return adj[v];
	}
	
	public int vertices() { //number of vertices(vertex)
		return size;
	}
	
	public Iterable<DirectedEdge> edges() { // all edges in the graph
		Bag<DirectedEdge> set = new ArrayBag<>();
		for (Bag<DirectedEdge> bag : adj) {
			for (DirectedEdge edge : bag) {
				set.add(edge);
			}
		}
		return set;
	}

	@Override
	public String toString() {
		return "EdgeWeightedDigraph [size=" + size + ", edgeNum=" + edgeNum + ", adj=" + Arrays.toString(adj) + "]";
	}
}
