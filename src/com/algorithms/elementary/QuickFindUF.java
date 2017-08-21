package com.algorithms.elementary;

import java.util.Arrays;
import java.util.Date;

import edu.princeton.cs.algs4.StdIn;

/**
 * Array
 * @author kim
 * ������ά���Ƿ����ӡ���������id��һ�µĻ� ��˵�������ӵ�
 * 
 */
public class QuickFindUF {
	
	public static void main(String[] args) {
		
		int N = StdIn.readInt();
		QuickFindUF uf = new QuickFindUF(N);

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
	
	//��ͨ����
	private int count;
	
	//ʵ������
	private int[] id;
	
	//N
	public QuickFindUF(int capacity) {
		id = new int[capacity];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
		count = capacity;
	}
	
	//add connection between
	//N
	public void union(int p, int q) {
		int pValue = find(p);
		int qValue = find(q);
		//��ȵĻ�
		if (pValue == qValue) return;
		//������q��ֵ�ĳ�p��ֵ
		for (int i = 0; i < id.length; i++) 
			if (id[i] == qValue)
				id[i] = pValue;
		count--;
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
		return count;
	}
	
}