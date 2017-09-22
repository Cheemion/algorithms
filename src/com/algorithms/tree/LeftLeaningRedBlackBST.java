package com.algorithms.tree;

import java.util.NoSuchElementException;

import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.Queue;
/**
 * ��������
 * ��������ֻ��ʵ����ƽ��2-3����
 * Ҳ����ר��дһ��ƽ��2-3X��
 * @author altro
 *
 * @param <K>
 * @param <V>
 */
public class LeftLeaningRedBlackBST <K extends Comparable<K>, V> implements Map<K, V> {
	
	private Node root;
	
	@Override
	public void put(K k, V v) {
		root = put(root, k, v);
		root.color = Color.BLACK; // reset the root color to black
	}

	private Node put(Node node, K k, V v) {
		if (node == null) //find the place of this new node
			return new Node(k, v, 1, Color.RED);
		int cmp = node.k.compareTo(k);
		
		if 		(cmp > 0)  node.left = put(node.left, k, v); // node��k��һ�� �ŵ���ߵ�tree��
		else if (cmp < 0)  node.right = put(node.right, k, v); // node��kСһ�� �ŵ��ұߵ�tree��
		else			   node.v = v; //hit
		
		if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
		if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
		if (isRed(node.right) && isRed(node.left)) flipColors(node);
		
		node.size = size(node.left) + size(node.right) + 1;
		
		return node;
	}

	// �ж�size�Ĵ�С
	private int size(Node node) {
		if (node == null)
			return 0;
		return node.size;
	}

	@Override
	public V get(K k) {
		return get(root, k);
	}

	private V get(Node node, K k) {

		if (node == null)
			return null; // not find
		else if (node.k.compareTo(k) > 0) { // node��k��һ�� �ŵ���ߵ�����
			return get(node.left, k);
		} else if (node.k.compareTo(k) < 0) { // node��kСһ�� �ŵ��ұߵ�����
			return get(node.right, k);
		} else { // equal
			return node.v;
		}

	}

	@Override
	public void delete(K k) {
		
	}

	// delete the k in the node tree and reset the size prorperty of this tree
	// and subtrees to correct value
	private Node delete(Node node, K k) {
		if (node == null) return null;
		int cmp = node.k.compareTo(k);
		
		if (cmp > 0) { // node.k > k  enter the left subtree
			if (isRed(node.left) || isRed(node.left.left)) { //���ӽڵ㲻��2-�ڵ�
				node.left = delete(node.left, k);
				return node;
			} if (!isRed(node.left) && !isRed(node.left.left) && ()) { // ��ڵ���2-�ڵ� 
				node.left.color = Color.RED; 
				node.right.color = Color.RED;
			} else if ()
			node.left = delete(node.left,k);
			return node;
		} else if (cmp < 0) { // node.k < k
			return node;
		} else {	//hit the key 
		}
	}

	// ɾ���Ӳ���node��ʼ����С��node
	private Node deleteMin(Node node) {
		
	}
	
	@Override
	public void deleteMin() {
		
	}

