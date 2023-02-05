// dp.boj2579;

import java.io.*;
import java.util.*;

public class Boj2579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] score = new int[n+1];
		for (int i=1; i<=n; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		int[][] ans = new int[n+1][3];
		ans[1][1] = score[1];
		if (n > 1) {
			ans[2][1] = score[2];
			ans[2][2] = score[1] + score[2];
		}
		if (n > 2) {
			for (int i=3; i<=n; i++) {
				ans[i][1] = Math.max(ans[i-2][1], ans[i-2][2]) + score[i];
				ans[i][2] = ans[i-1][1] + score[i];
			}
		}
		System.out.println(Math.max(ans[n][1], ans[n][2]));
	}
}
