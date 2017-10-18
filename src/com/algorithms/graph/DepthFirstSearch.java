package com.algorithms.graph;

import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.Stack;

public class DepthFirstSearch implements Paths{
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	
	public static void main(String[] args) {
		Graph g = new Graph(3);
		g.addEdge(0, 0);
		g.addEdge(1, 0);
		DepthFirstSearch dgs = new DepthFirstSearch(g, 0);
		System.out.println(dgs.hasPathTo(0));
		System.out.println(dgs.pathTo(0));
	}
	
	public DepthFirstSearch(Graph g, int s) {
		this.s = s;
		marked = new boolean[g.vertices()];
		edgeTo = new int[g.vertices()];
		dfs(g, s);
	}
	
	private void dfs(Graph g, int s) {
		marked[s] = true;
		for (int w : g.adj(s)) {
			if (!marked[w]) {
				edgeTo[w] = s;
				dfs(g, w);
			}
		}
	}
	
	@Override
	public boolean hasPathTo(int v) {
		return marked[v];
	}

	@Override
	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) return null;
		Stack<Integer> stack = new ArrayStack<>();
		for (int vertex = v; vertex != s; vertex = edgeTo[v])
			stack.push(vertex);
		stack.push(s);
		return stack;
	}
	
}