	@Override
	public void deleteMax() {
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

	private int size(Node node, K lo, K hi) {
		if (node == null)
			return 0;
		int cmpToLow = node.k.compareTo(lo);
		int cmpToHi = node.k.compareTo(hi);
		if (cmpToLow < 0) { // node is less than lo
			return size(node.right, lo, hi);
		} else if (cmpToHi > 0) { // node is large than hi
			return size(node.left, lo, hi);
		} else { // node is between lo and hi, [lo, hi]
			return size(node.right, lo, hi) + size(node.left, lo, hi) + 1;
		}
	}

	@Override
	public K min() {
		Node min = min(root);
		if (min == null) return null;
		return min.k;
	}
	
	//get the smallest node in the given node
	private Node min(Node node) {
		if (node == null) return null;
		
		for (; node.left != null; node = node.left);
		return node;
	}
	

	@Override
	public K max() {
		Node max = max(root);
		if (max == null) return null;
		return max.k;
	}
	
	//get the most max node in the given node
	private Node max(Node node) {
		if (node == null) return null;
		for (; node.right != null; node = node.right);
		return node;
	}
	
	// return the key that is less or equal to the paramter k
	@Override
	public K floor(K k) {
		return floor(root, k);
	}

	private K floor(Node node, K k) {

		if (node == null)
			return null;
		int cmp = node.k.compareTo(k);

		if (cmp > 0)
			return floor(node.left, k); // node.k ���� k so we need to find the k
										// in the left tree
		else if (cmp < 0) { // node.k is less then k
			K returnValue = node.k; // so node.k might be the value .but iam not
									// sure we shall see
			if (floor(node.right, k) != null) // �������right ���л��и��ӽ�k then we
												// should return that value
				returnValue = floor(node.right, k);
			return returnValue;
		} else {
			return node.k;
		}
	}

	// return the k that is large or equal to k the paramter
	@Override
	public K ceiling(K k) {
		return ceiling(root, k);
	}

	private K ceiling(Node node, K k) {

		if (node == null)
			return null;
		int cmp = node.k.compareTo(k);

		if (cmp > 0) {
			K returnValue = node.k; // so node.k might be the value .but iam not
									// sure we shall see
			if (ceiling(node.left, k) != null) // �������right ���л��и��ӽ�k then we
												// should return that value
				returnValue = ceiling(node.left, k);
			return returnValue;
		} else if (cmp < 0) { // node.k is less then k
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

	private int rank(Node node, K k) {
		if (node == null)
			return 0;
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
		if (k > root.size - 1)
			throw new NoSuchElementException();
		return select(root, k);
	}

	private K select(Node node, int k) {

		if (size(node.left) > k) {
			return select(node.left, k);
		} else if (size(node.left) < k) {
			return select(node.right, k - size(node.left) - 1);
		} else
			return node.k;

	}

	// ��С���ȼ���queue
	private void keys(Node node, K lo, K hi, Queue<K> queue) {
		if (node == null)
			return;
		int cmpToLow = node.k.compareTo(lo);
		int cmpToHi = node.k.compareTo(hi);

		if (cmpToLow < 0) { // ������lo�ķ�Χ
			keys(node.left, lo, hi, queue);
		} else if (cmpToHi > 0) { // ������hi�ķ�Χ
			keys(node.right, lo, hi, queue);
		} else { // ��2����Χ��
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
	
	private void flipColors(Node h) {
		assert(!isRed(h));
		assert(isRed(h.right));
		assert(isRed(h.left));
		
		h.color = Color.RED;
		h.left.color = Color.BLACK;
		h.right.color = Color.BLACK;
	}
	
	
	//�����Ǻ�link��ʱ��,turn this red link to left
	private Node rotateLeft(Node  h) {
		assert(isRed(h.right));
		
		Node  x = h.right;  //change��the pointers
		h.right = x.left;
		x.left = h;
		
		x.color = h.color; //change the colors
		h.color = Color.RED;
		
		x.size = h.size; //change the sizes
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	//�����Ǻ�link��ʱ��,turn this red link to left
	private Node rotateRight(Node  h) {
		assert(isRed(h.left));
		
		Node  x = h.left;
		h.left = x.right;
		x.right = h;
		
		x.color = h.color;
		h.color = Color.RED;
		
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		
		return x;
	}
	
	private boolean isRed(Node x) {
		if (x == null) return false;
		return x.color == Color.RED;
	}
	
	
	private class Node {
		private K k;
		private V v;
		private Node left;
		private Node right;
		private int size;
		private Color color;

		Node(K k, V v, int size, Color color) {
			this.k = k;
			this.v = v;
			this.size = size;
			this.color = color;
		}
		
	}
	
	private enum Color {
		BLACK,
		RED
	}
}
