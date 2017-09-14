package com.algorithms.search.application;

import java.util.List;

import com.algorithms.elementary.LinkedStack;
import com.algorithms.elementary.MinPriorityQueue;
import com.algorithms.elementary.Queue;
import com.algorithms.elementary.Stack;

public class Solver {
	
	
	public static void main(String[] args) {
		Solver solver = new Solver(new Board( new int[][]{{0,1,3},{4,2,5},{7,8,6}} ));
		for (Board b : solver.solution()) {
			System.out.println(b);
			System.out.println("\n");
		}
	}
	
	MinPriorityQueue<Board> board = new MinPriorityQueue<>();
	Stack<Board> storedBoard = new LinkedStack<>();
    public Solver(Board initial) {
    	board.insert(initial);
    	run(storedBoard);
    }
    
    private void run(Stack<Board> stack) {
    	Board min = board.delMin();
    	stack.push(min);
    	if (min.isGoal()) return;
    	else {
    		Iterable<Board> neighbors = min.neighbors();
    		for(Board b : neighbors) {
    			b.setPreviousPrevious(min);
    			board.insert(b);
    		}
    		run(stack);
    	}
    	stack.pop();
    }
    
    
    public boolean isSolvable() {
    	return true;
    }
    public int moves() {
    	return storedBoard.size();
    }
    public Iterable<Board> solution() {
    	return storedBoard;
    }
    
}