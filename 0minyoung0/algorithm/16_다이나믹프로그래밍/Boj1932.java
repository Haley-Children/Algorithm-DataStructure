// dp.boj1932;

import java.io.*;
import java.util.*;

public class Boj1932 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n+1][n+1];
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=i; j++) {
				if (j == 1) {
					arr[i][j] = arr[i-1][j] + Integer.parseInt(st.nextToken());
				}
				else if (j == i) {
					arr[i][j] = arr[i-1][j-1] + Integer.parseInt(st.nextToken());
				}
				else {
					arr[i][j] = Math.max(arr[i-1][j-1], arr[i-1][j]) + Integer.parseInt(st.nextToken());
				}
			}
		}
		int ans = 0;
		for (int i=1; i<=n; i++) {
			if (ans < arr[n][i]) ans = arr[n][i];
		}
		System.out.println(ans);
	}
}
