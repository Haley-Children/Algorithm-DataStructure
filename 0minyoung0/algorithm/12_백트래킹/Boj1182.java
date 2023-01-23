// 백트래킹.boj1182;

import java.util.Scanner;

public class Boj1182 {
	static int n, s;
	static int cnt;
	static int[] nums;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		s = sc.nextInt();
		nums = new int[n];
		for (int i=0; i<n; i++) {
			nums[i] = sc.nextInt();
		}
		cntSub(0,0);
		if (s==0) cnt--;
		System.out.println(cnt);
	}
	private static void cntSub(int k, int curSum) {
		if (k==n) {
			if (curSum == s) cnt++;
			return;
		}
		cntSub(k+1, curSum);
		cntSub(k+1, curSum+nums[k]);
	}
}
