// 백트래킹.boj15651;

import java.util.Scanner;

public class Boj15651 {
	static int m;
	static int n;
	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[m];
		func(0);
		System.out.println(sb);
	}
	private static void func(int k) {
		if (k==m) {
			for (int i=0; i<m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i=0; i<n; i++) {
			arr[k] = i+1;
			func(k+1);
		}
	}
}
