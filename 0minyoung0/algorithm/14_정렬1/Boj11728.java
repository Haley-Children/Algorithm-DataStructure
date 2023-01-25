// 정렬.boj11728;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11728 {
	static int n,m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[] arr1 = new int[n];
		int[] arr2 = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<m; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		int idx1 = 0;
		int idx2 = 0;
		for (int i=0; i<n+m; i++) {
			if (idx1 == n) sb.append(arr2[idx2++]).append(" ");
			else if (idx2 == m || arr1[idx1] < arr2[idx2]) sb.append(arr1[idx1++]).append(" ");
			else sb.append(arr2[idx2++]).append(" ");
		}
		System.out.println(sb);
	}
}