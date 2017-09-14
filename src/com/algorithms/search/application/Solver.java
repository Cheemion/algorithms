package com.algorithms.search.application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.algorithms.elementary.LinkedStack;
import com.algorithms.elementary.MinPriorityQueue;
import com.algorithms.elementary.Stack;

public class Solver {
	
	public static void main(String[] args) {
		Solver solver = new Solver(new Board( new int[][]{{0,1,3},{4,2,5},{7,8,6}} ));
		if (solver.isSolvable())
			for (Board b : solver.solution()) {
				System.out.println(b);
				System.out.println("\n");
			}
		else
			System.out.println("not solvable");
	}
	
	MinPriorityQueue<Board> board = new MinPriorityQueue<>();
	Stack<Board> storedBoard = new LinkedStack<>();
	private static boolean isGoal = false;
	
    public Solver(Board initial) {
    	board.insert(initial);
    	run(board, storedBoard);
    }
    
    private void run(MinPriorityQueue<Board> board, Stack<Board> storedBoard) {
    	if (isGoal == true) return;
    	Board min = board.delMin();
    	storedBoard.push(min);
    	if (min.isGoal()) {
    		isGoal = true;
    		return;
    	}
    	else {
    		Iterable<Board> neighbors = min.neighbors();
    		for(Board b : neighbors) {
    			b.setAncesstor(min);
    			board.insert(b);
    		}
    		run(board, storedBoard);
    	}
    	if (!isGoal)
    		storedBoard.pop();
    }
    
    
    public boolean isSolvable() {
    	return isGoal == true;
    }
    public int moves() {
    	return storedBoard.size();
    }
    public Iterable<Board> solution() {
    	return storedBoard;
    }
    
}