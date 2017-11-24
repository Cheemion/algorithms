package com.algorithms.regex;

public class GREP {
	private NFA nfa; 
	public GREP(String pattern) {
		this.nfa = new NFA("(.*" + pattern + ".*");
	}
	public boolean search(String txt) {
		return nfa.recognizes(txt);
	}
}
