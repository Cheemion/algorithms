package com.algorithms.elementry;
/**
 * Array
 * @author kim
 * ������ά���Ƿ����ӡ���������id��һ�µĻ� ��˵�������ӵ�
 * 
 */
public class QuickFindUF {
	
	public static void main(String[] args) {
		QuickFindUF uf = new QuickFindUF(10);
		uf.union(1, 2);
		uf.union(1, 3);
		System.out.println(uf.connected(2, 3));
	}
	
	//ʵ������
	private int[] id;
	//N
	public QuickFindUF(int capacity) {
		id = new int[capacity];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}
	
	//add connection between
	//N
	public void union(int p, int q) {
		int pValue = id[p];
		int qValue = id[q];
		//������q��ֵ�ĳ�p��ֵ
		for (int i = 0; i < id.length; i++) 
			if (id[i] == qValue)
				id[i] = pValue;
	}
	//�ж�2�����Ƿ�������һ��
	//1
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}
	//�ҵ�p���identifier
	//1
	public int find (int p) {
		return id[p];
	}
	//�ܹ����ٸ���
	//1
	public int count() {
		return id.length;
	}
}