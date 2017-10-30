package com.algorithms.graph;

import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.Stack;

/**
 * topologicl order exists only if the digraph is not a cycle
 * @author Administrator
 *
 */
public class EdgeWeightedTopologiclOrder {
	private boolean[] marked;
	//��һ����ɵ�Ԫ���ȼ�������
	//�������� topoligic��˳��, ���������Ҫ������ɵ�
	private Stack<Integer> order;
	private boolean isCycle;
	
	public static void main(String[] args) {
		EdgeWeightedDigraph g = new EdgeWeightedDigraph(4);
		g.addEdge(new DirectedEdge(0, 1, 1));
		g.addEdge(new DirectedEdge(1, 2, 1));
		g.addEdge(new DirectedEdge(2, 3, 1));
		System.out.println("1");
		EdgeWeightedTopologiclOrder to = new EdgeWeightedTopologiclOrder(g);
		System.out.println(to.isCycle());
		System.out.println("1");
		System.out.println(to.topologiclOrder());
	}
	
	public EdgeWeightedTopologiclOrder(EdgeWeightedDigraph g) {
		cycleDetect(g);
		marked = new boolean[g.vertices()];
		order = new ArrayStack<>();
		for (int v = 0; v < g.vertices(); v++) 
			if (!marked[v]) dfs(g, v);
	}
	
	private void cycleDetect(EdgeWeightedDigraph g) {
		boolean[] isMarked = new boolean[g.vertices()];
		Integer[] edgeTo = new Integer[g.vertices()];
		for (int i = 0; i < g.vertices(); i++) {
			if (!isCycle)
				cycleDetect(g, i, isMarked, edgeTo);
		}
	}
	
	private void cycleDetect(EdgeWeightedDigraph g, int source, boolean[] marked ,Integer[] edgeTo) {
		marked[source] = true;
		for (DirectedEdge e : g.adj(source)) {
			int v = e.to();
			for (Integer markedV = edgeTo[source]; markedV != null; markedV = edgeTo[markedV])
				if (v == markedV) isCycle = true; //��һ���Ƿ����Ѿ������ĵ���
		}
		
		for (DirectedEdge e : g.adj(source)) { //��ǰ�ĵ�ĸ����ı�
			int w = e.to();
			if (!marked[w] && !isCycle) {
				edgeTo[w] = source;
				cycleDetect(g, w, marked, edgeTo);
			}
		}
	}
	
	public boolean isCycle() {
		return isCycle;
	}
	
	private void dfs(EdgeWeightedDigraph g, int v) {
		marked[v] = true;
		for (DirectedEdge e : g.adj(v)) {
			int w = e.to();
			if (!marked[w])
				dfs(g, w);
		}
		order.push(v);
	}
	
	public Iterable<Integer> topologiclOrder() {
		return order;
	}
}
