package com.algorithms.search.application;

import com.algorithms.elementary.ArrayBag;
import com.algorithms.elementary.Bag;

public class Board implements Comparable<Board>{
	
	public static void main(String[] args) {
		
		Board board = new Board( new int[][]{{0,1,3},{4,2,5},{7,8,6}}); 
		System.out.println("done");
		
	}
	
	private int[][] blocks;
	//private static int[][] goal = new int[][]{{1,2,3},{4,5,6},{7,8,0}};
	private static int[][] goal = new int[][]{{1,2,3},{4,5,6}, {7,8,0}};
	
	private int hamming;
	private int manhattan;
	private int blankI;
	private int blankJ;
	private Board ancestor;
	
	//You may assume that the constructor receives an n-by-n array containing the n2 integers between 0 and n2 − 1
	//where 0 represents the blank square.
	// (where blocks[i][j] = block in row i, column j)
	
	public Board(int[][] blocks) { 
		this(blocks, null);
	}
	
    public Board(int[][] blocks,Board ancestor) {
    	this.ancestor = ancestor;
    	this.blocks = blocks;
    	for (int i = 0; i < goal.length; i++) {
			for (int j = 0; j < goal[i].length; j++) {
				int value = goal[i][j];
				for (int i1 = 0; i1 < blocks.length; i1++) {
					for (int j1 = 0; j1 < blocks.length; j1++) {
						if (blocks[i1][j1] == 0) { blankI = i1; blankJ = j1; }
						if (blocks[i1][j1] == value) {
							if (i1 != i || j1 != j) hamming++;
							manhattan = manhattan + Math.abs(i1 - i) + Math.abs(j1 - j);
						}
					}
				}
				
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
    		return new Board(exchange(1, 0, 1, 1, copy(blocks)));
    	else 
    		return new Board(exchange(0, 0, 0, 1, copy(blocks)));
    }
    
    
    public boolean equals(Board other) {
    	if (other == this) return true;
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
    		int[][] newBlock = copy(blocks);
    		exchange(blankI, blankJ, blankI, blankJ - 1, newBlock);
    		if (ancestor == null || !ancestor.equals(new Board(newBlock))) 
    		bag.add(new Board(newBlock, this));
    	}
    	
    	if (blankJ + 1 >=0 && blankJ + 1 < dimension()) {
    		
    		
    		//右边边移动
    		int[][] newBlock = copy(blocks);
    		exchange(blankI, blankJ, blankI, blankJ + 1, newBlock);
    		
    		if (ancestor == null || !ancestor.equals(new Board(newBlock))) 
    		bag.add(new Board(newBlock, this));
    	}
    	
    	if (blankI + 1 >=0 && blankI + 1 < dimension()) {
    		//下边移动
    		int[][] newBlock = copy(blocks);
    		exchange(blankI, blankJ, blankI + 1, blankJ, newBlock);
    		
    		if (ancestor == null || !ancestor.equals(new Board(newBlock))) 
    		bag.add(new Board(newBlock, this));
    	}
    	
    	if (blankI - 1 >=0 && blankI - 1 < dimension()) {
    		//上边移动
    		int[][] newBlock = copy(blocks);
    		exchange(blankI, blankJ, blankI - 1, blankJ, newBlock);
    		
    		if (ancestor == null || !ancestor.equals(new Board(newBlock))) 
    		bag.add(new Board(newBlock, this));
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
	
	public Board getAncesstor() {
		return ancestor;
	}

	public void setAncesstor(Board ancestor) {
		this.ancestor = ancestor;
	}
	
	private int[][] copy(int[][] origin) {
		int[][] returnValue = new int[origin.length][origin[0].length];
		for (int i = 0; i < returnValue.length; i++) {
			for (int j = 0; j < returnValue[i].length; j++) {
				returnValue[i][j] = origin[i][j];
			}
		}
		return returnValue;
	}
	
}