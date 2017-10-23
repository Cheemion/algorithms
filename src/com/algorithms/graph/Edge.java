package com.algorithms.graph;

public class Edge implements Comparable<Edge> {
	
	private int v;
	private int w;
	private double weight;
	
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int either() {
		return v;
	}
	public int other(int vertex) {
		if (vertex == v) return w;
		else return v;
	}
	
	public double weight() {
		return weight;
	}
	
	
	@Override
	public int compareTo(Edge that) {
		if (this.weight < that.weight) return -1;
		else if (this.weight > that.weight) return 1;
		else return 0;
	}

	@Override
	public String toString() {
		return "Edge [v=" + v + ", w=" + w + ", weight=" + weight + "]";
	}

}
