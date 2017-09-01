package com.algorithms.sort;

import static com.algorithms.sort.Sorts.*;

import java.util.Arrays;
import java.util.Comparator;
//��������
//���������˳�����ܻ��бȽϴ�Ĳ��
// һ��һ���ƣ�����һ������һ��
public class InsertionSort {
	
	public static void main(String[] args) {
		Integer[] a = new Integer[]{ 2, 3, 4, 1, 10, 22, 0 ,33, 1};
		System.out.println(Arrays.toString(a));
		sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	// O(n^2) ������
	// ��õ���� O(n)
	public static void sort(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			for (int j = i ; j > 0; j--) {
				if (less(a[j], a[j - 1])) 
					exch(a, j, j - 1);
				else
					break;
			}
		}
	}
	
	public static void sort(Comparable[] a, int low, int hi) {
		for (int i = low; i <= hi ; i++) {
			for (int j = i ; j > low; j--) {
				if (less(a[j], a[j - 1])) 
					exch(a, j, j - 1);
				else
					break;
			}
		}
	}
	
	// O(n^2) ������
	// ��õ���� O(n)
	public static <T> void sort(T[] a, Comparator<? super T> cmp) {
		for (int i = 1; i < a.length; i++) {
			for (int j = i ; j > 0; j--) {
				if (less(a[j], a[j - 1], cmp)) 
					exch(a, j, j - 1);
				else
					break;
			}
		}
	}
}
