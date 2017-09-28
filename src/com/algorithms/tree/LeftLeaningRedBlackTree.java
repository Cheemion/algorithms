package com.algorithms.tree;

import java.util.NoSuchElementException;

import com.algorithms.elementary.ArrayQueue;
import com.algorithms.elementary.Queue;

/**
 * 2-3-4tree  
 * @author altro
 *
 */
public class LeftLeaningRedBlackTree <K extends Comparable<K>, V> implements Map<K, V>{
	
	public static void main(String[] args) {
		
		LeftLeaningRedBlackTree<Integer, Integer> tree = new LeftLeaningRedBlackTree<>();
		tree.put(10, 10);
		tree.put(7, 7);
		tree.put(6, 6);
		tree.put(8, 8);
		tree.put(11, 11);
		tree.put(15, 15);
		tree.put(17, 17);
		tree.put(0, 0);
		tree.put(4, 4);
		tree.put(19, 19);
		System.out.println("tree.get(10) + " + tree.get(10));
		System.out.println("tree.get(-1) + " + tree.get(-1));
		System.out.println("tree.get(15) + " + tree.get(15));
		
		tree.delete(15);
		tree.delete(17);
		System.out.println("hah");
	}
	
	private Node root = null;
	
	public void put(K k, V v) {
		root = put(root, k, v);
		root.color = BLACK;
	}
	
	private Node put(Node cn, K k, V v) {
		if (cn == null) return new Node(k, v, 1, RED);
		if(isRed(cn.left) && isRed(cn.right)) split4Node(cn);//是4节点的话 就split
		
		int cmp = k.compareTo(cn.k);
		if (cmp > 0) cn.right = put(cn.right, k, v); // k > node.k go right
		else if (cmp < 0) cn.left = put(cn.left, k, v);
		else cn.v = v; //hit
		
		//following code is to fix the tree on the way up
		if (isRed(cn.right) && !isRed(cn.left)) cn = rotateLeft(cn); //　right leaning 3nodes的时候 　 需要变成　left leaning
		if (isRed(cn.left) && isRed(cn.left.left)) cn = rotateRight(cn);  //变成了一个4节点
		
		//if (isRed(cn.left) && isRed(cn.right)) flipColors(cn); 好像不split也可以 
		
		cn.size = size(cn.left) + size(cn.right) + 1;
		return cn;
	}
	
	public V get(K k) {
		return get(root, k);
	}
	
