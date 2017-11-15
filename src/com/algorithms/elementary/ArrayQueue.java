package com.algorithms.elementary;

import java.util.Iterator;

public class ArrayQueue<T> implements Queue<T>{
	
	
	public static void main(String[] args) {
		Queue<Integer> queue = new ArrayQueue();
		queue.enqueue(1);
		queue.enqueue(1);
		queue.enqueue(1);
		queue.dequeue();
		queue.enqueue(2);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(3);
		queue.enqueue(3);
		queue.dequeue();
		for(int s : queue)
			System.out.println(s);
	}

	private static int DEFAULT_CAPACITY = 10;
	private int head = 0;
	private int tail = 0;
	private int capacity;
	private T[] base;
	
	public ArrayQueue(int capacity) {
		this.capacity = capacity;
		base = (T[]) new Object[capacity];
	}
	
	public ArrayQueue() {
		this.capacity = DEFAULT_CAPACITY;
		base = (T[]) new Object[capacity];
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ArrayQueueIterator();
	}
	
	//并没有控制多线程问题
	private class ArrayQueueIterator implements Iterator<T> {
		
		int first = head;
		int last = tail;
		@Override
		public boolean hasNext() {
			return last - first > 0;
		}

		@Override
		public T next() {
			return base[first++];
		}
		
	}
	
	@Override
	public void enqueue(T t) {
		if (tail == capacity) {
			if (head == 0) {
				capacity = capacity * 2;
				resize(capacity);
			} else {
				reset();
			}
		}
		base[tail++] = t;
	}

	@Override
	public T dequeue() {
		return base[head++];
	}

	@Override
	public boolean isEmpty() {
		return (tail - head) <= 0;
	}

	@Override
	public int size() {
		return tail - head;
	}

	private void reset() {
		for(int i = head, j = 0; i < tail; i++, j++) {
			base[j] = base[i];
		}
		tail = size();
		head = 0;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int newSize) {
		T[] oldBase = base;
		base = (T[]) new Object[newSize];
		for (int i = 0; i < oldBase.length; i++) 
			base[i] = oldBase[i];
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(T t : this)
			sb.append(t + " ");
		return sb.toString();
	}
}
