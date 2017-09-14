package com.algorithms.search.application;

import com.algorithms.elementary.LinkedStack;
import com.algorithms.elementary.MinPriorityQueue;
import com.algorithms.elementary.Stack;

public class Solver {
	
	public static void main(String[] args) {
		Solver solver = new Solver(new Board( new int[][]{{1,2,3},{4, 5, 0},{7,8,6}} ));
		
		if (solver.isSolvable())
			for (Board b : solver.solution()) {
				System.out.println(b);
				System.out.println("\n");
			}
		else
			System.out.println("not solvable");
		
	}
	
	MinPriorityQueue<Board> board = new MinPriorityQueue<>();
	Stack<Board> stack = new LinkedStack<>();
	Board goal = null;
	Board initial = null;
	
    public Solver(Board initial) {
		this.initial = initial;
    	board.insert(initial);
    	board.insert(initial.twin());
    	run(board);
    }
    
    private void run(MinPriorityQueue<Board> board) {
    	
    	while(true) {
    		Board min = board.delMin();
    		//System.out.println(min);
    		if (min.isGoal()) {
    			goal = min;
    			for (Board b = goal; b != null; b = b.getAncesstor()) {
    				stack.push(b);
    			}
    			break;
    		}
    		else {
        		Iterable<Board> neighbors = min.neighbors();
           		for(Board b : neighbors) 
        			board.insert(b);
    		}
    	}
    	
    }
    
    public boolean isSolvable() {
    	Board pop = stack.pop();
    	stack.push(pop);
    	return pop == initial;
    }
    
    public int moves() {
    	return stack.size();
    }
    public Iterable<Board> solution() {
    	return stack;
    }
    
}