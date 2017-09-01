package com.algorithms.sort;
import static com.algorithms.sort.Sorts.*;

import java.util.Arrays;
public class QuickSort {
	
	// NlogN optimal
	// worse N ^ 2
	// average 1.39N lg N
	public static void main(String[] args) {
		Integer[] a = new Integer[]{ 2, 3, 4, 1, 10, 22,33, 1};
		int i = (int) selectRecursive(a, 100);
		System.out.println(i);
	}
	
	public static void sort(Comparable[] a) {
		shuffle(a);
		sort(a, 0, a.length - 1);
	}
	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int p = partition(a, lo, hi);
		sort(a, lo, p - 1); //sort left
		sort(a, p + 1, hi); // sort right
	}
	
	//return the index of the first elements should be
	private static int partition(Comparable[] a, int lo, int hi) {
		Comparable firstElement = a[lo];
		int i = lo, j = hi + 1;
		while(true) {
			while(less(a[++i], firstElement))
				if (i == hi) break;
			while(less(firstElement, a[--j]));
				
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	//average on N 
	// k 得到排在第k的 element
	public static Comparable select(Comparable[] a, int k) {
		Sorts.shuffle(a);
		int lo = 0, hi = a.length - 1;
		while (hi > lo) {
			int j = partition(a, lo , hi);
			if (j < k) lo = j + 1;
			else if (j > k) hi = j - 1;
			else return a[k];
		}
		return a[k];
	}
	
	//recursive
	public static Comparable selectRecursive(Comparable[] a, int k, int lo, int hi) {
		int j = partition(a, lo, hi);
		if (j > k) return selectRecursive(a, k, lo, j - 1);
		else if (j < k) return selectRecursive(a, k, j + 1, hi);
		else return a[j];
	}
	//recursive
	public static Comparable selectRecursive(Comparable[] a, int k) {
		Sorts.shuffle(a);
		return selectRecursive(a, k, 0, a.length - 1);
	}
}
