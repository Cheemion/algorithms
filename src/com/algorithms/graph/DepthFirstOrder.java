package com.algorithms.graph;

import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.Stack;

/**
 * topologicl order exists only if the digraph is not a cycle
 * @author Administrator
 *
 */
public class DepthFirstOrder {
	private boolean[] marked;
	//第一个完成的元素先加入其中
	private Stack<Integer> order;
	private boolean isCycle;
	public DepthFirstOrder(Digraph g) {
		cycleDetect();
		if (!isCycle) {
			order = new ArrayStack<>();
			for (int v = 0; v < g.vertices(); v++) 
				if (!marked[v]) dfs(g, v);
		}
	}
	
	
	
	private void cycleDetect() {
		
	}
	
	public boolean isCycle() {
		return isCycle;
	}
	
	private void dfs(Digraph g, int v) {
		marked[v] = true;
		for (int w : g.adj(v))
			if (!marked[v])
				dfs(g, w);
		order.push(v);
	}
	public Iterable<Integer> topologiclOrder() {
		return order;
	}
}
