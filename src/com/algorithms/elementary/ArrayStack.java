package com.algorithms.elementary;
/**
 * 先进先出
 */
import java.util.Iterator;

public class ArrayStack<T> implements Stack<T>{
	
	public static void main(String[] args) {
		Stack<Integer> stack = new LinkedStack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(1);
		stack.pop();
		stack.push(100);
		stack.push(200);
		stack.pop();
		
		for (Integer i : stack)
			System.out.println(i);
	}
	
	
	private int capacity;
	private static int DEFAULT_CAPACITY = 10;
	private T[] base;
	private int index = 0; // 数组的元素的数量
	
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
