package com.algorithms.elementary;

public interface Bag<T> extends Iterable<T>{
	void add (T t);
	boolean isEmpty();
	int size();
}
