package com.algorithms.graph;

import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.Stack;

/**
 * depth first search
 * @author Administrator
 *
 */
public class DirectedDFS implements Paths{
	
	private boolean[] marked;
	private int[] edgeTo;
	private int s; // source Æðµã
	
	public DirectedDFS(Digraph g, int s) {
		marked = new boolean[g.vertices()];
		dfs(g, s);
	}

	private void dfs(Digraph g, int s) {
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
