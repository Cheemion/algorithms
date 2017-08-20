package com.algorithms.elementry;
/**
 * Array
 * @author kim
 * 用数组维护是否连接。如果数组的id是一致的话 就说明是连接的
 * 
 */
public class QuickFindUF {
	
	public static void main(String[] args) {
		QuickFindUF uf = new QuickFindUF(10);
		uf.union(1, 2);
		uf.union(1, 3);
		System.out.println(uf.connected(2, 3));
	}
	
	//实际数组
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
		//把所有q的值改成p的值
		for (int i = 0; i < id.length; i++) 
			if (id[i] == qValue)
				id[i] = pValue;
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
		return id.length;
	}
}