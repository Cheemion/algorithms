package com.algorithms.elementary;

import java.util.NoSuchElementException;
import static com.algorithms.sort.Sorts.less;
/**
 * 可以通过index 找到那个key 并且修改key或者一些其他的操作
 * values 存放真实的
 * @author altro
 *
 * @param <T>
 */
public class IndexMinPriorityQueue<T extends Comparable<T>> {
	
	private int cursor; // next heap position to be inserted
	//第一个元素拿来记录这个queue的size
	private T[] values;
	private Integer[] pq; //pq 其实就是heap中的index(index 从1 开始) ->>  实际存储在values中的对象的index的映射  就是qp
	private Integer[] qp; //qp 是每个对象绑定的那个key  -》  这个对象在heap中的index
	//所以通过   k  via qp -> heapIndx,  heapIndex  via  pq -> values' index
	private int capacity;
	
	public static void main(String[] args) {
		IndexMinPriorityQueue<Integer> impq = new IndexMinPriorityQueue<>(10);
		impq.insert(1, 1);
		impq.insert(3, 3);
		impq.insert(6, 6);
		impq.insert(7, 0);
		
		System.out.println(impq.contains(0));
		System.out.println(impq.delMin());
		System.out.println(impq.delMin());
		System.out.println(impq.delMin());
		System.out.println(impq.delMin());
		System.out.println(impq.size());
		
		impq.insert(1, 1);
		impq.insert(3, 3);
		impq.insert(6, 6);
		impq.insert(7, 0);
		impq.decreaseKey(1, -100);
		impq.decreaseKey(3, -1000);
		
	}
	
	public IndexMinPriorityQueue(int capacity) {
		values = (T[]) new Comparable[capacity];
		pq = new Integer[capacity + 1];
		qp = new Integer[capacity];
		this.capacity = capacity;
	}
	
	public T peek(int k) {
		return values[k];
	}
	
	public void insert(int k, T t) {
		if (k < 0 || k >= capacity) throw new RuntimeException("k: " + k +"beyond the capacity");
		values[k] = t;
		pq[++cursor] = k;
		qp[k] = cursor;
		swim(cursor);
	}
	
	public void decreaseKey(int k, T t) {
		if (!contains(k)) throw new NoSuchElementException("no such index " + k);
		values[k] = t;
		int pqIndex = qp[k];
		swim(pqIndex);
	}
	
	public boolean contains(int k) {
		return values[k] != null;
	}
	
	public int delMin() {
		if (isEmpty()) throw new NoSuchElementException();
		int i = pq[1];
		exch(1, cursor--);
		
		values[i] = null;
		qp[i] = null;
		pq[cursor + 1] = null;
		
		sink(1, cursor);
		return i;
	}
	
	public boolean isEmpty() {
		return cursor == 0;
	}
	
	public int size() {
		return cursor;
	}
	
	//pq  其实就是heap中的index(index 从1 开始) ->>  实际存储在values中的对象的index的映射 
	//qp  是每个对象绑定的那个key  -》  对象在heap中的index
	//target 从 1开始 target 是pq的targets
	private void swim(int target) {
		while (target > 1 && less(values[pq[target]], values[pq[target / 2]])) {
			exch(target, target / 2);
			target = target / 2;
		}
	}
	
	private void exch(int i, int j) {
		int temp = qp[pq[i]];
		qp[pq[i]] = qp[pq[j]];
		qp[pq[j]] = temp; 
		
		temp = pq[j];
		pq[j] = pq[i];
		pq[i] = temp;
	}

	//max 
	//n means the last element 's index in the a
	private void sink(int target, int n) {
		while(target * 2 <= n) {  //target * 2 means node的子左节点的是有值的
			int childNode = target * 2;
			if (childNode + 1 <= n) //右结点有值的话
				if (less(values[pq[childNode + 1]], values[pq[childNode]])) //右结点比左结点大的话
					childNode = childNode + 1;
			
			if (less(values[pq[target]] ,values[pq[childNode]])) //如果子元素都没有比父元素大的话
				break;
			
			exch(target, childNode);
			target = childNode;
		}
	}
}
