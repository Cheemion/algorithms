package com.algorithms.string;

public class RabinKarp {
	
	private static final long R = 256;
	private static final long Q = 997; //Q 越大 冲突越小,值可以取到 10^20
	private int m;   	 //pattern length
	private long rm; 	 // R^(m - 1) % Q  需要被减去的值
	private long patHash;// pattern hash value
	
	public static void main(String[] args) {
		RabinKarp rk = new RabinKarp("22s");
		int search = rk.search("aa22ss");
		System.out.println(search);
	}
	
	
	public RabinKarp(String pat) {
		m = pat.length();
		rm = 1;
		for (int i = 1; i <= m - 1; i++) {
			rm = (R * rm) % Q;
		}
		patHash = hash(pat, m);
	}
	
	//Compute hash for m-digit key
	private long hash(String key, int m) {
		long h = 0;
		for (int j = 0; j < m; j++)
			h = (R * h + key.charAt(j)) % Q;
		return h;
	}
	
	private int search(String txt) {
		int n = txt.length();
		long txtHash = hash(txt, m);
		if (patHash == txtHash) return 0;
		for (int i = m; i < n; i++) {
			txtHash = (txtHash + Q - rm * txt.charAt(i - m) % Q) % Q;
			txtHash = (txtHash * R + txt.charAt(i)) % Q;
			if (patHash == txtHash) return i - m + 1;
		}
		return n;
	}
	
}
