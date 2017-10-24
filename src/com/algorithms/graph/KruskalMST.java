package com.algorithms.graph;

import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.MinPriorityQueue;
import com.algorithms.elementary.Queue;
import com.algorithms.elementary.QuickUnionUF;

public class KruskalMST implements MST{
	
	private Queue<Edge> mst = new ArrayQueue<>();
	
	public KruskalMST(EdgeWeightedGraph g){
		MinPriorityQueue<Edge> pq = new MinPriorityQueue<>();
		for (Edge e : g.edges())
			pq.insert(e);
		
		QuickUnionUF uf = new QuickUnionUF(g.vertices());
		while (!pq.isEmpty() && mst.size() < g.vertices() - 1) { // vertices 个点 需要有 vertices - 1 条边来连接
			Edge e  = pq.delMin();
			int v = e.either(), w = e.other(v);
			if (!uf.connected(v, w)) {
				uf.union(v, w);
				mst.enqueue(e);
			}
		}
	}
	
	
	@Override
	public Iterable<Edge> edges() {
		return mst;
	}

	@Override
	public double weight() {
		int allWeight = 0;
		for (Edge edge : edges())
			allWeight += edge.weight();
		return allWeight;
	}
}
