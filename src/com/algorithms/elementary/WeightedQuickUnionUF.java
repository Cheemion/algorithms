package com.algorithms.elementary;

import java.util.Date;

import edu.princeton.cs.algs4.StdIn;

/**
 * weighted tree
 * 权重的qucikUnion
 * 防止某个节点的树太长了
 * 在union的时候把小树放在大树下,这样子能得到最短的数
 * 有2个选择，一个是使用树类，用一个字段维护数的大小
 * 或者使用另外一个数组，用另外一个数组来维护数的大小
 * 在这里我使用一个数组来维护子树的大小
 * 任何树节点x的depth最长是lgN
 * 因为当一个节点x在T1中 并入到T2中的时候，x的depth加1
 * 但是x所在的树的size 至少double
 * 总共N个节点，最多double LgN次
 * 所以所有的节点的depth最多为LgN
 * avoid having too tall tree
 * @author Administrator
 *
 */
public class WeightedQuickUnionUF {
	
	public static void main(String[] args) {
		int N = StdIn.readInt();
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
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
		System.out.println(uf.getClass().getSimpleName() + (after.getTime() - before.getTime()) + " 毫秒");
	}
	
	//实际数组
	private int[] id;
	//子树的大小 刚开始都是1
	private int[] treeSize;
	
	private int count;
	//找到root节点
	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}
	
	//N
	public WeightedQuickUnionUF(int capacity) {
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
		//根节点
		int pRoot = root(p);
		int qRoot = root(q);
		
		//2个树已经连在一起了
		if (pRoot == qRoot) return;
		
		//子树大小
		int pTreeSize = treeSize[pRoot];
		int qTreeSize = treeSize[qRoot];
		//如果树大小相等，或者p树是大小，q是小树的话
		if (pTreeSize == qTreeSize || pTreeSize > qTreeSize) {
			id[qRoot] = pRoot; //q树依附p树
			treeSize[pRoot] = treeSize[pRoot] + treeSize[qRoot]; //修改size
		} else {
			id[pRoot] = qRoot; //p树依附q树
			treeSize[qRoot] = treeSize[qRoot] + treeSize[pRoot]; //修改size
		}
		count--;
	}
	
	//判断2个点是否连接在一起
	//lg(N)
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	//找到p点的identifier
	//lg(N)
	public int find (int p) {
		return root(p);
	}
	//总共多少个点
	//1
	public int count() {
		return count;
	}
}
