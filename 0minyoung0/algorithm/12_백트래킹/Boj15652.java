// 백트래킹.boj15652;

import java.util.Scanner;

public class Boj15652 {
	static int n, m;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[m];
		func(0);
		System.out.println(sb);
	}
	private static void func (int k) {
		if (k==m) {
			for (int i=0; i<m; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		if (k!=0) {
			for (int i=arr[k-1]-1; i<n; i++) {
				arr[k] = i+1;
				func(k+1);
			}
		}
		else {
			for (int i=0; i<n; i++) {
				arr[k] = i+1;
				func(k+1);
			}
		}
	}
}
