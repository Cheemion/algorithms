package com.algorithms.elementary;

import java.util.Date;

import edu.princeton.cs.algs4.StdIn;

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
		int N = StdIn.readInt();
		QuickUnionUF uf = new QuickUnionUF(N);
		Date before = new Date();
		try {
			while (!StdIn.isEmpty()) {
				int p = StdIn.readInt();
				int q = StdIn.readInt();
				uf.union(p, q);
				System.out.println(p + " " + q);
			}
		} catch (Exception e) {
			
		}
		System.out.println(uf.count() + " components");
		Date after = new Date();
		System.out.println(uf.getClass().getSimpleName() + (after.getTime() - before.getTime()) + " 毫秒");
	}
	
	//连通分量的大小
	private int count;
	
	//实际数组
	private int[] id;
	
	//找到root节点
	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}
	
	//N
	public QuickUnionUF(int capacity) {
		count = capacity;
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
		
		if (pRoot == qRoot) return;
		
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
		return count;
	}
}
