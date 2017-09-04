package com.algorithms.sort;

/**
 * 堆排序
 * @author altro
 *	index 从1开始
 */
import static com.algorithms.sort.Sorts.*;

import java.util.Arrays;

public class HeapSort {
	
	public static void main(String[] args) {
		Integer[] a = new Integer[]{ -1, 3, 4, 1, 10, 22, 0 ,33, 1};
		System.out.println(Arrays.toString(a));
		sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	
	//第一个元素不会排序
	public static void sort(Comparable[] a) {
		//堆有序
		for (int i = (a.length - 1) / 2; i >= 1; i--)
			sink(a, i, a.length - 1);
		
		for (int n = a.length - 1; n > 1; n--) {
			exch(a, 1, n); //最大的数拿到末尾
			sink(a, 1, n - 1); //把第一个数sink下去
		}
	}

	// index 从 1开始
	// max 最大的值往上走
	private static void swim(Comparable[] a, int target) {
		while (target > 1 && less(a[target / 2], a[target])) {
			exch(a, target, target / 2);
			target = target / 2;
		}
	}
	//max 
	//n means the last element 's index in the a
	private static void sink(Comparable[] a, int target, int n) {
		while(target * 2 <= n) {  //target * 2 means node的子左节点的是有值的
			int childNode = target * 2;
			if (childNode + 1 <= n) //右结点有值的话
				if (less(a[childNode], a[childNode + 1])) //右结点比左结点大的话
					childNode = childNode + 1;
			
			if (less(a[childNode] ,a[target])) //如果子元素都没有比父元素大的话
				break;
			
			exch(a, target, childNode);
			target = childNode;
		}
	}
	
}
