package com.algorithms.elementary;

import java.util.Iterator;

public class LinkedQueue<T> implements Queue<T>{
	
	private Node<T> first;
	private Node<T> last;
	private int size = 0;
	
	public LinkedQueue(){}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedQueueIterator();
	}

	private class LinkedQueueIterator implements Iterator<T> {
		
		private Node<T> node = first;
		
		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public T next() {
			Node<T> oldFirst = node;
			node = oldFirst.next;
			return oldFirst.t;
		}
		
	}
	
	@Override
	public void enqueue(T t) {
		Node<T> oldLast = last;
		last = new Node<T>(t, null);
		if (isEmpty()) first = last;
		else oldLast.next = last;
		size++;
	}

	@Override
	public T dequeue() {
		Node<T> node = first;
		first = first.next;
		if (isEmpty()) last = null;
		size--;
		return node.t;
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
