// dp.boj1003;

import java.io.*;
import java.util.*;

public class Boj1003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] ans = new int[41][2];
		ans[0][0] = 1;
		ans[1][1] = 1;
		for (int i=2; i<=40; i++) {
			ans[i][0] = ans[i-1][0] + ans[i-2][0];
			ans[i][1] = ans[i-1][1] + ans[i-2][1];
		}
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(ans[n][0]).append(" ").append(ans[n][1]).append("\n");
		}
		System.out.println(sb);
	}
}
