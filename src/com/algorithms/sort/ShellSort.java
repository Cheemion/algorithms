package com.algorithms.sort;

import java.util.Arrays;
import static com.algorithms.sort.Sorts.*;
/**
 * 希尔排序 , like 7 sorted and then 3 sorted and 1 soreted. then it is sorted
 * 减少逆序的数量，排序的本质就是减少逆序的数量。
 * 远距离排序能快速的减少逆序的数量
 * 算法的代码本质其实就是 n个距离的插入排序排序
 * 数组未排序的order的影响对perfance的影响不是很大
 * @author altro
 *
 */
public class ShellSort {
	
	public static void main(String[] args) {
		Integer[] a = new Integer[]{ 2, 3, 4, 1, 10, 22, 0 ,33, 1};
		System.out.println(Arrays.toString(a));
		sort(a);
		System.out.println(Arrays.toString(a));
	}
	
	//Sedgewick 增量序列的最坏时间复杂度为 O(N4/3)；平均时间复杂度约为 O(N7/6)。
	//hi=max(9∗4^n−9∗2^n+1, 2^(n + 2) * (2^(n + 2) - 3)) + 1
	//5 是由N = 0 得出来的
	//28
	private static int INCREMENT_SEQUENCE_SEDGEWICK[] = {
		    1,5,19,41,109,209,505,929,
		    2161,3905,8929,16001,36289,64769,146305,260609,
		    587521,1045505,2354689,4188161,9427969,16764929,37730305,67084289,
		    150958081,268386305,603906049,1073643521};
	
	//Knuth  sequence 3N + 1 , N 是之前的元素
	//O(n^1.5)
	//20
	int INCREMENT_SEQUENCE_KNUTH[] = {
		    1,4,13,40,121,364,1093,3280,
		    9841,29524,88573,265720,797161,2391484,7174453,21523360,
		    64570081,193710244,581130733,1743392200};
	
	//非常快  代码精彪，小数组代码有很好的performance
	private static void sort(Comparable[] a, int[] inSeq) {
		int seqIndex = 0;
		for (; inSeq[seqIndex + 1] < a.length; seqIndex++); 
		for (; seqIndex >= 0; seqIndex--) {
			int increment = inSeq[seqIndex];
			for (int i = increment; i < a.length; i++) {
				 for (int j = i; j - increment >= 0 && less(a[j], a[j - increment]); j = j - increment) 
					exch(a, j - increment, j);
			}
		}
	}
	private static void sort(Comparable[] a) {
		sort(a, INCREMENT_SEQUENCE_SEDGEWICK);
	}
}
