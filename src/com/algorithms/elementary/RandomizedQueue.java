package com.algorithms.elementary;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<T> implements Iterable<T> {
	
	
	public static void main(String[] args) {
		RandomizedQueue<Integer> r = new RandomizedQueue();
		r.enqueue(114);
		r.enqueue(422);
		r.enqueue(1);
		r.enqueue(2);
		r.enqueue(3);
		r.enqueue(44);
		
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
		System.out.println(r.sample());
	}
	
	private static Random rand = new Random();
	private int size;
	private Node<T> first;
	public RandomizedQueue() {}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	public void enqueue(T t) {
		if (t == null) throw new IllegalArgumentException();
		first = new Node<>(t, first);
		size++;
	}

	public T dequeue() {
		if (size == 0) throw new NoSuchElementException(); 
		int n = rand.nextInt(size);
		int previsouN = n - 1;
		T value = null;
		if (n == 0) { //第一个元素
			value = first.t;
			first = first.next;
		} else { //其他情况都是允许的
			Node<T> target = first;
			for (int i = previsouN; i > 0; i--, target = target.next);  //选到需要dequeue的对象的之前的那个对象
			Node<T> temp = target.next;
			target.next = target.next.next;
			value = temp.t;
			temp.next = null;
			temp.t = null;
		}
		size--;
		return value;
	}

	public T sample() {
		if (size == 0) throw new NoSuchElementException(); 
		int n = rand.nextInt(size);
		Node<T> target = first;
		for (int i = n; i > 0; i--, target = target.next);
		return target.t;
	}

	private class RandomizedQueueIterator implements Iterator<T> {
		
		int n = size - 1; // 0 到 length - 1
		private T[] base;
		
		RandomizedQueueIterator() {
			base = (T[]) new Object[size];
			int i = 0;
			for (Node<T> node = first; node != null; node = node.next) {
				base[i++] = node.t;
			}
		}
		
		@Override
		public boolean hasNext() {
			return n >= 0;
		}
		
		@Override
		public T next() {
			int targetId = rand.nextInt(n + 1);
			T temp = base[targetId];
			base[targetId] = base[n];
			n--;
			return temp;
		}
		
	}
	
	public Iterator<T> iterator() {
		return new RandomizedQueueIterator();
	}
	
	private class Node<T> {
		Node(T t, Node<T> next) {
			this.t = t;
			this.next = next;
		}
		T t;
		Node<T> next;
	}
}
