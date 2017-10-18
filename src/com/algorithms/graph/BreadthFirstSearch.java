package com.algorithms.graph;

import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.Queue;
import com.algorithms.elementary.Stack;

public class BreadthFirstSearch implements Paths {
	private boolean[] marked;
	private int[] edgeTo;
	private int s; //source ���
	
	
	public static void main(String[] args) {
		Graph g = new Graph(3);
		g.addEdge(0, 0);
		g.addEdge(1, 0);
		DepthFirstSearch dgs = new DepthFirstSearch(g, 0);
		System.out.println(dgs.hasPathTo(0));
		System.out.println(dgs.pathTo(1));
	}
	
	public BreadthFirstSearch(Graph g, int s) {
		this.s = s;
		marked = new boolean[g.vertices()];
		edgeTo = new int[g.vertices()];
		bfs(g, s);
	}
	
	
	//s��㿪ʼ����������ɢ
	private void bfs(Graph g, int s) {
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
