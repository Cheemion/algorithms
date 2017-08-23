package com.algorithms.elementary;

import java.util.Date;

import edu.princeton.cs.algs4.StdIn;

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
		System.out.println(uf.getClass().getSimpleName() + (after.getTime() - before.getTime()) + " ����");
	}
	
	//��ͨ�����Ĵ�С
	private int count;
	
	//ʵ������
	private int[] id;
	
	//�ҵ�root�ڵ�
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
		count--;
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
		return count;
	}
}
