package com.algorithms.tree;

import com.algorithms.elementary.Queue;

/**
 * �ǳ��� hashing��Ч�ʲ��
 * ����ͨ��ǰ���һ��R��С������ ����R^2��С������ ���ж�ǰ��2���ַ�, �����ٸ���TST��
 * @author altro
 *
 * @param <V>
 */
public class TernarySearchTries<V> implements StringST<V>{
	
	
	public static void main(String[] args) {
		TernarySearchTries<Integer> tst = new TernarySearchTries();
		tst.put("haha", 323);
		tst.put("haha", 1);
		tst.put("a", 2);
		tst.put("b", 23);
		System.out.println(tst.get("haha"));
		System.out.println(tst.get("a"));
		System.out.println(tst.get("b"));
	}
	
	
	
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
		if (key == null) return;
		delete(key, 0);
	}
	
	private void delete(String key, int d) {
		
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

	@Override
	public Iterable<String> keys() {
		return null;
	}
	
	@Override
	public Iterable<String> keysWithPrefix(String s) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Iterable<String> keysThatMatch(String s) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String longestPrefixOf(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