	//cn means currentNode
	private V get(Node cn, K k) {
		if (cn == null) return null; // not find the key
		
		int cmp = k.compareTo(cn.k);
		if(cmp > 0) return get(cn.right, k);
		else if (cmp < 0) return get(cn.left, k);
		else return cn.v; // hit
	}
	
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		return size(root);
	}

	@Override
	public int size(K lo, K hi) {
		return size(root, lo, hi);
	}
	
	private int size(Node node, K lo, K hi) {
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
		if (isEmpty()) return null;
		else return min(root).k;
	}

	@Override
	public K max() {
		if (isEmpty()) return null;
		else return max(root).k;
	}

	//get the most max node in the given node
	private Node max(Node node) {
		while(node.right != null) node = node.right;
		return node;
	}
	
	
	@Override
	public K floor(K k) {
        if (k == null) throw new IllegalArgumentException("argument to floor() is null");
		return floor(root, k);
	}
	
	private K floor(Node node, K k) {
		
		if (node == null) return null;
		int cmp = node.k.compareTo(k); 
		
		if (cmp > 0) return floor(node.left, k); //node.k 大于 k so we need to find the k in the left tree
		else if (cmp < 0) {   //node.k is less then k
			K returnValue = node.k;	 //so node.k might be the value .but iam not sure we shall see
			if (floor(node.right, k) != null)  //如果发现right 树中还有更接近k then we should return that value
				returnValue = floor(node.right, k);
		   return returnValue;
		} else {
			return node.k;
		}
	}

	@Override
	public K ceiling(K k) {
        if (k == null) throw new IllegalArgumentException("argument to ceilling() is null");
        else return ceiling(root, k);
	}

	private K ceiling(Node node, K k) {
		
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
	
	@Override
	public int rank(K k) {
        if (k == null) throw new IllegalArgumentException("argument to rank() is null");
		return rank(root, k);
	}

	
	private int rank(Node node, K k) {
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
		if (root == null) throw new NoSuchElementException();
		if (k > root.size - 1 || k < 0) throw new NoSuchElementException();
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
	
	// keys in [lo , hi] in sorted order
	@Override
	public Iterable<K> keys(K lo, K hi) {
		Queue<K> queue = new ArrayQueue<>();
		keys(root, lo, hi, queue);
		return queue;
	}
	
	//最小的先加入queue
	private void keys(Node node, K lo, K hi, Queue<K> queue) {
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

	@Override
	public Iterable<K> keys() {
		Queue<K> queue = new ArrayQueue<>();
		keys(root, min(), max(), queue);
		return queue;
	}
	
	public void delete(K k) {
        if (k == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(k)) return;
		
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = delete(root, k);
		if (root != null)
			root.color = BLACK;
	}
	
	public boolean contains(K k) {
		return get(k) != null;
	}

	private Node delete(Node cn, K k) {
		
		if (cn == null) return null;
		
		int cmp = k.compareTo(cn.k);
		
		if (cmp < 0) { // k < node.k go left
            if (!isRed(cn.left) && !isRed(cn.left.left)) //保证了下一个左元素不是2nodes
                cn = moveRedLeft(cn);
            cn.left = delete(cn.left, k);
		} else if (cmp > 0) { // k > node.k go right
            if (isRed(cn.left) && !isRed(cn.right)) //如果是3节点的话需要 rotate 把red转到右边
                cn = rotateRight(cn);
            if (!isRed(cn.right) && !isRed(cn.right.left)) //保证下一个右节点不是2nodes
                cn = moveRedRight(cn);
            cn.right = delete(cn.right, k);
		} else { //hit
			
            if (isRed(cn.left) && !isRed(cn.right)) 
            	cn = rotateRight(cn);
            
            if (k.compareTo(cn.k) == 0 && (cn.right == null)) //find null just return null
                return null;
            
            if (!isRed(cn.right) && !isRed(cn.right.left)) //保证下一个右节点不是2nodes
                cn = moveRedRight(cn);
            
            if (k.compareTo(cn.k) == 0) {
            	 Node x = min(cn.right);
                 cn.k = x.k;
                 cn.v = x.v;
                 cn.right = deleteMin(cn.right);
            } else cn.right = delete(cn.right, k);
		}
		return fixup(cn);
	}
	
    private Node min(Node x) {
        if (x.left == null) return x; 
        else return min(x.left); 
    } 
	
	
	public void deleteMin() {
		//保证了root节点不是2nodes
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		
		root = deleteMin(root);
		root.color = BLACK;
	}
	
	
	public Node deleteMin(Node cn) {
		if (cn.left == null) return null;
		
		if (!isRed(cn.left) && !isRed(cn.left.left)) 
			cn = moveRedLeft(cn);
		
		cn.left = deleteMin(cn.left);
		
		return fixup(cn);
	}
	
	private Node moveRedLeft(Node cn) {
		flipColors(cn);
		if (isRed(cn.right.left)) {
			cn.right = rotateRight(cn.right);
			cn = rotateLeft(cn);
			flipColors(cn);
		}
		return cn;
	}
	
	
	
	public void deleteMax() {
		//保证了root节点不是2nodes
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		
		root = deleteMax(root);
		root.color = BLACK;
	}
	
	
	
	//make sure currentNode is not a 2Node by make currentNode is red or currentNode.left is red
	private Node deleteMax(Node cn) {
		//这一句话保证红色节点总在右边
		if (isRed(cn.left) && !isRed(cn.right) /* && !isRed(cn.right) 这一句感觉要加因为 4node 并不需要rotateRight*/) 
			cn = rotateRight(cn); //一定要先rotateRight
		
		if (cn.right == null) return null; // approach the end and find the currentNode's childNode is null then just return null;
		
		//保证了右边子节点不是 2-nodes 所以每次都保证右边子节点不是2node，所以最开始我们保证了root节点不是black, 因为这段代码对root节点无效
		if (!isRed(cn.right) && !isRed(cn.right.left)) { //右节点是子节点　&&  右边子节点是2节点　
			cn = moveRedRight(cn); // 右边子节点  now it is not 2 node;
		}
		
		cn.right = deleteMax(cn.right);
		return fixup(cn); 
	}
	
	//used when cn.left is not a 2Node
	private Node moveRedRight(Node cn) {
		flipColors(cn);
		if (isRed(cn.left.left)) { //judge if the  cn.left node is 2 node. 因为有2种strategy 要进行，并且是leftLeaning 所以只需要判断一下cn.left.left就可以了
			cn = rotateRight(cn); //一种是borrow node from siblings node if siblings is red 
			flipColors(cn);
		}
		//另外一种是 combine parent and sibling node to become a 3 node 也就是 第一句代码flipColors(cn) 语句的功能
		return cn;
	}
	
	// 判断size的大小
	private int size(Node node) {
		if (node == null)
			return 0;
		return node.size;      
	}
	
	private void split4Node(Node cn) {
		flipColors(cn);
	}
	
	private void flipColors(Node h) {
		assert(!isRed(h));
		assert(isRed(h.right));
		assert(isRed(h.left));
		
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}
	
	
	//右树是红link的时候,turn this red link to left
	private Node rotateLeft(Node  h) {
		assert(isRed(h.right));
		
		Node  x = h.right;  //change　the pointers
		h.right = x.left;
		x.left = h;
		
		x.color = h.color; //change the colors
		h.color = RED;
		
		x.size = h.size; //change the sizes
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	//左树是红link的时候,turn this red link to left
	private Node rotateRight(Node  h) {
		assert(isRed(h.left));
		
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		
		x.color = h.color;
		h.color = RED;
		
		x.size = h.size; //size is the same
		h.size = size(h.left) + size(h.right) + 1;
		
		return x;
	}
	
	
	private Node fixup(Node h) {
		if (isRed(h.right)) h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		// on the way up eliminate 4 nodes
		//if (isRed(h.left) && isRed(h.right)) flipColors(h); //这句话感觉没有必要加　不知道为什么要　split 4 nodes
		h.size = size(h.left) + size(h.right) + 1; //right the size
		return h;
	}
	
	
	private boolean isRed(Node x) {
		if (x == null) return false;
		return x.color == RED;
	}
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node {
		private K k;
		private V v;
		private Node left, right;
		private int size;
		private boolean color;
		
		Node(K k, V v, int size, boolean color) {
			this.k = k;
			this.v = v;
			this.size = size;
			this.color = color;
		}
		
		@Override
		public String toString() {
			if (color) return "red " + v;
			else return "black " + v;
		}
		
	}
}
