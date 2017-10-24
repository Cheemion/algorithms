package com.algorithms.graph;

import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.MinPriorityQueue;
import com.algorithms.elementary.Queue;

public class LazyPrimMST implements MST{
	
	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPriorityQueue<Edge> pq;
	
	public LazyPrimMST(EdgeWeightedGraph g) {
		pq = new MinPriorityQueue<>();
		mst = new ArrayQueue<>();
		marked = new boolean[g.vertices()];
		visit(g, 0);
		
		while (!pq.isEmpty()) {
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			if (marked[v] && marked[w]) continue;
			mst.enqueue(e);
			if (!marked[v]) visit(g, v);
			if (!marked[w]) visit(g, w);
		}
	}
	
	private void visit(EdgeWeightedGraph g, int i) {
		marked[i] = true;
		for (Edge edge : g.adj(i)) 
			if (!marked[edge.other(i)])
				pq.insert(edge);
	}

	@Override
	public Iterable<Edge> edges() {
		return mst;
	}

}
