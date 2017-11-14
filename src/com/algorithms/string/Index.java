package com.algorithms.string;

public interface Index {
	// index Ϊ 0(inclusive) to radix - 1 (inclusive)
	int getIndex(); // return the index of the object, the index is used to replace this object on the array
	int getRadix(); // return the radix of the index .like 10 is the radix of the decimal system 
}