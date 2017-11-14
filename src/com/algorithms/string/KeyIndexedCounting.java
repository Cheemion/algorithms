package com.algorithms.string;

import java.util.Arrays;

//根据Index接口来排序对象
public class KeyIndexedCounting {
	
	public static void main(String[] args) {
		
		IndexInteger[] ii = new IndexInteger[10];
		for (int i = 0; i < ii.length; i++) {
			ii[i] = new IndexInteger(9 - i);
		}
		System.out.println(Arrays.toString(ii));
		sort(ii);
		System.out.println(Arrays.toString(ii));
		
	}
	
	private static class IndexInteger implements Index {
		private Integer i;
		public IndexInteger(Integer i) {
			this.i = i;
		}
		
		@Override
		public int getIndex() {
			return i;
		}

		@Override
		public int getRadix() {
			return 10;
		}
		@Override
		public String toString() {
			return "" + i;
		}
	}
	
	
	public static <T extends Index> void sort(T[] a) {
		int radix = a[0].getRadix();
		int n = a.length;
		@SuppressWarnings("unchecked")
		Object[] aux = new Object[n];
		int[] count = new int[radix + 1];
		
		for (int i = 0; i < n; i++) //计算出现的每一种index出现的频率
			count[a[i].getIndex() + 1]++;
		
		for (int i = 0; i < radix; i++) {
			count[i + 1] = count[i] + count[i + 1]; //累加起来
		}
		
		for (int i = 0; i < n; i++) { //根据累加的数组，来排序复制数组
			aux[count[a[i].getIndex()]++] = a[i];
		}
		
		for (int i = 0; i < n; i++)
			a[i] = (T) aux[i];
	}
	
}
