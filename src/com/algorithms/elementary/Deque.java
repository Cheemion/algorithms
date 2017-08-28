package com.algorithms.elementary;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author altro
 *
 * @param <T>
 */
public class Deque<T> implements Iterable<T> {
	   
	  public static void main(String[] args) {
		  Deque<Integer> deque = new Deque<>();
		  deque.addFirst(1);
		  deque.addFirst(12);
		  deque.addLast(100);
		  deque.addFirst(200);
		  deque.addFirst(1100);
		  deque.addLast(100000111);
		  deque.addFirst(50000000);
		  
		  for (int s : deque)
			  System.out.println(s);
	 }
	
	   transient private Node<T> first;
	   transient private Node<T> last;
	   transient private int size = 0;
	   
	   public Deque() {}
	   
	   public void addFirst(T t) {
		   
		   if (t == null)
			   throw new IllegalArgumentException();
		   
		   Node<T> oldFirst = first;
		   first = new Node<>(null, t, oldFirst);
		   if (oldFirst == null)
			   last = first;
		   else
			   oldFirst.previous = first;
		   
		   size++;
	   }

	   public boolean isEmpty() {
		   return size == 0;
	   }
	   
	   public int size() {
		   return size;
	   }

	   public void addLast(T t) {
		   
		   if (t == null)
			   throw new IllegalArgumentException();
		   
		   Node<T> oldLast = last;
		   last = new Node<>(last, t, null);
		   if (oldLast == null)
			   first = last;
		   else 
			   oldLast.next = last;
		   
		   size++;
	   }
	   public T removeFirst() {
		   if (size == 0)
			   throw new NoSuchElementException();
		   Node<T> oldFirst = first;
		   first = oldFirst.next;
		   if (first == null) {
			   last = null;
		   } else {
			   first.previous = null;
		   }
		   T value = oldFirst.t;
		   oldFirst.next = null;
		   oldFirst.t = null;
		   size--;
		   return value;
	   }
	   
	   public T removeLast() {
		   if (size == 0)
			   throw new NoSuchElementException();
		   Node<T> oldLast = last;
		   last = oldLast.previous;
		   if (last == null) {
			   first = null;
		   } else {
			   last.next = null;
		   }
		   T value = oldLast.t;
		   oldLast.previous = null;
		   oldLast.t = null;
		   size--;
		   return value;
	   }
	   
	   
	   public Iterator<T> iterator() {
		   return new DequeIterator();
	   }
	   
	   private class DequeIterator implements Iterator<T> {
		   
		   private Node<T> node = first;
		   
			@Override
			public boolean hasNext() {
				return node != null;
			}
	
			@Override
			public T next() {
				Node<T> temp = node;
				node = node.next;
				return temp.t;
			}
		   
	   }
	   
		private class Node<T> {
			Node(Node<T> previous, T t, Node<T> next) {
				this.t = t;
				this.next = next;
				this.previous = previous;
			}
			Node(T t) {
				this.t = t;
			}
			
			T t;
			Node<T> next;
			Node<T> previous;
		}
}
