package com.algorithms.elementary;

public class BinarySearch {
	
	
	
	public static void main(String[] args) {
		int [] a = new int[]{1, 2, 3, 4, 4,5 , 5, 10, 15, 200, 400};
		int tobefind = 15;
		int s = rank1(tobefind, a);
		int ss = rank2(tobefind, a);
		System.out.println( s + "  " + ss);
	}
	
	//二分法查找
	//a是从小到大的序列 
	//循环
	public static int rank1(int key, int [] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			int middle = lo + (hi - lo) / 2;
			if (key < a[middle]) hi = middle - 1; 
			else if (key > a[middle]) lo = middle + 1;
			else return middle;
		}
		return -1; //没找到
	}
	//recursive
	public static int rank2(int key, int [] a) {
		return find(key, 0, a.length - 1, a);
	}
	
	private static int find(int key, int low, int high, int [] a){
		int middle = low + (high - low) / 2;
		
		if(high < low) return -1; //没有找到
		else if (key > a[middle]) return find(key, middle + 1, high, a);
		else if (key < a[middle]) return find(key, low, high - 1, a);
		else return middle; //找到了
	}
}
