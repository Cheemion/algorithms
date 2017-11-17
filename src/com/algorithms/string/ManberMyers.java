package com.algorithms.string;

import java.util.Arrays;
import static com.algorithms.sort.Sorts.*;

/**
 * using suffix array find the longeset repeated substring
 * In computer science, a suffix array is a sorted array of all suffixes of a string
 * @author altro
 *
 */
public class ManberMyers {
	
	private static final int radix = 256;

	public static void main(String[] args) {
		String[] suffixes = suffixes("999zsdfasdf9");
		System.out.println("Original: " + Arrays.toString(suffixes));
		sort(suffixes);
		System.out.println(Arrays.toString(suffixes));
	}
	
	public static String lrs(String s) {
		String lrs = "";
		String[] originalSuffixes = StringUtil.suffixes(s); //������һ���ַ�
		sort(originalSuffixes);
		return lrs;
	}
	
	public static void sort(String[] original) {
		int[] sortedToOriginal = new int[original.length];
		int[] originalToSorted = new int[original.length];
		sort(original, sortedToOriginal, originalToSorted);
	}
	
	// lo and hi both are inclusive
	// in this case each String is distinct
	public static void quickSort3WayViaManberMyersMethod(String[] ss, int lo, int hi, int[] sortedToOriginal, int[] originalToSorted, int sortedDigit) {
		
		if (hi <= lo) return;
		//lt��߱�FirstCharС, gt�ұ߱�firstChar��
		int i = lo + 1, gt = hi, lt = lo;
		
		int value = originalToSorted[sortedToOriginal[lo] + sortedDigit];
		
		while (i <= gt) {
			int t = originalToSorted[sortedToOriginal[i] + sortedDigit];
			if (t < value) {
				originalToSorted[sortedToOriginal[lt]] = i;
				originalToSorted[sortedToOriginal[i]] = lt;
				int temp = sortedToOriginal[lt];
				sortedToOriginal[lt] = sortedToOriginal[i];
				sortedToOriginal[i] = temp;
				exch(ss, lt++, i++);
			} else if (t > value) {
				originalToSorted[sortedToOriginal[gt]] = i;
				originalToSorted[sortedToOriginal[i]] = gt;
				int temp = sortedToOriginal[gt];
				sortedToOriginal[gt] = sortedToOriginal[i];
				sortedToOriginal[i] = temp;
				exch(ss, i, gt--);
			} else i++;
		}
		quickSort3WayViaManberMyersMethod(ss, lo, lt - 1, sortedToOriginal, originalToSorted, sortedDigit);
		quickSort3WayViaManberMyersMethod(ss, lt, gt, sortedToOriginal, originalToSorted, sortedDigit * 2);
		quickSort3WayViaManberMyersMethod(ss, gt + 1, hi, sortedToOriginal, originalToSorted, sortedDigit);
	}
	
	//originalIndex meaning sortedIndex -> originalIndex
	//sortedIndex meaning originalIndex -> sortedIndex
	//���ݵ�һ�� char ������
	public static void sort(String[] ss, int[] sortedToOriginal, int[] originalToSorted) {
		String[] firstSorted = new String[ss.length];
		//�Ȱѵ�һ����ĸ������
		int[] count = new int[radix + 2];
		int[] count1 = null;
		
		for (int i = 0; i < ss.length; i++) {
			count[charAt(ss[i], 0) + 2]++;
		}
		
		for (int i = 0; i < count.length - 1; i++) {
			count[i + 1] = count[i] + count[i + 1]; //�ۼ�����  (r)
		}
		count1 = Arrays.copyOf(count, count.length);
		
		for (int i = 0; i < ss.length; i++) { //�����ۼӵ����飬������������
			sortedToOriginal[count[charAt(ss[i], 0) + 1]] = i;
			originalToSorted[i] = count[charAt(ss[i], 0) + 1];
			firstSorted[count[charAt(ss[i], 0) + 1]++] = ss[i];
		}
		
		for (int i = 0; i < ss.length; i++) {
			ss[i] = firstSorted[i];
		}
		
		//���ݵ�һ�������˳�򣬾�����������ô����ͷһ����ĸ��ͬ������һ��
		for (int i = 0; i < count1.length - 1; i++) {
			if (count1[i + 1] - count1[i] > 1)
				quickSort3WayViaManberMyersMethod(ss , count1[i], count[i + 1] - 1, sortedToOriginal, originalToSorted, 1);
		}
		
	}
	
	
	private static int charAt(String s, int d) {
		if (d < s.length()) return s.charAt(d);
		else return -1;
	}
	
	// include the empty suffix ""
	public static String[] suffixes(String s) {
		int n = s.length();
		String[] suffixes = new String[n + 1];
		suffixes[n] = "";
		for (int i = 0; i < n; i++) 
			suffixes[i] = s.substring(i, n); // endIndex is exclusive
		return suffixes;
	}
}
