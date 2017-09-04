package com.algorithms.select;

import com.algorithms.sort.Sorts;
import static com.algorithms.sort.Sorts.*;
public class QuickSelect {
	//利用等比数组求和公式 可以得出时间复杂度为 O(n)
	// n
	// n/2
	// n /4
	//最后为1
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
}
