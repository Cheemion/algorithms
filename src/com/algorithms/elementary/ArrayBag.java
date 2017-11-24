package com.algorithms.elementary;

import java.util.Iterator;

public class ArrayBag<T> implements Bag<T>{
	
	public static void main(String[] args) {
		Bag<Integer> bag = new LinkedBag<>();
		bag.add(1);
		bag.add(2);
		bag.add(3);
		bag.add(4);
		bag.add(5);
		for (Integer a : bag)
			System.out.println(a);
	}
	
	
	private static int DEFAULT_CAPACITY = 16;
	private int capacity;
	private T[] base;
	private int index;//元素的数量
	
	public ArrayBag() {
		this.capacity = DEFAULT_CAPACITY;
		base = (T[]) new Object[capacity];
	}
	
	public ArrayBag(int capacity) {
		this.capacity = capacity;
		base = (T[]) new Object[capacity];
	}
	
	private class ArrayBagIterator implements Iterator<T> {
		
		int amount = index;
		
		@Override
		public boolean hasNext() {
			return amount > 0;
		}

		@Override
		public T next() {
			return base[--amount];
		}
		
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ArrayBagIterator();
	}

	@SuppressWarnings("unchecked")
	private void resize(int newSize) {
		T[] oldBase = base;
		base = (T[]) new Object[newSize];
		for (int i = 0; i < index; i++) 
			base[i] = oldBase[i];
	}
	
	@Override
	public void add(T t) {
		if(base.length == index) {
			capacity = capacity * 2;
			resize(capacity);
		}
		base[index++] = t;
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(T t : this)
			sb.append(t + " ");
		return sb.toString();
	}
}
