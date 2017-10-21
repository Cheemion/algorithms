package com.algorithms.graph;

import com.algorithms.elementary.ArrayBag;
import com.algorithms.elementary.Bag;

public class Digraph {
	
	private final int size; // number of vertices
	private int edgeNum;
	private Bag<Integer>[] adj;
	
	public Digraph(int size) {
		this.size = size;
		adj = new ArrayBag[size];
		for (int i = 0; i < adj.length; i++) 
			adj[i] = new ArrayBag<>();
	}
	public void addEdge(int v, int w) {
		adj[v].add(w);
		edgeNum++;
	}
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	public int vertices(){
		return size;
	}
	public int edges() {
		return edgeNum;
	}
	Digraph reverse() {
		Digraph d = new Digraph(size);
		for (int v = 0; v < size; v++) {
			Bag<Integer> bag = adj[v];
			for (int w : bag)
				d.addEdge(w, v);
		}
		return d;
	}
	
}
