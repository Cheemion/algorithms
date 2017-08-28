package com.algorithms.elementary.application;
/**
 * Dynamic connectivity solution to estimate percolation threshold
 * @author altro
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.algorithms.elementary.PathCompressionWeightedQuickUnion;

import javafx.util.Pair;

public class Percolation {
	
	public static Random rand = new Random();
	
	public static void main(String[] args) {

		int size = 10000;
		Percolation percolation = new Percolation(size);
		while(!percolation.percolates()) {
			int row = rand.nextInt(size);
			int col = rand.nextInt(size);
			//System.out.println("("+ row + "," + col +")" + percolation.isOpen(row, col));
			if (!percolation.isOpen(row, col)) {
				percolation.open(row, col);
			}
		}
		System.out.println(percolation.numberOfOpenSites());
		System.out.println(1.0 * percolation.numberOfOpenSites() / (size * size));
	}
	
	//size + 1 当作top;
	//size + 2 当作 bottom;
	//因此只要判断top 和 bottom是否连在一起就可以了
	private int topId;
	private int bottomId;
	private int size;
	PathCompressionWeightedQuickUnion uf;
	
	//流通方块的大小，白色方块的大小
	private int openSiteNum = 0;
	
	//0 black means blocked, 1 white means open
	private int siteColor[];
	
	//size means column size because its a square
	public Percolation(int size) {
		siteColor = new int[size * size + 2];
		siteColor[size * size] = 1;
		siteColor[size * size + 1] = 1;
		this.size = size;
		uf = new PathCompressionWeightedQuickUnion(size * size + 2);
		topId = size * size;
		bottomId = size * size + 1;
	}
	
     //从0开始
	 public void open(int row, int col) {
		 
		 if (isOpen(row, col)) return;
		 
		 int id = row * size + col;
		 //在四周
		 if (row == 0 || row == size -1  || col == 0 || col == size - 1) {
			 
			 Pair<Integer, Integer> top = new Pair<>(topId / size, topId % size);
			 Pair<Integer, Integer> bottom = new Pair<>(bottomId / size, bottomId % size);
			 
			 List<Pair<Integer, Integer>> list =  new ArrayList<>();
			 
			 if(row == 0) {
				 list.add(top);
				 list.add(new Pair<>(row + 1, col));
			 } else {
				 list.add(bottom);
				 list.add(new Pair<>(row - 1, col));
			 }
			 
			 if(col == 0) {
				 list.add(new Pair<>(row, col + 1));
			 } else {
				 list.add(new Pair<>(row, col - 1));
			 }
			 
			 list.forEach(pair -> {
				 int rowTemp = pair.getKey();
				 int colTemp = pair.getValue();
				 int idTemp = rowTemp * size + colTemp;
				 if (isOpen(rowTemp, colTemp))
					 uf.union(id, idTemp);
			 });
			 
			 
		 } else {
			 Pair<Integer, Integer> top = new Pair<Integer, Integer>(row - 1, col);
			 Pair<Integer, Integer> bottom = new Pair<Integer, Integer>(row + 1, col);
			 Pair<Integer, Integer> right = new Pair<Integer, Integer>(row, col + 1);
			 Pair<Integer, Integer> left = new Pair<Integer, Integer>(row, col - 1);
			 List<Pair<Integer, Integer>> list = Arrays.asList(top, bottom, right, left);
			 list.forEach(pair -> {
				 int rowTemp = pair.getKey();
				 int colTemp = pair.getValue();
				 int idTemp = rowTemp * size + colTemp;
				 if (isOpen(rowTemp, colTemp))
					 uf.union(id, idTemp);
			 });
		 }
		 
		 siteColor[id] = 1;
		 openSiteNum++;
	 }
	 
	 public boolean isOpen(int row, int col) {
		 int index = row * size + col;
		 return siteColor[index] == 1;
	 }
	 
	 public boolean isFull(int row, int col) {
		 int index = row * size + col;
		 return uf.connected(index, topId);
	 }
	 
     public int numberOfOpenSites() {
		  return openSiteNum;
	 }
     
	 public boolean percolates() {
		 return uf.connected(topId, bottomId);
	 }
	 
}
