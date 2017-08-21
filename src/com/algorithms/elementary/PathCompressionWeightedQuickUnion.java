package com.algorithms.elementary;

import java.util.Date;

import edu.princeton.cs.algs4.StdIn;

/**
 * ·��ѹ����Ȩ����
 * @author Administrator
 *
 */
public class PathCompressionWeightedQuickUnion {

	public static void main(String[] args) {
		
		int N = StdIn.readInt();
		PathCompressionWeightedQuickUnion uf = new PathCompressionWeightedQuickUnion(N);
		System.out.println(uf.getClass().getName());
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
	
	//ʵ������
	private int[] id;
	//�����Ĵ�С �տ�ʼ����1
	private int[] treeSize;
	//����
	private int count;
	
	//�ҵ�root�ڵ�
	private int root(int i) {
		while (i != id[i]) {
			//�Ѹ��ڵ�ָ�򸸽ڵ�ĸ��ڵ�
			//make every other node in path point to its grandparent
			//·��ѹ��
			id[i] = id[id[i]]; //ֻ��WeightedQuick��һ�д���
			i = id[i];
		}
		return i;
	}
	
	//N
	public PathCompressionWeightedQuickUnion(int capacity) {
		count = capacity;
		id = new int[capacity];
		treeSize = new int[capacity];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
			treeSize[i] = 1;
		}
	}
	
	//add connection between
	//Lg(N)
	public void union(int p, int q) {
		//���ڵ�
		int pRoot = root(p);
		int qRoot = root(q);
		
		//2�����Ѿ�����һ����
		if (pRoot == qRoot) return;
		
		//������С
		int pTreeSize = treeSize[pRoot];
		int qTreeSize = treeSize[qRoot];
		//�������С��ȣ�����p���Ǵ�С��q��С���Ļ�
		if (pTreeSize == qTreeSize || pTreeSize > qTreeSize) {
			id[qRoot] = pRoot; //q������p��
			treeSize[pRoot] = treeSize[pRoot] + treeSize[qRoot]; //�޸�size
		} else {
			id[pRoot] = qRoot; //p������q��
			treeSize[qRoot] = treeSize[qRoot] + treeSize[pRoot]; //�޸�size
		}
		
		count--;
	}
	
	//�ж�2�����Ƿ�������һ��
	//lg(N)
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	//�ҵ�p���identifier
	//lg(N)
	public int find (int p) {
		return root(p);
	}
	
	//�ܹ����ٸ���
	//1
	public int count() {
		return count;
	}
}
