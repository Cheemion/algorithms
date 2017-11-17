package com.algorithms.string;
import static com.algorithms.sort.Sorts.exch;

/**
 * 和一般的quickSort相比
 * 这个quickSort只需要go through 1.39NlogN个  character 而不需要每次都比较String的多少
 * @author altro
 *
 */
public class QuickSort3Way {
	
	public static void sort(String[] a) {
		sort(a, 0, a.length - 1, 0);
	}
	
	private static void sort(String[] a, int lo, int hi, int d) {
		if (hi <= lo) return;
		int lt = lo, gt = hi;
		int v = charAt(a[lo], d);
		int i = lo + 1;
		while (i <= gt) {
			int t = charAt(a[i], d);
			if (t < v) exch(a, lt++, i++);
			else if (t > v) exch(a, i, gt--);
			else i++;
		}
		sort(a, lo, lt - 1, d);
		if (v >= 0) sort(a, lt, gt, d + 1);
		sort(a, gt + 1, hi ,d);
	}
	
	
	private static int charAt(String s, int d) {
		if (d < s.length()) return s.charAt(d);
		else return -1;
	}
}
