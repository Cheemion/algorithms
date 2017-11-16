package com.algorithms.string;

import java.util.Arrays;

/**
 * string sort algorithms
 * 从字符串的尾部开始排序
 * @author altro
 * least significant digit radix sorts, least significant 代表的是 字符串的最后一位
 * 时间复杂度是  wn , w的意思是字符串的长度
 */
public class LSD {
	
	public static void main(String[] args) {
		
		System.out.println((int)("1".charAt(0)));
		System.out.println((int)("2".charAt(0)));
		System.out.println((int)("3".charAt(0)));
		System.out.println((int)("4".charAt(0)));
		System.out.println((int)("s".charAt(0)));
		
		
		String s = "222";
		String s1 = "szs";
		String s3 = "2dd";
		String ss[] = new String[3];
		ss[0] = s;
		ss[1] = s1;
		ss[2] = s3;
		
		System.out.println(Arrays.toString(ss));
		sort(ss, 3);
		System.out.println(Arrays.toString(ss));
	}
	
	public static void sort(String[] a, int w) { //w means fixed-length strings
		
		int radix = 256; //2^8
		int n = a.length;
		String[] aux = new String[n];
		
		for (int d = w - 1; d >= 0; d--) {
			
			int[] count = new int[radix + 1];
			
			for (int i = 0; i < n; i++)
				count[a[i].charAt(d) + 1]++;
			
			for (int r = 0; r < radix; r++)
				count[r + 1] += count[r];
			
			for (int i = 0; i < n; i++)
				aux[count[a[i].charAt(d)]++] = a[i];
			
			for (int i = 0; i < n; i++)
				a[i] = aux[i];
			
		}
	}
	
}
