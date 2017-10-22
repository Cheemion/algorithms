package com.algorithms.graph;
/**
 * stronglyConnected means v can reach w, and w can reach v
 * @author Administrator
 *
 */

public class KosarajuSharirSCC {
	private boolean marked[];
	private int[] id;
	private int count;
	
	public KosarajuSharirSCC(Digraph g) {
		marked = new boolean[g.vertices()];
		id = new int[g.vertices()];
		TopologiclOrder to = new TopologiclOrder(g.reverse());
		for (int v : to.topologiclOrder()) {
			if (!marked[v]) {
				dfs(g, v);
				count++;
			}
		}
	}

	private void dfs(Digraph g, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : g.adj(v))
			if (!marked[w])
				dfs(g, w);
	}
	
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}
}
