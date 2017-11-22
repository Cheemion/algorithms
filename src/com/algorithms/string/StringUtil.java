package com.algorithms.string;

import java.util.Arrays;

public class StringUtil {
	
	
	public static void main(String[] args) {
		
		System.out.println(lrs("banana"));
		System.out.println(Arrays.toString(suffixes("banana")));
		
	}
	
	
	public static String[] suffixes(String s) {
		int n = s.length();
		String[] suffixes = new String[n];
		for (int i = 0; i < n; i++) 
			suffixes[i] = s.substring(i, n); // endIndex is exclusive
		return suffixes;
	}
	
	//longest Common Prefix
	public static int lcp(String s, String t) {
		int n = Math.min(s.length(), t.length());
		for (int i = 0; i < n; i++)
			if (s.charAt(i) != t.charAt(i)) 
				return i;
		return n;
	}
	
	// longeset repeated substring
	public static String lrs(String s) {
		int n = s.length();
		
		String[] suffixes = suffixes(s);
		QuickSort3Way.sort(suffixes); // 这里可以使用任何sort 可以使用 quickSort3way
		
		String lrs = "";
		for (int i = 0; i < n - 1; i++) {
			int len = lcp(suffixes[i], suffixes[i + 1]);
			if (len > lrs.length()) lrs = suffixes[i].substring(0, len);
		}
		
		return lrs;
	}
	
	public static int bruteForceSearch(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();
		for (int i = 0; i <= N - M; i++) {
			int j = 0;
			for (; j < M; j++)
				if (txt.charAt(i + j) != pat.charAt(j))
					break;
			if (j == M) return i;
		}
		return N;
	}
	
}
