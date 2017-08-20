package com.algorithms.elementry;
/**
 * Tree
 * @author Administrator
 *	ÿ���ڵ��ֵָ�����ĸ��ڵ�
 *	rootָ���Լ�
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
	
	//ʵ������
	private int[] id;
	
	//�ҵ�root�ڵ�
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
	
	//�ж�2�����Ƿ�������һ��
	//N
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	//�ҵ�p���identifier
	//N
	public int find (int p) {
		return root(p);
	}
	//�ܹ����ٸ���
	//1
	public int count() {
		return id.length;
	}
}
