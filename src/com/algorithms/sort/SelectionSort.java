package com.algorithms.sort;

/**
 * ѡ������
 * @author altro
 *
 */
import static com.algorithms.sort.Sorts.*;

import java.util.Arrays;
import java.util.Comparator;
//ѡ������,��ͷ��β����һ�飬ѡ����һ����õģ�
//�ٴӵڶ���λ�ñ���һ��ѡ���ڶ�����С�� 
//ect
public class SelectionSort {
	
	public static void main(String[] args) {
		Integer[] a = new Integer[]{ 2, 3, 4, 1, 10, 22, 0 ,33, 1};
		System.out.println(Arrays.toString(a));
		sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	// O(n^2)  �ǳ���
	public static void sort(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i; j < a.length; j++) {
				if (less(a[j], a[min])) min = j;
			}
			exch(a, i, min);
		}
	}
	
	public static <T> void sort(T[] a, Comparator<? super T> cmp) {
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i; j < a.length; j++) {
				if (less(a[j], a[min], cmp)) min = j;
			}
			exch(a, i, min);
		}
	}
	
}
