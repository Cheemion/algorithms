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
	private int[] pq; //pq 其实就是heap中的index(index 从1 开始) ->>  实际存储在values中的对象的index的映射 
	private int[] qp; //qp 是每个对象绑定的那个key  -》  这个对象在heap中的index
	//所以通过   k  via qp -> heapIndx,  heapIndex  via  pq -> values' index
	private int capacity;
	
	public IndexMinPriorityQueue(int capacity) {
		values = (T[]) new Comparable[capacity];
		pq = new int[capacity + 1];
		qp = new int[capacity];
		this.capacity = capacity;
	}
	
	public void insert(int k, T t) {
		if (k < 0 || k >= capacity) throw new RuntimeException("k: " + k +"beyond the capacity");
		values[k] = t;
		pq[++cursor] = k;
		qp[k] = cursor;
		swim(cursor);
	}
	
	public void decreaseKey(int k, T t) {
		
	}
	
	public boolean contains(int k) {
		return true;
	}
	
	public T min(){
		return values[1];
	}
	
	public int delMin() {
		if (isEmpty()) throw new NoSuchElementException();
		T value = values[1];
		exch(values, 1, size--);
		sink(values, 1, size);
		return value;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	//pq  其实就是heap中的index(index 从1 开始) ->>  实际存储在values中的对象的index的映射 
	//qp  是每个对象绑定的那个key  -》  对象在heap中的index
	//target 从 1开始 target 是pq的targets
	private int swim(int target) {
		while (target > 1 && less(values[target], values[target / 2])) {
			exch(target, target / 2);
			target = target / 2;
		}
		return target;
	}
	
	
	private void exch(int i, int j) {
		int temp = pq[j];
		pq[j] = pq[i];
		pq[i] = temp;
	}

	//max 
	//n means the last element 's index in the a
	private int sink(Comparable[] a, int target, int n) {
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
}
