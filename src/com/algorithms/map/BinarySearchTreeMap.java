package com.algorithms.map;

import java.util.NoSuchElementException;

import com.algorithms.elementary.ArrayBag;
import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.Bag;
import com.algorithms.elementary.Queue;

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
		
		if (node == null) return null; //not find
		else if (node.k.compareTo(k) > 0) { //node的k大一点 放到左边的数中
			return get(node.left, k);
		} else if (node.k.compareTo(k) < 0) { //node的k小一点 放到右边的数中
			return get(node.right, k);
		} else {  					//equal
			return node.v;
		}
		
	}
	

	@Override
	public void delete(K k) {
		
	}
	
	private void delete(Node<K, V> node, K k) {
		int cmp = node.k.compareTo(k);
		if (cmp > 0) delete(node.left, k);
		else if (cmp < 0) delete(node.right, k);
		else { 					//equal
			
		}
	}
	
	private Node<K, V> deleteMin(Node<K, V> node) {
		for (; node.left != null; node = node.left);
		return node;
	}
	
	
	
	@Override
	public void deleteMin() {
		delete(min());
	}
	
	@Override
	public void deleteMax() {
		delete(max());
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
	public int size(K lo, K hi) {
		return size(root, lo, hi);
	}
	
	private int size(Node<K, V> node, K lo, K hi) {
		if (node == null) return 0;
		int cmpToLow = node.k.compareTo(lo);
		int cmpToHi = node.k.compareTo(hi);
		if (cmpToLow < 0) { //node is less than lo
			return size(node.right, lo, hi);
		} else if (cmpToHi > 0) { // node is large than hi
			return size(node.left, lo, hi);
		} else { // node is between lo and hi, [lo, hi]
			return size(node.right, lo, hi) + size(node.left, lo, hi) + 1;
		}
	}
	
	@Override
	public K min() {
		Node<K, V> node;
		for (node = root; node.left != null; node = node.left);
		return node.k;
	}

	@Override
	public K max() {
		Node<K, V> node;
		for (node = root; node.right != null; node = node.right);
		return node.k;
	}
	
	//return　the key that is less or equal to the paramter k
	@Override
	public K floor(K k) {
		return floor(root, k);
	}
	
	private K floor(Node<K, V> node, K k) {
		if (node == null) return null;
		int cmp = node.k.compareTo(k); 
		
		if (cmp > 0) return floor(node.left, k); //node.k 大于 k so we need to find the k in the left tree
		else if (cmp < 0) {   //node.k is less then k
			K returnValue = node.k;	 //so node.k might be the value .but iam not sure we shall see
			if (floor(node.left, k) != null)  //如果发现right 树中还有更接近k then we should return that value
				returnValue = floor(node.left, k);
		   return returnValue;
		} else {
			return node.k;
		}
	}
	
	//return　the k that is large or equal to k the paramter
	@Override
	public K ceiling(K k) {
		return ceiling(root, k);
	}
	
	private K ceiling(Node<K, V> node, K k) {
		
		if (node == null) return null;
		int cmp = node.k.compareTo(k); 
		
		if (cmp > 0) {
			K returnValue = node.k;	 //so node.k might be the value .but iam not sure we shall see
			if (ceiling(node.left, k) != null)  //如果发现right 树中还有更接近k then we should return that value
				returnValue = ceiling(node.left, k);
		   return returnValue;
		}
		else if (cmp < 0) {   //node.k is less then k
			return ceiling(node.right, k);
		} else {
			return node.k;
		}
		
	}
	
	
	// the number of keys less than key
	@Override
	public int rank(K k) {
		return rank(root, k);
	}
	
	private int rank(Node<K, V> node, K k) {
		if (node == null) return 0;
		int cmp = node.k.compareTo(k);
		if (cmp > 0) 
			return rank(node.left, k);
		else if (cmp < 0) {
			return size(node.left) + rank(node.right, k) + 1;
		} else 
			return size(node.left);
	}
	
	@Override
	public K select(int k) {
		if (k > root.size - 1) throw new NoSuchElementException("the int k is large than size - 1");
		return select(root, k);
	}
	
	private K select(Node<K, V> node, int k) {
		
		if (size(node.left) > k) {
			return select(node.left, k);
		} else if (size(node.left) < k) {
			return select(node.right, k - size(node.left) - 1);
		} else 
			return node.k;
		
	}
	
	//最小的先加入bag
	private void keys(Node<K, V> node, K lo, K hi, Queue<K> queue) {
		if (node == null) return;
		int cmpToLow = node.k.compareTo(lo);
		int cmpToHi = node.k.compareTo(hi);
		
		if (cmpToLow < 0) { //超出了lo的范围
			keys(node.left, lo, hi, queue);
		} else if (cmpToHi > 0) { //超出了hi的范围
			keys(node.right, lo, hi, queue);
		} else { //在2个范围内
			keys(node.left, lo, hi, queue);
			queue.enqueue(node.k);
			keys(node.right, lo, hi, queue);
		}
	}
	
	// keys in [lo , hi] in sorted order
	@Override
	public Iterable<K> keys(K lo, K hi) {
		Queue<K> queue = new ArrayQueue<>();
		keys(root, lo, hi, queue);
		return queue;
	}
	
	@Override
	public Iterable<K> keys() {
		Queue<K> queue = new ArrayQueue<>();
		keys(root, min(), max(), queue);
		return queue;
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
