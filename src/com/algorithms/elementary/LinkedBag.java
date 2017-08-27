package com.algorithms.elementary;

import java.util.Iterator;


public class LinkedBag<T> implements Bag<T>{
	
	private Node<T> head;
	private int number = 0;
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedBagIterator();
	}

	private class LinkedBagIterator implements Iterator<T> {
		private Node<T> node = head;
		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public T next() {
			Node<T> oldHead = node;
			node = node.next;
			return oldHead.t;
		}
		
	}
	
	@Override
	public void add(T t) {
		head = new Node<T>(t, head);
		number++;
	}

	@Override
	public boolean isEmpty() {
		return number == 0;
	}

	@Override
	public int size() {
		return number;
	}
	
	private class Node<T> {
		Node(T t, Node next) {
			this.t = t;
			this.next = next;
		}
		T t;
		Node next;
	}
}
