package com.algorithms.elementary;
/**
 * �Ƚ��ȳ�
 */
import java.util.Iterator;

public class ArrayStack<T> implements Stack<T>{
	
	public static void main(String[] args) {
		Queue<Integer> stack = new LinkedQueue<Integer>();
		stack.enqueue(1);
		stack.enqueue(2);
		stack.enqueue(3);
		stack.enqueue(1);
		stack.dequeue();
		stack.enqueue(100);
		stack.enqueue(200);
		stack.dequeue();
		
		for (Integer i : stack)
			System.out.println(i);
	}
	
	private int capacity;
	private static int DEFAULT_CAPACITY = 10;
	private T[] base;
	private int index = 0; // �����Ԫ�ص�����
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		this.capacity = capacity;
		base = (T[]) new Object[capacity];
	}
	
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		this.capacity = DEFAULT_CAPACITY;
		base = (T[]) new Object[capacity];
	}
	
	@Override
	public void push(T t) {
		if(index == capacity) {
			capacity = capacity * 2;
			resize(capacity);
		}
		base[index++] = t;
	}

	@Override
	public T pop() {
		T t = base[--index];
		base[index] = null;
		if (index > 0 && index == capacity / 4) {
			capacity = capacity / 2;
			resize(capacity);
		}
		return t;
	}

	@Override
	public boolean isEmpty() {
		return index == 0;
	}

	@Override
	public int size() {
		return index;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}
	private class StackIterator implements Iterator<T> {
		
		private int i = ArrayStack.this.index;
		
		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public T next() {
			return base[--i];
		}

	}
	
	@SuppressWarnings("unchecked")
	private void resize(int newSize) {
		T[] oldBase = base;
		base = (T[]) new Object[newSize];
		for (int i = 0; i < index; i++) 
			base[i] = oldBase[i];
	}
	
}
