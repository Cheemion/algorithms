package com.algorithms.sort;

import static com.algorithms.sort.Sorts.*;

import java.util.Arrays;
/**
 * MergeSort �� stable��
 * @author kim
 *
 */
public class MergeSort {
	
	public static void main(String[] args) {
		Integer[] a = new Integer[]{ 2, 3, 4, 1, 10, 22, 0 ,33, 1};
		System.out.println(Arrays.toString(a));
		bottomUpSort(a);
		System.out.println(Arrays.toString(a));
	}
	
	//Time : N log(N) & wasting space that proportional to N
	public static void sort(Comparable[] a) {
		sort(a, new Comparable[a.length], 0, a.length - 1);
	}
	
	public static void bottomUpSort(Comparable[] a) {
		int n = a.length;
		Comparable[] aux = new Comparable[a.length];
		//  size ��ָ ������Ĵ�С����������Ĵ�С
		for (int size = 1; size < n; size = size + size) {
			for (int low = 0; low < n; low = low + size + size) {
				merge(a, aux, low, low + size - 1, Math.min(low + size + size - 1, n - 1));
			}
		}
	}
	
	
	//mid �����Ҷ��Ѿ���������ˣ�
	//�����merge����
	//a �͸��� ����ĳ�����Ҫһ��
	/**            i            j  
 	 *             []  []  []  [ ]  []
	 *             low     mid    hi   
	 */
	private static void sort(Comparable[] a, Comparable[] aux, int low, int hi) {
		if (low >= hi) return;
		int middle = low + (hi - low) / 2;
		sort(a, aux, low, middle); //sort left
		sort(a, aux, middle + 1, hi); // sort right
		merge(a, aux, low, middle, hi);
	}
	
	/**            i              j  
 	 *            [ ]  [ ]  [ ]  [ ]  [ ]
	 *            low       mid        hi   
	 */
	private static void merge(Comparable[] a, Comparable[] aux,int low, int middle, int hi) {
		int i = low;
		int j = middle + 1;
		for (int k = low; k <= hi; k++)
			aux[k] = a[k];   //��Ԫ�ظ��Ƶ�����������
		
		for (int k = low; k <= hi; k++) {
			
			//ǰ��2���ж�����һ��Ҫ��ǰ�档
			//Ҫ��Ȼ����Ƚϵ�ʱ�� ����ֿ�ָ���쳣
			//��Ϊ�Ѿ��п���j �Ѿ�������hi�Ĵ�С
			if (i > middle) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(aux[i],aux[j])) a[k] = aux[i++]; //
			else a[k] = aux[j++];
		}
	}
	
}