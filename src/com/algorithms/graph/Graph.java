package com.algorithms.graph;

import com.algorithms.elementary.ArrayBag;
import com.algorithms.elementary.Bag;

public class Graph {
	
	private final int size; // number of vertices
	private int edgeNum;
	private Bag<Integer>[] adj;
	
	public static void main(String[] args) {
		Graph g = new Graph(3);
		g.addEdge(0, 0);
		g.addEdge(1, 1);
		System.out.println(g.vertices());
		System.out.println(g.numberOfSelfLoops(g));
	}
	
	public Graph(int size) {
		this.size = size;
		adj = new ArrayBag[size];
		for (int i = 0; i < adj.length; i++) 
			adj[i] = new ArrayBag<>();
	}
	
	//同一个点会加两遍，比如(1, 1)
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		edgeNum++;
	}
	
	public Iterable<Integer> adj(int v) { //vertices adjacent to v
		return adj[v];
	}
	
	public int vertices() { //number of vertices(vertex)
		return size;
	}
	
	public int edges() { // number of edges
		return edgeNum;
	}
	
	public static int degree(Graph g, int v) {
		int degree = 0;
		for (int w : g.adj(v)) degree++;
		return degree;
	}
	
	public static int maxDegree(Graph g) {
		int max = 0;
		for (int v = 0; v < g.vertices(); v++) {
			if (degree(g, v) > max)
				max = degree(g, v);
		}
		return max;
	}
	
	public static double averageDegree(Graph g) {
		return 2.0 * g.edges() / g.vertices();
	}
	
	public static int numberOfSelfLoops(Graph g) {
		int count = 0;
		for (int v = 0; v < g.vertices(); v++) 
			for (int w : g.adj(v))
				if (v == w) count++;
		return count / 2;
	}
	
}
