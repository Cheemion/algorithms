package com.algorithms.map;
/**
 * 基于二叉树的map
 * Key是独一无二的 Unique
 * @author altro
 *
 * @param <K>
 * @param <V>
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> implements Map<K, V>{
	
	private Node<K, V> root;
	
	@Override
	public void put(K k, V v) {
		root = put(root, k, v);
	}
	
	private Node<K, V> put(Node<K, V> node, K k, V v) {
		if (node == null) return new Node<>(k, v, 1);
		int cmp = node.k.compareTo(k);
		if (cmp > 0) { //node的k大一点 放到左边的数中
			node.left = put(node.left, k, v);
		} else if (cmp < 0) { //node的k小一点 放到右边的数中
			node.right = put(node.right, k, v);
		} else node.v = v;
		node.size = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	//判断size的大小
	private int size(Node<K, V> node) {
		if (node == null) return 0;
		return node.size;
	}

	@Override
	public V get(K k) {
		return get(root, k);
	}
	
	private V get(Node<K, V> node, K k) {
		if (node == null) return null;
		else if (node.k.compareTo(k) > 0) { //node的k大一点 放到左边的数中
			return get(node.left, k);
		} else if (node.k.compareTo(k) < 0) { //node的k小一点 放到右边的数中
			return get(node.right, k);
		} else {
			return node.v;
		}
	}
	

	@Override
	public void delete(K k) {
		
	}

	@Override
	public boolean contains(K k) {
		return get(k) != null;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		return root.size;
	}

	@Override
	public K min() {
		Node<K, V> node;
		for (node = root; node != null; node = node.left);
		return node.k;
	}

	@Override
	public K max() {
		Node<K, V> node;
		for (node = root; node != null; node = node.right);
		return node.k;
	}

	@Override
	public K floor(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K ceiling(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int rank(K k) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public K select(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMax() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<K> keys(K lo, K hi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<K> keys() {
		return null;
	}
	
	private class Node<K, V> {
		private K k;
		private V v;
		private Node<K, V> left;
		private Node<K, V> right;
		private int size;
		Node(K k, V v) { this.k = k; this.v = v; }
		Node(K k, V v, int size) { this.k = k; this.v = v; this.size = size;}
	}
	
}
