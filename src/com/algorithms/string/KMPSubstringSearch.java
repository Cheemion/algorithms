package com.algorithms.string;

import java.util.Arrays;
// time propertional to N
public class KMPSubstringSearch{
	
	private static final int R = 256; //Ĭ��2^8
	private int[][] dfa;
	private String pattern;
	
	public KMPSubstringSearch(String pattern) {
		this.pattern = pattern;
		this.dfa = dfaIngeniousWay(pattern, R);
	}
	
	public static void main(String[] args) {
		KMPSubstringSearch w = new KMPSubstringSearch("ababac");
		int s = w.search("1234sababacsssc");
		System.out.println(s);
		System.out.println("1234sababacsssc".substring(s, s + 6));
	}
	
	private static int[][] dfaIngeniousWay(String pat, int radix) {
		int m = pat.length();
		int[][] dfa = new int[radix][m]; //��״̬ ��ѡ��
		dfa[pat.charAt(0)][0] = 1;
		for (int x = 0, j = 1; j < m; j++) {
			for (int c = 0; c < R; c++) {
				dfa[c][j] = dfa[c][x];
			}
			dfa[pat.charAt(j)][j] = j + 1;
			x = dfa[pat.charAt(j)][x];
		}
		return dfa;
	}
	
	
	//��һ����ʾ״̬���ڶ�����ʾradix
	private static int[][] dfaMyWay(String pat, int radix) {
		int m = pat.length();
		int[][] dfa = new int[radix][m]; //��״̬ ��ѡ��
		//������ƥ���ֵ�޸ĳɶ�Ӧ��״̬
		for (int i = 0; i < m; i++)
			dfa[pat.charAt(i)][i] = i + 1;
		
		//ֱ�Ӵ�1��״̬��ʼƥ��
		//0 state �Ѿ������
		for (int i = 1; i < m; i++) {
			String knownString = pat.substring(1, i);
			int failedState = failedState(knownString, dfa);
			char passCh = pat.charAt(i);
			for (int j = 0; j < radix; j++)
				if (j != passCh)
					dfa[j][i] = dfa[j][failedState];
		}
		return dfa;
	}
	
	private static int failedState(String knownString, int[][] dfa) {
		int state = 0;
		for (int i = 0; i < knownString.length(); i++) {
			char c = knownString.charAt(i);
			state = dfa[c][state];
		}
		return state;
	}
	
	public int search(String txt) {
		int i, j, n = txt.length(), m = pattern.length();
		for (i = 0, j = 0; i < n && j < m; i++)
			j = dfa[txt.charAt(i)][j];
		if (j == m) return i - m;
		else return n;
	}
}
