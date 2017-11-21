package com.algorithms.tree;

import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.Queue;

/**
 * simple table implementation
 * @author altro
 *
 * @param <V>
 */
public class RWayTries<V> implements StringST<V>{
	
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
	
	@Override
	public Iterable<String> keys() {
		Queue<String> queue = new ArrayQueue<String>();
		collect(root, "", queue);
		return queue;
	}

	
	private void collect(Node<V> x, String prefix, Queue<String> q) {
		if (x == null) return;
		if (x.value != null) q.enqueue(prefix);
		for (char c = 0; c < R; c++)
			collect(x.next[c], prefix + c, q);
	}

	
	
	@Override
	public Iterable<String> keysWithPrefix(String prefix) {
		Queue<String> queue = new ArrayQueue<>();
		Node<V> x = get(root, prefix, 0);
		collect(x, prefix, queue);
		return queue;
	}
	
	private void collect(Node<V> x, String pre, String pat, Queue<String> q) {
		int d = pre.length();
		if (x == null) return;
		if (d == pat.length() && x.value != null) q.enqueue(pre);
		if (d == pat.length()) return;
		
		char next = pat.charAt(d);
		for (char c = 0; c < R; c++)
			if (next == '.' || next == c)
				collect(x.next[c], pre + c, pat, q);
	}

	//通配符 匹配
	//所有和pat匹配的键, .代表匹配全部
	@Override
	public Iterable<String> keysThatMatch(String pattern) {
		Queue<String> q = new ArrayQueue<>();
		collect(root, "", pattern, q);
		return q;
	}

	@Override
	public String longestPrefixOf(String query) {
		int length = search(root, query, 0, 0);
		return query.substring(0, length);
	}
	
	private int search(Node<V> x, String query, int d, int length) {
		if (x == null) return length;
		if (x.value != null) length = d;
		if (d == query.length()) return length;
		char c = query.charAt(d);
		return search(x.next[c], query, d + 1, length);
	}

}
