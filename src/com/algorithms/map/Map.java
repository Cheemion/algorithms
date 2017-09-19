package com.algorithms.map;

public interface Map<K extends Comparable<K>, V> {
	void put(K k, V v);
	
	V get(K k);
	
	void delete(K k);
	
	boolean contains(K k);
	
	boolean isEmpty();
	
	int size();
	
	int size(K lo, K hi);
	
	K min();
	
	K max();
	
	K floor(K k);
	
	K ceiling(K k);
	
	// the number of keys less than key
	int rank(K k);
	
	K select(int k);
	
	void deleteMin();
	
	void deleteMax();
	
	// keys in [lo , hi] in sorted order
	Iterable<K> keys(K lo, K hi);
	
	Iterable<K> keys();
}
