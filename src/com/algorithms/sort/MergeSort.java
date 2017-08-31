package com.algorithms.sort;

import static com.algorithms.sort.Sorts.*;

import java.util.Arrays;
/**
 * MergeSort 是 stable的
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
	
	//Time : N log(N) & wasting space that proportional to N
	public static void bottomUpSort1(Comparable[] a) {
		int n = a.length;
		Comparable[] aux = new Comparable[a.length];
		for (int step = 1; step < n; step = step * 2) {
			for (int j = 0; j < n; j = j + step) {
				System.out.println(n - 1 - j);
				int realStep = Math.min(step, n - 1 - j);
				merge(a, aux, j, (j + (realStep / 2))>j?(j + (realStep / 2))-1:(j + (realStep / 2)), j + realStep);
				System.out.println(Arrays.toString(a));
			}
		}
	}
	
	public static void bottomUpSort(Comparable[] a) {
		int n = a.length;
		Comparable[] aux = new Comparable[a.length];
		//  size 是指 左数组的大小，和右数组的大小
		for (int size = 1; size < n; size = size + size) {
			for (int low = 0; low < n; low = low + size + size) {
				merge(a, aux, low, low + size - 1, Math.min(low + size + size - 1, n - 1));
			}
		}
	}
	
	
	
	//mid 的左右都已经是有序的了，
	//这个是merge方法
	//a 和辅助 数组的长度需要一致
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
			aux[k] = a[k];   //把元素复制到辅助数组上
		
		for (int k = low; k <= hi; k++) {
			
			//前面2个判断条件一定要在前面。
			//要不然下面比较的时候 会出现空指针异常
			//因为已经有可能j 已经超出了hi的大小
			if (i > middle) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(aux[i],aux[j])) a[k] = aux[i++]; //
			else a[k] = aux[j++];
		}
	}
	
}
