package com.algorithms.elementry;

/**
 * weighted tree
 * Ȩ�ص�qucikUnion
 * ��ֹĳ���ڵ����̫����
 * ��union��ʱ���С�����ڴ�����,�������ܵõ���̵���
 * ��2��ѡ��һ����ʹ�����࣬��һ���ֶ�ά�����Ĵ�С
 * ����ʹ������һ�����飬������һ��������ά�����Ĵ�С
 * ��������ʹ��һ��������ά�������Ĵ�С
 * �κ����ڵ�x��depth���lgN
 * ��Ϊ��һ���ڵ�x��T1�� ���뵽T2�е�ʱ��x��depth��1
 * ����x���ڵ�����size ����double
 * �ܹ�N���ڵ㣬���double LgN��
 * �������еĽڵ��depth���ΪLgN
 * avoid having too tall tree
 * @author Administrator
 *
 */
public class WeightedQuickUnionUF {
	
	public static void main(String[] args) {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
		uf.union(1, 2);
		uf.union(1, 3);
		System.out.println(uf.connected(2, 3));
		System.out.println(uf.connected(2, 4));
	}
	
	//ʵ������
	private int[] id;
	//�����Ĵ�С �տ�ʼ����1
	private int[] treeSize;
	
	//�ҵ�root�ڵ�
	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}
	
	//N
	public WeightedQuickUnionUF(int capacity) {
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
		return id.length;
	}
}
