package com.algorithms.sort;
import static com.algorithms.sort.Sorts.*;
import java.util.Arrays;
public class ImprovedQuickSort {
	private static int CUTOFF = 15;  //between 5 to 15 is good
	public static void main(String[] args) {
		Integer[] a = new Integer[]{ 2, 3, 4, 1, 10, 22, 0 ,33, 1};
		System.out.println(Arrays.toString(a));
		sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	public static void sort(Comparable[] a) {
		shuffle(a);
		sort(a, 0, a.length - 1);
	}
	private static void sort(Comparable[] a, int lo, int hi) {
		
		//小数组使用InsertionSort会快一些
		if (hi <= lo + CUTOFF) {
			InsertionSort.sort(a, lo, hi);
			return;
		}
		
		//找到3个数的中间值
		int median = medianOf3(a, lo, lo + (hi - lo) / 2, hi);
		exch(a, median, lo);
		
		
		//
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
	
	private static int medianOf3(Comparable[]a, int lo, int mid, int hi) {
		boolean loLessThanMid = less(a[lo], a[mid]);
		boolean midLessThanHi = less(a[mid], a[hi]);
		boolean loLessThanHi = less(a[lo], a[hi]);
		if (loLessThanHi == midLessThanHi) return mid;
		else if(loLessThanHi != loLessThanMid) return lo;
		else return hi;
	}
	
}
