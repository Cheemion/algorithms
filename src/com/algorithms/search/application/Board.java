package com.algorithms.search.application;

import com.algorithms.elementary.ArrayBag;
import com.algorithms.elementary.Bag;

public class Board implements Comparable<Board>{
	
	private int blocks[][];
	private Board goal;
	private int hamming;
	private int manhattan;
	private int blankI;
	private int blankJ;
	//You may assume that the constructor receives an n-by-n array containing the n2 integers between 0 and n2 − 1
	//where 0 represents the blank square.
	// (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
    	this.blocks = blocks;
    	this.goal = new Board( new int[][]{{1,2,3},{4,5,6},{7,8,9}} );
    }
    
    public Board(int[][] blocks, Board goal) {
    	this.blocks = blocks;
    	this.goal = goal;
    	//n ^ 2
    	for (int i = 0; i < goal.blocks.length; i++) {
			for (int j = 0; j < goal.blocks[i].length; j++) {
				int value = goal.blocks[i][j];
				
				
				for (int i1 = 0; i1 < blocks.length; i1++) {
					for (int j1 = 0; j1 < blocks.length; j1++) {
						if (blocks[i1][j1] == 0) { blankI = i1; blankJ = j1; }
						if (blocks[i1][j1] == value) {
							if (i1 != i || j1 != j) hamming++;
							manhattan = manhattan + Math.abs(i1 - i) + Math.abs(j1 - j);
						}
					}
				}
				
				throw new RuntimeException("the contained number is not the same");
			}
		}
    }
    
    public int dimension() {
    	return blocks.length;
    }
    public int hamming() {
    	return hamming;
    }
    public int manhattan() {
    	return manhattan;
    }
    public boolean isGoal() {
    	return hamming == 0;
    }
    
    public Board twin() {
    	if (blocks[0][0] == 0 || blocks[0][1] == 0)
    		return new Board(exchange(1, 0, 1, 1, blocks.clone()), goal);
    	else 
    		return new Board(exchange(0, 0, 0, 1, blocks.clone()), goal);
    }
    
    
    public boolean equals(Board other) {
    	if (this.dimension() != other.dimension()) return false;
    	for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				if (this.blocks[i][j] != other.blocks[i][j])
					return false;
			}
		}
    	return true;
    }
    public Iterable<Board> neighbors() {
    	Bag<Board> bag = new ArrayBag<>();
    	
    	if (blankJ - 1 >=0 && blankJ - 1 < dimension()) {
    		//左边移动
    		int[][] newBlock = blocks.clone();
    		exchange(blankI, blankJ, blankI, blankJ - 1, newBlock);
    		bag.add(new Board(newBlock, goal));
    	}
    	
    	if (blankJ + 1 >=0 && blankJ + 1 < dimension()) {
    		//右边边移动
    		int[][] newBlock = blocks.clone();
    		exchange(blankI, blankJ, blankI, blankJ + 1, newBlock);
    		bag.add(new Board(newBlock, goal));
    	}
    	
    	if (blankI + 1 >=0 && blankI + 1 < dimension()) {
    		//下边移动
    		int[][] newBlock = blocks.clone();
    		exchange(blankI, blankJ, blankI + 1, blankJ, newBlock);
    		bag.add(new Board(newBlock, goal));
    	}
    	
    	if (blankI - 1 >=0 && blankI - 1 < dimension()) {
    		//上边移动
    		int[][] newBlock = blocks.clone();
    		exchange(blankI, blankJ, blankI - 1, blankJ, newBlock);
    		bag.add(new Board(newBlock, goal));
    	}
    	return bag;
    }
    
    public String toString() {
    	StringBuffer stringBuffer = new StringBuffer();
    	for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[i].length; j++) {
				stringBuffer.append(blocks[i][j]);
				stringBuffer.append(" ");
			}
			stringBuffer.append('\n');
		}
    	return stringBuffer.toString();
    }
    
    private int[][] exchange(int i, int j, int i1, int j1, int[][] block) {
    	int value = block[i][j];
    	block[i][j] = block[i1][j1];
    	block[i1][j1] = value;
    	return block;
    }

	@Override
	public int compareTo(Board o) {
		if (o == null) throw new NullPointerException();
		if (manhattan > o.manhattan) return 1;
		else if (manhattan < o.manhattan) return -1;
		
		return 0;
	}
    
}