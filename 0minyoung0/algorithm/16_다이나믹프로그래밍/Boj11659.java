// dp.boj11659;

import java.io.*;
import java.util.*;

public class Boj11659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] nums = new int[n];
		int[] sums = new int[n];
		st = new StringTokenizer(br.readLine());
		nums[0] = Integer.parseInt(st.nextToken());
		sums[0] = nums[0];
		for (int i=1; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			sums[i] = nums[i] + sums[i-1];
		}
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			if (n1 == 0) {
				sb.append(sums[n2]).append("\n");
			}
			else {
				sb.append(sums[n2]-sums[n1-1]).append("\n");
			}
		}
		System.out.println(sb);
	}
}
