// 백트래킹.boj15655;

import java.util.Arrays;
import java.util.Scanner;

public class Boj15655 {
	static int n,m;
	static int nums[];
	static boolean isUsed[];
	static int arr[];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		nums = new int[n];
		isUsed = new boolean[n];
		arr = new int[m];
		for (int i=0; i<n; i++) {
			nums[i] = sc.nextInt();
		}
		Arrays.sort(nums);
		func(0, -1);
		System.out.println(sb);
	}
	private static void func (int k, int pre) {
		if (k==m) {
			for (int i=0; i<m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i=pre+1; i<n; i++) {
			if (!isUsed[i]) {
				arr[k] = nums[i];
				isUsed[i] = true;
				func(k+1, i);
				isUsed[i] = false;
			}
		}
	}
}
