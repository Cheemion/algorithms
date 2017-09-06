package com.algorithms.sort;

import java.util.Date;
import java.util.Random;

public class SortingSpeedTest {

	public static void main(String[] args) {
		Random rand = new Random();
		int n = 10000000;
	    Integer[] ints = new Integer[n];
	    for (int i = 0; i < n; i++) 
	    	ints[i] = rand.nextInt();
	    
	    long before;
	    
/*	    before = new Date().getTime();
	    Selection.sort(ints);
	    System.out.println("Selection is using " + (new Date().getTime() - before) + "milliseconds");
	    
	    Sorts.shuffle(ints);
	    
	    before = new Date().getTime();
	    InsertionSort.sort(ints);
	    System.out.println("insertionSort is using " + (new Date().getTime() - before) + "milliseconds");*/
	    
	    Sorts.shuffle(ints);
	    before = new Date().getTime();
	    ShellSort.sort(ints);
	    System.out.println("ShellSort is using " + (new Date().getTime() - before) + "milliseconds");
	    
	    Sorts.shuffle(ints);
	    before = new Date().getTime();
	    MergeSort.sort(ints);
	    System.out.println("MergeSort is using " + (new Date().getTime() - before) + "milliseconds");
	    
	    Sorts.shuffle(ints);
	    before = new Date().getTime();
	    QuickSort.sort(ints);
	    System.out.println("QuickSort is using " + (new Date().getTime() - before) + "milliseconds");
	    
	    Sorts.shuffle(ints);
	    before = new Date().getTime();
	    ImprovedQuickSort.sort(ints);
	    System.out.println("ImprovedQuickSort is using " + (new Date().getTime() - before) + "milliseconds");
	    
	    Sorts.shuffle(ints);
	    before = new Date().getTime();
	    QucikSort3Way.sort(ints);
	    System.out.println("QucikSort3Way is using " + (new Date().getTime() - before) + "milliseconds");
	    
	    Sorts.shuffle(ints);
	    before = new Date().getTime();
	    HeapSort.sort(ints);
	    System.out.println("HeapSort is using " + (new Date().getTime() - before) + "milliseconds");
	}
}
