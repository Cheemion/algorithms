package com.algorithms.regex;

import com.algorithms.elementary.ArrayBag;
import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.Bag;
import com.algorithms.elementary.Stack;
import com.algorithms.graph.Digraph;
import com.algorithms.graph.DirectedDFS;

/*
 * Nondeterministic finite automaton
 */
public class NFA {
	
	private char[] re; // match transitions
	private Digraph g;// epsilon transition digraph
	private int m; //number of status
	
	public static void main(String[] args) {
		NFA nfa = new NFA(".*(A*B|AC)D");
		System.out.println(nfa.recognizes("SDXZDASDAAAABD"));
	}
	
	
	public NFA(String regex) {
		//regex = "(" + regex + ")"; //加上() 为了程序更好的判断
		m = regex.length();
		re = regex.toCharArray();
		g = buildEpsilonTransitionDigraph();
	}
	
	//Metacharacters
	//now it only includes ( ) . * |
	private Digraph buildEpsilonTransitionDigraph() {
		Digraph g = new Digraph(m + 1);
		Stack<Integer> ops = new ArrayStack<>(); //operations
		for (int i = 0; i < m; i++) {
			int lp = i; //left Parenthesis
			if (re[i] == '(' || re[i] == '|') 
				ops.push(i);
			else if (re[i] == ')') {
				int or = ops.pop();
				if (re[or] == '|') {
					lp = ops.pop();
					g.addEdge(lp, or + 1);
					g.addEdge(or, i);
				} else lp = or;
			}
			
			if (i < m - 1 && re[i + 1] == '*') { //增加*的构建过程.
				g.addEdge(lp, i + 1);
				g.addEdge(i + 1, lp);
			}
			
			if (re[i] == '(' || re[i] == '*' || re[i] == ')') //这些 wildcard会指向 i + 1 的状态
				g.addEdge(i, i + 1);
		}
		return g;
	}
	
	public boolean recognizes(String txt) {
		
		Bag<Integer> pc = new ArrayBag<>();
		DirectedDFS dfs = new DirectedDFS(g, 0);
		for (int i = 0; i < g.vertices(); i++)
			if (dfs.hasPathTo(i)) pc.add(i);
		
		//所有的字符串都走完 才能判断到底匹配不匹配
		for (int i = 0; i < txt.length(); i++) {
			Bag<Integer> match = new ArrayBag<Integer>();
			
			for (int v : pc) {
				if (v == m) continue; //到了最后一位 if we reach the accept state we have nothing to do
				if (re[v] == txt.charAt(i) || re[v] == '.')
					match.add(v + 1);
			}
			
			dfs = new DirectedDFS(g, match);
			pc = new ArrayBag<Integer>();
			for (int v = 0; v < g.vertices(); v++)
				if (dfs.hasPathTo(v)) pc.add(v);
		}
		
		for (int v : pc) 
			if (v == m) return true;
		
		return false;
	}
	
}
