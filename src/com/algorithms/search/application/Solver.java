package com.algorithms.search.application;


import com.algorithms.elementary.ArrayStack;
import com.algorithms.elementary.LinkedStack;
import com.algorithms.elementary.MinPriorityQueue;
import com.algorithms.elementary.Stack;

public class Solver {
	
	public static void main(String[] args) {
		System.out.println("start");
		Solver solver = new Solver(new Board( new int[][]{{0,1,3},{4,2,5},{7,8,6}} ));
		for (Board b : solver.solution()) {
			System.out.println(b);
		}
		System.out.println("done");
	}
	
	
	MinPriorityQueue<Board> boards = new MinPriorityQueue<>();
	Stack<Board> stacks = new ArrayStack<>();
	
    public Solver(Board initial) {
    	boards.insert(initial);
    	solve();
    }
    private void solve() {
    	Board min = boards.delMin();
    	stacks.push(min);
    	if (min.isGoal()) return;
    	else {
    		for (Board b : min.neighbors()) {
    			b.setAncesstor(min);
    			boards.insert(b);
    		}
    	}
    	solve();
    	stacks.pop();
    }
    public boolean isSolvable() {
    	return true;
    }
    public int moves() {
    	return stacks.size();
    }
    public Iterable<Board> solution() {
    	return stacks;
    }
    
}