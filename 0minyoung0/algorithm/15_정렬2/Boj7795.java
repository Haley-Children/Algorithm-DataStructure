// 정렬.boj7795;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj7795 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] arrA = new int[n];
			int m = Integer.parseInt(st.nextToken());
			int[] arrB = new int[m];
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				arrA[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arrA);
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				arrB[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arrB);
			int ans = 0;
			int idxA = 0;
			int idxB = 0;
			while (idxA != n && idxB != m) {
				if (arrA[idxA] > arrB[idxB]) {
					ans += n - idxA;
					idxB++;
				}
				else {
					idxA++;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}