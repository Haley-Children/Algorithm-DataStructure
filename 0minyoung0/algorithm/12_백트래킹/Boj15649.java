// 백트래킹.boj15649;

import java.util.Scanner;

public class Boj15649 {
	static int[] nums;
	static boolean[] used;
	static int n, m;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		nums = new int[n];
		used = new boolean[n];
		backTracking(0);
		System.out.println(sb);
	}
	private static void backTracking(int k) {
		if (k==m) {
			for (int i=0; i<m; i++) {
				sb.append(nums[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i=0; i<n; i++) {
			if (!used[i]) {
				nums[k] = i+1;
				used[i] = true;
				backTracking(k+1);
				used[i] = false;
			}
		}
	}
}
