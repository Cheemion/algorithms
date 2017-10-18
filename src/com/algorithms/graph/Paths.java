package com.algorithms.graph;

public interface Paths {
	public boolean hasPathTo(int v);
	Iterable<Integer> pathTo(int v);
}
