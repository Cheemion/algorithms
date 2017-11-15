package com.algorithms.string;

import com.algorithms.sort.InsertionSort;
import static com.algorithms.sort.Sorts.exch;

import java.util.Arrays;
/**
 * most-significant-digit-first string sort
 * @author altro
 * most-significant means �ַ����ĵ�һλ
 */
public class MSD {
	
	
	public static void main(String[] args) {
		String s = "111";
		String s1 = "444";
		String s3 = "333";
		String s4 = "555";
		String ss[] = new String[4];
		ss[0] = s;
		ss[1] = s1;
		ss[2] = s3;
		ss[3] = s4;
		
		System.out.println(Arrays.toString(ss));
		sort(ss);
		System.out.println(Arrays.toString(ss));
		
	}
	
	private static int radix = 256;
	private static final int threshold = 2;
	
	private static int charAt(String s, int d) {
		if (d < s.length()) return s.charAt(d);
		else return -1;
	}
	
	//û�� ������������ߵ�aux�ǿ��Ը��õ�
	//low and hi is inclusive
	public static void sort(String[] ss, String[] aux, int low, int hi, int d) {
		
		if (hi <= low + threshold) {
			insertionSort(ss, low, hi, d);
			return;
		}
		
		int[] count = new int[radix + 2]; //��������β�����ַ��� ��ŵ� 0 ��1��λ��
		
		for (int i = low; i <= hi; i++) { //����Ƶ��
			count[charAt(ss[i], d) + 2]++;
		}
		
		for (int i = 0; i < radix + 1; i++) { //����Ƶ��
			count[i + 1] = count[i + 1] + count[i];
		}
		
		for (int i = low; i <= hi; i++) { //���Ƶ���������
			aux[count[charAt(ss[i], d) + 1]++] = ss[i]; 
		}
		
		for (int i = low; i <= hi; i++) { //��aux���鸴�Ƶ���ʵ����
			ss[i] = aux[i - low];          //��д
		}
		
		for (int r = 0; r < radix; r++) {  //�ݹ���� ����ÿ���ַ�
			if (count[r] != count[r + 1])
				sort(ss, aux, low + count[r], low + count[r + 1] - 1, d + 1);
		}
	}
	
	
	public static void sort(String[] ss) {
		sort(ss, new String[ss.length], 0, ss.length - 1, 0);
	}
	
	//�������� 
	private static void insertionSort(String[] a, int lo, int hi, int d) {
		for (int i = lo; i <= hi ; i++) {
			for (int j = i ; j > lo && less(a[j], a[j - 1], d); j--)
				exch(a, j, j - 1);
		}
	}
	
	private static boolean less(String v, String w, int d) {
		return v.substring(d).compareTo(w.substring(d)) < 0;
	}
	
}
