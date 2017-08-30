package com.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

public class Sorts {
	
	private static Random rand = new Random();
	
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	public static <T> void exch(T[] a, int i, int j) {
		T swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	//判断是不是ascending 的排序
	public static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++) 
			if (less(a[i], a[i - 1])) return false;
		return true;
	}
	
	public static void main(String[] args) {
		Integer[] a = new Integer[]{ 1, 2, 3, 4, 5 };
		shuffle(a);
		System.out.println(Arrays.toString(a));
	}
	
	//N
	public static <T> void shuffle(T[] a) {
		for (int i = 0; i < a.length; i++) {
			int position = rand.nextInt(i + 1);
			exch(a, i, position);
		}
	}
	
}
