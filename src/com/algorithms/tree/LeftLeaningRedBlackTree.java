package com.algorithms.tree;
/**
 * 2-3-4tree  
 * @author altro
 *
 */
public class LeftLeaningRedBlackTree <K extends Comparable<K>, V>/* implements Map<K, V>*/{
	
	
	public static void main(String[] args) {
		
/*		LeftLeaningRedBlackTree<Integer, Integer> tree = new LeftLeaningRedBlackTree<>();
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
		
		tree.deleteMax();
		tree.deleteMax();
		*/
		
		LeftLeaningRedBlackTree<Integer, Integer> tree1 = new LeftLeaningRedBlackTree<>();
		tree1.put(10, 10);
		tree1.put(7, 7);
		tree1.put(15, 15);
		tree1.put(5, 5);
		tree1.deleteMax();
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
	
	public void delete(K k) {
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = delete(root, k);
		if (root != null)
			root.color = BLACK;
	}
	
	private Node delete(Node cn, K k) {
		if (cn == null) return null;
		int cmp = k.compareTo(cn.k);
		
		if (cmp < 0) { // k < node.k go left
            if (!isRed(cn.left) && !isRed(cn.left.left))
                cn = moveRedLeft(cn);
            cn.left = delete(cn.left, k);
		} else if (cmp > 0) { // k > node.k go right
            if (isRed(cn.left))
                cn = rotateRight(cn);
            if (!isRed(cn.right) && !isRed(cn.right.left))
                cn = moveRedRight(cn);
            cn.right = delete(cn.right, k);
		} else { //hit
			
            if (isRed(cn.left)) {
                cn = rotateRight(cn);
                cn.right = delete(cn.right, k);
            }
            
            if (cn.left == null)
            	return cn.right;
            else if (cn.right == null)
            	return cn.left;
            else {
                Node x = min(cn.right);
                cn.k = x.k;
                cn.v = x.v;
                cn.right = deleteMin(cn.right);
            }
		}
		return fixup(cn);
	}
	
    private Node min(Node x) { 
        // assert x != null;
        if (x.left == null) return x; 
        else return min(x.left); 
    } 
	
	
	public void deleteMin() {
		root = deleteMin(root);
		root.color = BLACK;
	}
	
	
	public Node deleteMin(Node cn) {
		if (cn.left == null) return null;
		
		if (!isRed(cn.left) && !isRed(cn.left.left)) cn = moveRedLeft(cn);
		
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
			if (color) return "red" + v;
			else return "black" + v;
		}
		
	}
	

}
