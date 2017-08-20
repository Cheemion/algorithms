package com.algorithms.elementry;
/**
 * Tree
 * @author Administrator
 *	每个节点的值指向他的父节点
 *	root指向自己
 *	id[i] is parent of i
 *	root of i is id[id[id[i]]]
 */
public class QuickUnionUF {
	
	public static void main(String[] args) {
		QuickUnionUF uf = new QuickUnionUF(10);
		uf.union(1, 2);
		uf.union(1, 3);
		System.out.println(uf.connected(2, 3));
	}
	
	//实际数组
	private int[] id;
	
	//找到root节点
	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}
	
	//N
	public QuickUnionUF(int capacity) {
		id = new int[capacity];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}
	
	//add connection between
	//N
	public void union(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		id[qRoot] = pRoot;
	}
	
	//判断2个点是否连接在一起
	//N
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	//找到p点的identifier
	//N
	public int find (int p) {
		return root(p);
	}
	//总共多少个点
	//1
	public int count() {
		return id.length;
	}
}
