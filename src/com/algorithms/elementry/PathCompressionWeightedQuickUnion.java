package com.algorithms.elementry;
/**
 * 路径压缩的权重树
 * @author Administrator
 *
 */
public class PathCompressionWeightedQuickUnion {

	public static void main(String[] args) {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
		uf.union(1, 2);
		uf.union(1, 3);
		System.out.println(uf.connected(2, 3));
		System.out.println(uf.connected(2, 4));
	}
	
	//实际数组
	private int[] id;
	//子树的大小 刚开始都是1
	private int[] treeSize;
	
	//找到root节点
	private int root(int i) {
		while (i != id[i]) {
			//把父节点指向父节点的父节点
			//make every other node in path point to its grandparent
			id[i] = id[id[i]]; //只比WeightedQuick加一行代码
			i = id[i];
		}
		return i;
	}
	
	//N
	public PathCompressionWeightedQuickUnion(int capacity) {
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
		return id.length;
	}
}
