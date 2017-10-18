package com.algorithms.graph;

public class ConnectedComponents {
	
	public static void main(String[] args) {
		Graph g = new Graph(3);
		g.addEdge(0, 0);
		g.addEdge(1, 0);
		ConnectedComponents cc = new ConnectedComponents(g);
		
		System.out.println("count:" + cc.count());
		System.out.println(cc.id(0));
		System.out.println(cc.id(1));
		System.out.println(cc.id(2));
	}
	
	private boolean[] marked;
	private int[] id; // ÿ��vertex��id
	private int count; // �ܹ���Ϊ����
	
	public ConnectedComponents(Graph g) {
		marked = new boolean[g.vertices()];
		id = new int[g.vertices()];
		cc(g);
	}
	
	private void cc(Graph g) {
		for (int i = 0; i < g.vertices(); i++) {
			if (!marked[i])	{
				dfs(g, i);
				count++;
			}
		}
	}
	
	//һ��������һ��dfs�з���
	private void dfs(Graph g, int s) {
		marked[s] = true;
		id[s] = count;
		for (int w : g.adj(s)) {
			if (!marked[w]) {
				dfs(g, w);
			}
		}
	}
	
	public int count() {
		return count;
	}
	
	public int id(int v) {
		return id[v];
	}
	
}
