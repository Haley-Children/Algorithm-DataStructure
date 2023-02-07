// 백트래킹.boj15666;

import java.util.Arrays;
import java.util.Scanner;

public class Boj15666 {
	static int n,m;
	static int[] nums;
	static boolean[] isUsed;
	static int[] idx;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		nums = new int[n];
		isUsed = new boolean[n];
		idx = new int[m];
		for (int i=0; i<n; i++) nums[i] = sc.nextInt();
		Arrays.sort(nums);
		func(0);
		System.out.println(sb);
	}
	private static void func (int k) {
		if (k==m) {
			for (int i=0; i<m; i++) sb.append(nums[idx[i]]).append(" ");
			sb.append("\n");
			return;
		}
		int st = 0;
		if (k!=0) st = idx[k-1];
		for (int i=st; i<n; i++) {
			if (i==0 || nums[i-1]!=nums[i] || isUsed[i-1]) {
				idx[k] = i;
				isUsed[i] = true;
				func(k+1);
				isUsed[i] = false;
			}
		}
	}
}
