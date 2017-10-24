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
	private int[] pq; //pq ��ʵ����heap�е�index(index ��1 ��ʼ) ->>  ʵ�ʴ洢��values�еĶ����index��ӳ�� 
	private int[] qp; //qp ��ÿ������󶨵��Ǹ�key  -��  ���������heap�е�index
	//����ͨ��   k  via qp -> heapIndx,  heapIndex  via  pq -> values' index
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
	
	//pq  ��ʵ����heap�е�index(index ��1 ��ʼ) ->>  ʵ�ʴ洢��values�еĶ����index��ӳ�� 
	//qp  ��ÿ������󶨵��Ǹ�key  -��  ������heap�е�index
	//target �� 1��ʼ target ��pq��targets
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
		while(target * 2 <= n) {  //target * 2 means node������ڵ������ֵ��
			int childNode = target * 2;
			if (childNode + 1 <= n) //�ҽ����ֵ�Ļ�
				if (less(a[childNode + 1], a[childNode])) //�ҽ��������Ļ�
					childNode = childNode + 1;
			
			if (less(a[target] ,a[childNode])) //�����Ԫ�ض�û�бȸ�Ԫ�ش�Ļ�
				break;
			
			exch(a, target, childNode);
			target = childNode;
		}
	}
}
