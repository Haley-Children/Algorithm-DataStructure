// 백트래킹.boj15663;

import java.util.Arrays;
import java.util.Scanner;

public class Boj15663 {
	static int n,m;
	static int[] nums;
	static boolean[] isUsed;
	static int[] arr;
	static int[] recent;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		nums = new int[n];
		isUsed = new boolean[n];
		arr = new int[m];
		recent = new int[m];
		for (int i=0; i<n; i++) nums[i] = sc.nextInt();
		Arrays.sort(nums);
		func(0);
		System.out.println(sb);
	}
	private static void func (int k) {
		if (k==m) {
			for (int i=0; i<m; i++) {
				sb.append(nums[arr[i]]).append(" ");
			}
			sb.append("\n");
			return;
			}
		for (int i=0; i<n; i++) {
			if (!isUsed[i] && (i==0 || nums[i-1] != nums[i] || isUsed[i-1])) {
				arr[k] = i;
				isUsed[i] = true;
				func(k+1);
				isUsed[i] = false;
			}
		}
	}
}
