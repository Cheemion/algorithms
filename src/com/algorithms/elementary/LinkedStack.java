package com.algorithms.elementary;

import java.util.Iterator;

public class LinkedStack<T> implements Stack<T>{
	
	Node first = null;
	private int size = 0;
	public LinkedStack(){};
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedStackIterator();
	}

	private class LinkedStackIterator implements Iterator<T> {
		Node<T> node = first;
		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public T next() {
			Node<T> oldNode = node;
			node = node.next;
			return oldNode.t;
		}
		
	}
	
	@Override
	public void push(T t) {
		first = new Node<T>(t, first);
		size++;
	}

	@Override
	public T pop() {
		Node<T> oldFirst = first;
		first = first.next;
		size--;
		return oldFirst.t;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public int size() {
		return size;
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