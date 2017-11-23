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
		for (int c = 0; c < R; c++) { //��ʼ���е�ֵ��Ϊ-1
			right[c] = -1;
		}
		for (int j = 0; j < m; j++) { //pat�����е�char,��pattern�е�λ�á� [0, length - 1]
			right[pat.charAt(j)] = j;
		}
	}
	
	public int search(String txt) {
		int n = txt.length();
		int m = pat.length();
		int skip;//��Ҫ�����ĳ���
		for (int i = 0; i <= n - m; i += skip) { // i�����ı��У����������ƶ�
			skip = 0;
			for (int j = m - 1; j >= 0; j--) //j��pattern�е�λ�ã� ����������ƶ�
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
