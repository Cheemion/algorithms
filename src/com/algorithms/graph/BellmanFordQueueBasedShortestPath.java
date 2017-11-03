package com.algorithms.graph;

import com.algorithms.elementary.ArrayBag;
import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.Bag;
import com.algorithms.elementary.Queue;
import com.algorithms.elementary.Stack;

/**
 * can not have negative cyclic
 * @author altro
 *
 */
public class BellmanFordQueueBasedShortestPath {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private int source;
	private Queue<Integer> changed;
	private boolean hasNegativeCycle;
	private Bag<DirectedEdge> negativeCycleEdges;
	
	public static void main(String[] args) {
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(8);
		graph.addEdge(new DirectedEdge(4, 5, 0.35));
		graph.addEdge(new DirectedEdge(5, 6, -1));
		graph.addEdge(new DirectedEdge(6, 4, 0.37));
		graph.addEdge(new DirectedEdge(5, 7, 0.28));
		graph.addEdge(new DirectedEdge(7, 5, 0.28));
		graph.addEdge(new DirectedEdge(5, 1, 0.32));
		graph.addEdge(new DirectedEdge(0, 4, 0.38));
		graph.addEdge(new DirectedEdge(0, 2, 0.26));
		graph.addEdge(new DirectedEdge(7, 3, 0.39));
		graph.addEdge(new DirectedEdge(1, 3, 0.29));
		graph.addEdge(new DirectedEdge(2, 7, 0.34));
		graph.addEdge(new DirectedEdge(6, 2, -1.20));
		graph.addEdge(new DirectedEdge(3, 6, 0.52));
		graph.addEdge(new DirectedEdge(6, 0, -1.40));
		graph.addEdge(new DirectedEdge(6, 4, -1.25));
		BellmanFordQueueBasedShortestPath b = new BellmanFordQueueBasedShortestPath(graph, 0);
		System.out.println(b.hasNegativeCycle());
		System.out.println(b.negativeCycle());
	}
	
	
	public BellmanFordQueueBasedShortestPath(EdgeWeightedDigraph g, int s) {
		this.source = s;
		distTo = new double[g.vertices()];
		edgeTo = new DirectedEdge[g.vertices()];
		changed = new ArrayQueue<>();
		
		for (int i = 0; i < distTo.length; i++) 
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		changed.enqueue(s);
		start(g);
	}
	

	private void start(EdgeWeightedDigraph g) {
		int pass = 0;
		for (; pass < g.vertices() && !changed.isEmpty(); pass++) {
			Queue<Integer> temp = new ArrayQueue<>();
			for (Integer changedPosition : changed) {
				for (DirectedEdge e : g.adj(changedPosition))
					relax(e, temp);
			}
			changed = temp;
			if (changed.isEmpty()) break;
		}
		if (pass == g.vertices() && !changed.isEmpty()) {
			hasNegativeCycle = true;
			negativeCycleEdges = new ArrayBag<>();
			int changePos = changed.dequeue();
			for (int i = edgeTo[changePos].from(); i != changePos; i = edgeTo[i].from())
				negativeCycleEdges.add(edgeTo[i]);
			negativeCycleEdges.add(edgeTo[changePos]);
		}
	}


	public boolean hasPathTo(int v) {
		return distTo[v] != Double.POSITIVE_INFINITY;
	}
	
	public double distTo(int v) {
		return distTo[v];
	}
	
	public Iterable<DirectedEdge> pathTo(int v) {
		Stack<DirectedEdge> path = new ArrayStack<>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
			path.push(e);
		return path;
	}
	
	private void relax(DirectedEdge e, Queue<Integer> queue) {
		int v = e.from(), w = e.to();
		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			queue.enqueue(w);
		}
	}
	
	public boolean hasNegativeCycle() {
		return hasNegativeCycle;
	}
	
	public Iterable<DirectedEdge> negativeCycle() {
		return negativeCycleEdges;
	}
}

