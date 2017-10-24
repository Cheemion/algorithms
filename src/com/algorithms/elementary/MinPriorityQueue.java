package com.algorithms.elementary;
import static com.algorithms.sort.Sorts.*;

import java.util.NoSuchElementException;
public class MinPriorityQueue<T extends Comparable<T>> {
	
	private static int DEFAULT_CAPACITY = 10;
	private int size = 0;
	//第一个元素拿来记录这个queue的size
	private T[] base;
	private int capacity;
	
	public MinPriorityQueue(int capacity) {
		base = (T[]) new Comparable[capacity + 1];
		this.capacity = capacity;
	}
	public MinPriorityQueue() {
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
	public T min(){
		return base[1];
	}
	public T delMin() {
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
	private void swim(Comparable[] a, int target) {
		while (target > 1 && less(a[target], a[target / 2])) {
			exch(a, target, target / 2);
			target = target / 2;
		}
	}
	//max 
	//n means the last element 's index in the a
	private void sink(Comparable[] a, int target, int n) {
		while(target * 2 <= n) {  //target * 2 means node的子左节点的是有值的
			int childNode = target * 2;
			if (childNode + 1 <= n) //右结点有值的话
				if (less(a[childNode + 1], a[childNode])) //右结点比左结点大的话
					childNode = childNode + 1;
			
			if (less(a[target] ,a[childNode])) //如果子元素都没有比父元素大的话
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
