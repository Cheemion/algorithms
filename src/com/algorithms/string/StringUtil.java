package com.algorithms.string;

import java.util.Arrays;

public class StringUtil {
	
	
	public static void main(String[] args) {
		System.out.println(lrs("asa1sasa1zxcvasdfwefasfv234234rasdfq234rfasdgqwerf23rfasz34rq=asdgfasgdfhasdkjfhkajsdhjkfhasdfhjkasdhfjkashdjkfasdfasdfasdfasdfasdfasdf"));
	}
	
	public static String[] suffixes(String s) {
		int n = s.length();
		String[] suffixes = new String[n];
		for (int i = 0; i < n; i++) 
			suffixes[i] = s.substring(i, n); // endIndex is exclusive
		return suffixes;
	}
	
	//longestCommonPrefix
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
	
}
