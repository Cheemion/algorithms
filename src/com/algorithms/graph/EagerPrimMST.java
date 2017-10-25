package com.algorithms.graph;

import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.IndexMinPriorityQueue;
import com.algorithms.elementary.Queue;

//不能有多个分量 比如sets of isolated edges
public class EagerPrimMST implements MST{
	
	private boolean[] marked;
	private Queue<Edge> mst; // minimun spanning tree
	private IndexMinPriorityQueue<Edge> impq;
	private Double[] distTo; //from mst to the vertexes that not in the tree
	private Edge[] edgeTo;
	
	
	public static void main(String[] args) {
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(3);
		ewg.addEdge(new Edge(0, 1, 3));
		ewg.addEdge(new Edge(0, 2, 4));
		ewg.addEdge(new Edge(2, 1, 5));
		
		EagerPrimMST epmst = new EagerPrimMST(ewg);
		for (Edge edge : epmst.edges())
			System.out.println(edge);
		
		System.out.println(epmst.weight());
	}
	
	public EagerPrimMST(EdgeWeightedGraph g) {
		impq = new IndexMinPriorityQueue<>(g.vertices());
		mst = new ArrayQueue<>();
		marked = new boolean[g.vertices()];
		distTo = new Double[g.vertices()];
		edgeTo = new Edge[g.vertices()];
		
		visit(g, 0);
		while(mst.size() < g.vertices() && !impq.isEmpty()) {
			int minIndex = impq.delMin();
			visit(g, minIndex);
		}
	}
	
	private void visit(EdgeWeightedGraph g, int v) {
		marked[v] = true;
		if (v != 0) //初始化
			mst.enqueue(edgeTo[v]);
		for (Edge edge : g.adj(v)) {
			int w = edge.other(v);
			double weight = edge.weight();
			if (marked[w]) continue;
			if (distTo[w] == null) {
				distTo[w] = weight;
				edgeTo[w] = edge;
				impq.insert(w, edge);
			} else {
				if (distTo[w] > weight) {
					distTo[w] = weight;
					impq.decreaseKey(w, edge);
				}
			}
		}
	}

	@Override
	public Iterable<Edge> edges() {
		return mst;
	}

}
