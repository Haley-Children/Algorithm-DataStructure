// 정렬.boj5648;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj5648 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		long[] lArr = new long[n];
		for (int i=0; i<n; i++) {
			while(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			lArr[i] = Long.parseLong(st.nextToken());
		}
		reversedLong(lArr);
		Arrays.sort(lArr);
		for (int i=0; i<n; i++) {
			sb.append(lArr[i]).append("\n");
		}
		System.out.println(sb);
	}
	private static void reversedLong(long[] arr) {
		for (int i=0; i<arr.length; i++) {
			long temp = 0;
			while (arr[i] != 0) {
				temp *= 10;
				temp += arr[i] % 10;
				arr[i] /= 10;
			}
			arr[i] = temp;
		}
	}
}
