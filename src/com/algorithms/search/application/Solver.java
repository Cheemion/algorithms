package com.algorithms.search.application;

public class Solver {
	
    public Solver(Board initial) {
    	// find a solution to the initial board (using the A* algorithm)
    }
    public boolean isSolvable() {
    	// is the initial board solvable?
    }
    public int moves() {
    	// min number of moves to solve initial board; -1 if unsolvable
    }
    public Iterable<Board> solution() {
    	// sequence of boards in a shortest solution; null if unsolvable
    }
    
}