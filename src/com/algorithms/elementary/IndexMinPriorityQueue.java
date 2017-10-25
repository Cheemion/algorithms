package com.algorithms.elementary;

import java.util.NoSuchElementException;
import static com.algorithms.sort.Sorts.less;
/**
 * ����ͨ��index �ҵ��Ǹ�key �����޸�key����һЩ�����Ĳ���
 * values �����ʵ��
 * @author altro
 *
 * @param <T>
 */
public class IndexMinPriorityQueue<T extends Comparable<T>> {
	
	private int cursor; // next heap position to be inserted
	//��һ��Ԫ��������¼���queue��size
	private T[] values;
	private Integer[] pq; //pq ��ʵ����heap�е�index(index ��1 ��ʼ) ->>  ʵ�ʴ洢��values�еĶ����index��ӳ��  ����qp
	private Integer[] qp; //qp ��ÿ������󶨵��Ǹ�key  -��  ���������heap�е�index
	//����ͨ��   k  via qp -> heapIndx,  heapIndex  via  pq -> values' index
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
	
	//pq  ��ʵ����heap�е�index(index ��1 ��ʼ) ->>  ʵ�ʴ洢��values�еĶ����index��ӳ�� 
	//qp  ��ÿ������󶨵��Ǹ�key  -��  ������heap�е�index
	//target �� 1��ʼ target ��pq��targets
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
		while(target * 2 <= n) {  //target * 2 means node������ڵ������ֵ��
			int childNode = target * 2;
			if (childNode + 1 <= n) //�ҽ����ֵ�Ļ�
				if (less(values[pq[childNode + 1]], values[pq[childNode]])) //�ҽ��������Ļ�
					childNode = childNode + 1;
			
			if (less(values[pq[target]] ,values[pq[childNode]])) //�����Ԫ�ض�û�бȸ�Ԫ�ش�Ļ�
				break;
			
			exch(target, childNode);
			target = childNode;
		}
	}
}
