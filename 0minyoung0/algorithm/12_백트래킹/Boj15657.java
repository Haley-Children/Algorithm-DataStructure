// 백트래킹.boj15657;

import java.util.Arrays;
import java.util.Scanner;

public class Boj15657 {
	static int n,m;
	static int[] nums;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		nums = new int[n];
		arr = new int[m];
		for (int i=0; i<n; i++) {
			nums[i] = sc.nextInt();
		}
		Arrays.sort(nums);
		func(0);
		System.out.println(sb);
	}
	private static void func(int k) {
		if (k==m) {
			for (int i=0; i<m; i++) {
				sb.append(nums[arr[i]]).append(" ");
			}
			sb.append("\n");
			return;
		}
		int st = 0;
		if (k!=0) st = arr[k-1];
		for (int i=st; i<n; i++) {
			arr[k]=i;
			func(k+1);
		}
	}
}
