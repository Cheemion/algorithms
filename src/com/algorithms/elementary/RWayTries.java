package com.algorithms.elementary;
/**
 * simple table implementation
 * @author altro
 *
 * @param <V>
 */
public class RWayTries<V> {
	
	private static final int R = 256; // radix extended ASCII
	private Node<V> root = new Node<>();
	
	
	public void put(String key, V val) {
		root = put(root, key, val, 0);
	}
	
	public boolean contains(String key) {
		return get(key) != null;
	}
	
	public void delete(String key) {
		if (!contains(key)) return;
		delete(root, key, 0);
	}
	
	private void delete(Node<V> current, String key, int d) {
		if (d == key.length()) {
			current.value = null;
		} else {
			char cc = key.charAt(d);
			delete(current.next[cc], key, d + 1);
		}
	}
	
	public V get(String key) {
		Node<V> x = get(root, key, 0);
		if (x == null) return null;
		return x.value;
	}
	
	private Node<V> get(Node<V> c, String key, int d) {
		if (c == null) return null;
		if (d == key.length()) return c;
		char cc = key.charAt(d);
		return get(c.next[cc], key, d + 1);
	}
	
	private Node<V> put(Node<V> current, String key, V v, int d) {
		if (current == null) current = new Node<>();
		if (d == key.length()) { current.value = v; return current;}
		char c = key.charAt(d);
		current.next[c] = put(current.next[c], key, v, d + 1);
		return current;
	}
	
	private static class Node<V>{
		private V value;
		private Node[] next = new Node[R];
	}
}
