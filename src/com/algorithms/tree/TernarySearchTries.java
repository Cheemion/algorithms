package com.algorithms.tree;

import com.algorithms.elementary.Queue;

/**
 * 非常快 hashing的效率差不多
 * 可以通过前面放一个R大小的数组 或者R^2大小的数组 来判断前面2个字符, 后面再跟着TST树
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
		
		tst.delete("haha");
		System.out.println("delete haha");
		System.out.println(tst.get("haha"));

		
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
		delete(root, key, 0, false);
	}
	
	private void delete(Node<V> x, String key, int d, Boolean needsDelete) {
		if (key == null) return;
		char c = key.charAt(d);
		if (c < x.c) delete(x.left, key, d, needsDelete);
		else if (c > x.c) delete(x.right, key, d, needsDelete);
		else if (d < key.length() - 1) delete(x.mid, key, d, needsDelete);
		else { //hit 找到目标了
			x.val = null;
			if (x.mid == null && x.right == null && x.left == null)
				needsDelete = true;
		}
		
		if (needsDelete && x.val == null) {
			if (c < x.c) x.left = null;	
			else if (c > x.c) x.right = null;
			else x.mid = null;
		} else needsDelete = false;
		
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
