package com.algorithms.tree;

/**
 * 非常快 hashing的效率差不多
 * 可以通过前面放一个R大小的数组 或者R^2大小的数组 来判断前面2个字符, 后面再跟着TST树
 * @author altro
 *
 * @param <V>
 */
public class TernarySearchTries<V> {
	
	private Node<V> root;
	
	public void put(String key, V val) {
		root = put(root, key, val, 0);
	}
	
	
	public boolean contains (String key) {
		return get(key) != null;
	}
	
	public V get(String key) {
		Node<V> x = get(root, key, 0);
		if (x == null) return null;
		return x.val;
	}
	
	public void delete(String key) {
		if (!contains(key)) return;
		root = put(root, key, null, 0);
	}
	
	private Node<V> get(Node<V> x, String key, int d) {
		if (x == null) return null;
		char c = key.charAt(d);
		if (c < x.c) return get(x.left, key, d);
		else if (c > x.c) return get(x.right, key, d);
		else if (d < key.length() - 1) return get(x.mid, key, d + 1);
		else return x;
	}

	private Node<V> put(Node<V> x, String key, V val, int d) {
		char c = key.charAt(d);
		if (x == null) { x = new Node<>(); x.c = c;}
		if (c < x.c) x.left = put(x.left, key, val, d);
		else if (c > x.c) x.right = put(x.right, key, val, d);
		else if (d < key.length() - 1) x.mid = put(x.mid, key, val, d + 1);
		else x.val = val;
		return x;
	}

	private static class Node<V> {
		private V val;
		private char c;
		private Node<V> left, mid, right;
	}
	
}
