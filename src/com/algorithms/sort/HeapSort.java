package com.algorithms.sort;

/**
 * ������
 * @author altro
 *	index ��1��ʼ
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
	
	
	//��һ��Ԫ�ز�������
	public static void sort(Comparable[] a) {
		//������
		for (int i = (a.length - 1) / 2; i >= 1; i--)
			sink(a, i, a.length - 1);
		
		for (int n = a.length - 1; n > 1; n--) {
			exch(a, 1, n); //�������õ�ĩβ
			sink(a, 1, n - 1); //�ѵ�һ����sink��ȥ
		}
	}

	// index �� 1��ʼ
	// max ����ֵ������
	private static void swim(Comparable[] a, int target) {
		while (target > 1 && less(a[target / 2], a[target])) {
			exch(a, target, target / 2);
			target = target / 2;
		}
	}
	//max 
	//n means the last element 's index in the a
	private static void sink(Comparable[] a, int target, int n) {
		while(target * 2 <= n) {  //target * 2 means node������ڵ������ֵ��
			int childNode = target * 2;
			if (childNode + 1 <= n) //�ҽ����ֵ�Ļ�
				if (less(a[childNode], a[childNode + 1])) //�ҽ��������Ļ�
					childNode = childNode + 1;
			
			if (less(a[childNode] ,a[target])) //�����Ԫ�ض�û�бȸ�Ԫ�ش�Ļ�
				break;
			
			exch(a, target, childNode);
			target = childNode;
		}
	}
	
}
