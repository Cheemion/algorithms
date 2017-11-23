package com.algorithms.string;
/**
 * the most efficient algorithms
 * time propertional to n / r
 * @author altro
 *
 */
public class BoyerMooreStringSearch {
	
	public static void main(String[] args) {
		BoyerMooreStringSearch b = new BoyerMooreStringSearch("123");
		int search = b.search("aa123");
		System.out.println(search);
	}
	
	
	private int[] right;
	private String pat;
	private static final int R = 256;
	public BoyerMooreStringSearch(String pat) {
		this.pat = pat;
		int m = pat.length();
		right = new int[R];
		for (int c = 0; c < R; c++) { //初始所有的值都为-1
			right[c] = -1;
		}
		for (int j = 0; j < m; j++) { //pat中所有的char,在pattern中的位置。 [0, length - 1]
			right[pat.charAt(j)] = j;
		}
	}
	
	public int search(String txt) {
		int n = txt.length();
		int m = pat.length();
		int skip;//需要跳过的长度
		for (int i = 0; i <= n - m; i += skip) { // i是在文本中，从左向右移动
			skip = 0;
			for (int j = m - 1; j >= 0; j--) //j是pattern中的位置， 从右向左边移动
				if (pat.charAt(j) != txt.charAt(i + j)) {
					skip = j - right[txt.charAt(i + j)];
					if (skip < 1) skip = 1;
					break;
				}
			if (skip == 0) return i;
		}
		return n;
	}
}
