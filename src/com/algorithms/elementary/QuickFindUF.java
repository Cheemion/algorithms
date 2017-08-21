package com.algorithms.elementary;

import java.util.Arrays;
import java.util.Date;

import edu.princeton.cs.algs4.StdIn;

/**
 * Array
 * @author kim
 * 用数组维护是否连接。如果数组的id是一致的话 就说明是连接的
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
		System.out.println(uf.getClass().getSimpleName() + (after.getTime() - before.getTime()) + " 毫秒");
	}
	
	//连通分量
	private int count;
	
	//实际数组
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
		//相等的话
		if (pValue == qValue) return;
		//把所有q的值改成p的值
		for (int i = 0; i < id.length; i++) 
			if (id[i] == qValue)
				id[i] = pValue;
		count--;
	}
	
	//判断2个点是否连接在一起
	//1
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}
	
	//找到p点的identifier
	//1
	public int find (int p) {
		return id[p];
	}
	
	//总共多少个点
	//1
	public int count() {
		return count;
	}
	
}