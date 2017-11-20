package com.algorithms.tree;

public interface StringST<Value> {
	
	void put(String key, Value val);
	
	Value get(String key);
	
	void delete(String key);
	
	Iterable<String> keys();
	
	Iterable<String> keysWithPrefix(String s);
	
	Iterable<String> keysThatMatch(String s);
	
	String longestPrefixOf(String s);
	
}
