package com.algorithms.elementary;
import static com.algorithms.sort.Sorts.*;

import java.util.NoSuchElementException;

/**
 * 基于heap（堆）的priorityQueue
 * 可以参考堆排序  @HeapSort
 * @author altro
 *
 * @param <T>
 */
public class MaxPriorityQueue<T extends Comparable<T>> {
	
	public static void main(String[] args) {
		MaxPriorityQueue<Integer> pq = new MaxPriorityQueue();
		pq.insert(10);
		pq.insert(101);
		pq.insert(1011);
		pq.insert(1012);
		pq.insert(1013);
		pq.insert(1014);
		System.out.println("max number :" + pq.delMax());
		System.out.println("max number :" + pq.delMax());
		System.out.println("max number :" + pq.delMax());
		System.out.println("max number :" + pq.delMax());
		System.out.println("max number :" + pq.delMax());
		System.out.println("max number :" + pq.delMax());
		System.out.println("max number :" + pq.delMax());
	}
	
	private static int DEFAULT_CAPACITY = 4;
	private int size = 0;
	//第一个元素拿来记录这个queue的size
	private T[] base;
	private int capacity;
	
	public MaxPriorityQueue(int capacity) {
		base = (T[]) new Comparable[capacity + 1];
		this.capacity = capacity;
	}
	public MaxPriorityQueue() {
		base = (T[]) new Comparable[DEFAULT_CAPACITY + 1];
		this.capacity = DEFAULT_CAPACITY;
	}
	public void insert(T t) {
		if (size == capacity) {
			capacity = capacity * 2;
			resize(capacity);
		}
		base[++size] = t;
		swim(base, size);
	}
	public T max(){
		return base[1];
	}
	public T delMax() {
		if (isEmpty()) throw new NoSuchElementException();
		T value = base[1];
		exch(base, 1, size--);
		sink(base, 1, size);
		return value;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
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
	
	@SuppressWarnings("unchecked")
	private void resize(int newSize) {
		T[] oldBase = base;
		base = (T[]) new Comparable[newSize + 1];
		for (int i = 1; i <= size; i++) 
			base[i] = oldBase[i];
	}
	
}
