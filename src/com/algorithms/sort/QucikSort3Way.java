package com.algorithms.sort;
import static com.algorithms.sort.Sorts.*;
//Dijkstra
//3向切分快速排序, 重复元素多的时候使用 很快
public class QucikSort3Way {
	
	public static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int lt = lo, i = lo + 1, gt = hi;
		Comparable v = a[lo];
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0) exch(a, lt++, i++);
			else if (cmp > 0) exch(a, i, gt--);
			else i++;
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}
	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}
	
}
