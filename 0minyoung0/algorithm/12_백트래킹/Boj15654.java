// 백트래킹.boj15654;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15654 {
	static int n,m;
	static int[] nums;
	static int[] arr;
	static boolean[] isUsed;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		nums = new int[n];
		arr = new int[m];
		isUsed = new boolean[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
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
		for (int i=0; i<n; i++) {
			if (!isUsed[i]) {
				arr[k] = nums[i];
				isUsed[i] = true;
				func(k+1);
				isUsed[i] = false;
			}
		}
	}
}
