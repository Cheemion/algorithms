package com.algorithms.graph;

import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.Queue;
import com.algorithms.elementary.Stack;

public class DirectedBFS implements Paths{
	private boolean[] marked;
	private int[] edgeTo;
	private int s; //source 起点
	
	public static void main(String[] args) {
		Digraph g = new Digraph(3);
		g.addEdge(0, 0);
		g.addEdge(1, 0);
		DirectedBFS dgs = new DirectedBFS(g, 0);
		System.out.println(dgs.hasPathTo(0));
		System.out.println(dgs.hasPathTo(1));
	}
	
	public DirectedBFS(Digraph g, int s) {
		this.s = s;
		marked = new boolean[g.vertices()];
		edgeTo = new int[g.vertices()];
		bfs(g, s);
	}
	
	
	//s起点开始向其他点扩散
	private void bfs(Digraph g, int s) {
		Queue<Integer> queue = new ArrayQueue<>();
		queue.enqueue(s);
		marked[s] = true;
		while(!queue.isEmpty()) {
			Integer vertex = queue.dequeue();
			for (int w : g.adj(vertex)) {
				if (!marked[w]) {
					marked[w] = true;
					edgeTo[w] = vertex;
					queue.enqueue(w);
				}
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
