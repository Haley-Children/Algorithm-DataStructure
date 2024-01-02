package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분수열의 합 -> 다시 풀어보기
public class Main_1182 {

	static int n, s;
	static int cnt = 0;
	static int[] arr;
	static int[] isUsed;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		isUsed = new int[n];

		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		recur(0, 0);
		System.out.println(s == 0 ? cnt - 1 : cnt); // 합이 0인 경우 공집합 빼기
	}

	private static void recur(int sum, int depth) {
		if (depth == n) {
			if (sum == s) {
				cnt++;
			}
			return;
		}
		recur(sum, depth + 1); // 더하지 않는 경우
		recur(sum + arr[depth], depth + 1); // 더하는 경우
	}
}