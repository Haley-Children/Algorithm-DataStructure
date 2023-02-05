// dp.boj1149;

import java.io.*;
import java.util.*;

public class Boj1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] cost = new int[n][3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		cost[0][0] = Integer.parseInt(st.nextToken());
		cost[0][1] = Integer.parseInt(st.nextToken());
		cost[0][2] = Integer.parseInt(st.nextToken());
		for (int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken()) + Math.min(cost[i-1][1], cost[i-1][2]);
			cost[i][1] = Integer.parseInt(st.nextToken()) + Math.min(cost[i-1][0], cost[i-1][2]);
			cost[i][2] = Integer.parseInt(st.nextToken()) + Math.min(cost[i-1][0], cost[i-1][1]);
		}
		System.out.println(Math.min(cost[n-1][0],Math.min(cost[n-1][1],cost[n-1][2])));
	}
}
