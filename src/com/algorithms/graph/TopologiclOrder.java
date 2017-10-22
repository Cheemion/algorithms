package com.algorithms.graph;

import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.Stack;

/**
 * topologicl order exists only if the digraph is not a cycle
 * @author Administrator
 *
 */
public class TopologiclOrder {
	private boolean[] marked;
	//第一个完成的元素先加入其中
	private Stack<Integer> order;
	private boolean isCycle;
	
	public static void main(String[] args) {
		Digraph g = new Digraph(4);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		System.out.println("1");
		TopologiclOrder to = new TopologiclOrder(g);
		System.out.println(to.isCycle());
		System.out.println("1");
		
		System.out.println(to.topologiclOrder());
	}
	
	public TopologiclOrder(Digraph g) {
		cycleDetect(g);
		if (!isCycle) {
			marked = new boolean[g.vertices()];
			order = new ArrayStack<>();
			for (int v = 0; v < g.vertices(); v++) 
				if (!marked[v]) dfs(g, v);
		}
	}
	
	private void cycleDetect(Digraph g) {
		boolean[] isMarked = new boolean[g.vertices()];
		Integer[] edgeTo = new Integer[g.vertices()];
		for (int i = 0; i < g.vertices(); i++) {
			if (!isCycle)
				cycleDetect(g, i, isMarked, edgeTo);
		}
	}
	
	private void cycleDetect(Digraph g, int source, boolean[] marked ,Integer[] edgeTo) {
		marked[source] = true;
		for (int v : g.adj(source))  //下一个点
			for (Integer markedV = edgeTo[source]; markedV != null; markedV = edgeTo[markedV])
				if (v == markedV) isCycle = true; //下一点是否在已经经过的点中
		
		for (int w : g.adj(source)) { //当前的点的附近的边
			if (!marked[w] && !isCycle) {
				edgeTo[w] = source;
				cycleDetect(g, w, marked, edgeTo);
			}
		}
	}
	
	public boolean isCycle() {
		return isCycle;
	}
	
	private void dfs(Digraph g, int v) {
		marked[v] = true;
		for (int w : g.adj(v))
			if (!marked[w])
				dfs(g, w);
		order.push(v);
	}
	
	public Iterable<Integer> topologiclOrder() {
		if (isCycle) return null;
		return order;
	}
}
