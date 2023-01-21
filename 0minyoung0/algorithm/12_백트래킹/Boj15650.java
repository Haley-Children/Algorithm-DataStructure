// 백트래킹.boj15650;

import java.util.Scanner;

public class Boj15650 {
	static int m, n;
	static int[] arr;
	static boolean[] isUsed;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		arr = new int[n];
		isUsed = new boolean[m];
		func(0);
		System.out.println(sb);
	}
	private static void func(int k){
		if (k==n) {
			for (int i=0; i<n; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i=0; i<m; i++) {
			if ((k==0 || i+1 > arr[k-1]) && !isUsed[i]) {
				arr[k] = i + 1;
				isUsed[i] = true;
				func(k+1);
				isUsed[i] = false;
			}
		}
	}
}
