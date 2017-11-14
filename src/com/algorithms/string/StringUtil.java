package com.algorithms.string;

public class StringUtil {
	
	public static String[] suffixes(String s) {
		int n = s.length();
		String[] suffixes = new String[n];
		for (int i = 0; i < n; i++) 
			suffixes[i] = s.substring(i, n); // endIndex is exclusive
		return suffixes;
	}
	
	
	public static int longestCommonPrefix(String s, String t) {
		int n = Math.min(s.length(), t.length());
		for (int i = 0; i < n; i++)
			if (s.charAt(i) != t.charAt(i)) 
				return i;
		return n;
	}
	
	private static int charAt(String s, int d) {
		if (d < s.length()) return s.charAt(d);
		else return -1;
	}
}
