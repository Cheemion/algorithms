package com.algorithms.tree;

import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.Queue;

/**
 * �ǳ��� hashing��Ч�ʲ��
 * ����ͨ��ǰ���һ��R��С������ ����R^2��С������ ���ж�ǰ��2���ַ�, �����ٸ���TST��
 * �������� ��ߺ��ұߵĽڵ���ʵ �͵�ǰ�ڵ���ƽ�е�
 * @author altro
 *
 * @param <V>
 */
public class TernarySearchTries<V> implements StringST<V>{
	
	
	public static void main(String[] args) {
		TernarySearchTries<Integer> tst = new TernarySearchTries();
		tst.put("haha", 323);
		tst.put("haha", 1);
		tst.put("hahahh", 1);
		tst.put("as", 2);
		tst.put("aba", 23);
		
		System.out.println("*************************************");
		System.out.println(tst.longestPrefixOf("hahahhhs"));
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
		else if (d < key.length() - 1) delete(x.mid, key, d + 1, needsDelete);
		else { //hit �ҵ�Ŀ����
			x.val = null;
			if (x.mid == null && x.right == null && x.left == null) //û����tree
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
		@Override
		public String toString() {
			return "\n Node [val=" + val + ", c=" + c + ", left=" + left + ", mid=" + mid + ", right=" + right + "]";
		}
		
	}

	@Override
	public Iterable<String> keys() {
		Queue<String> queue = new ArrayQueue<>();
		search(root, "", queue);
		return queue;
	}
	
	public void search(Node<V> x, String prefix, Queue<String> queue) {
		if (x == null) return;
		if (x.val != null) queue.enqueue(prefix + x.c);
		
		search(x.left, prefix, queue);
		search(x.mid, prefix + x.c, queue);
		search(x.right, prefix, queue);
	}

	
	@Override
	public Iterable<String> keysWithPrefix(String prefix) {
		Queue<String> queue = new ArrayQueue<>();
		Node<V> node = get(root, prefix, 0);
		if (node.val != null) queue.enqueue(prefix);
		search(node.mid, prefix, queue);
		return queue;
	}
	
	private void collect(Node<V> x, String pre, String pat, Queue<String> q) {
		if (x == null) return;
		char ch = pat.charAt(pre.length());
		if (ch == x.c || ch == '.') {
			String newPre = pre + x.c;
			if (newPre.length() == pat.length() && x.val != null) q.enqueue(newPre);
			if (newPre.length() == pat.length()) return;
			collect(x.mid, newPre, pat, q);
		}
		
		collect(x.left, pre, pat, q);
		collect(x.right, pre, pat, q);
	}
	
	
	
	//ͨ��� ƥ��
	//���к�patƥ��ļ�, .����ƥ��ȫ��
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
		if (d == query.length()) return length;
		char c = query.charAt(d);
		if (c < x.c) return search(x.left, query, d, length);
		else if (c > x.c) return search(x.right, query, d, length);
		else return search(x.mid, query, d + 1, d + 1);
	}
}
