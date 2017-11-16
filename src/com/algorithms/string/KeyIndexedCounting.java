package com.algorithms.string;

import java.util.Arrays;


/**
 * ����Index�ӿ����������
 * �ǳ��ǳ��� ֻ��Ҫ linear time
 * @author altro
 *
 */
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
		
		for (int i = 0; i < n; i++) //������ֵ�ÿһ��index���ֵ�Ƶ��  (n)
			count[a[i].getIndex() + 1]++;
		
		for (int i = 0; i < radix; i++) {  
			count[i + 1] = count[i] + count[i + 1]; //�ۼ�����  (r)
		}
		
		for (int i = 0; i < n; i++) { //�����ۼӵ����飬������������
			aux[count[a[i].getIndex()]++] = a[i];  //(n)
		}
		
		for (int i = 0; i < n; i++) //(n)
			a[i] = (T) aux[i];
	}
	
}
