package com.algorithms.elementary;

public interface Stack<T> extends Iterable<T> {
	void push(T t);
	T pop();
	boolean isEmpty();
	int size();
}
