// 백트래킹.boj6603;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj6603 {
	static int k;
	static int[] nums;
	static boolean[] isUsed;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		while (k!=0) {
			nums = new int[k];
			isUsed = new boolean[k];
			arr = new int[6];
			for (int i=0; i<k; i++) nums[i] = Integer.parseInt(st.nextToken());
			func(0);
			sb.append("\n");
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
		}
		System.out.println(sb);
	}
	private static void func (int n) {
		if (n==6) {
			for (int i=0; i<6; i++) sb.append(nums[arr[i]]).append(" ");
			sb.append("\n");
			return;
		}
		int st = 0;
		if (n!=0) st = arr[n-1];
		for (int i=st; i<k; i++) {
			if (!isUsed[i]) {
				arr[n] = i;
				isUsed[i] = true;
				func(n+1);
				isUsed[i] = false;
			}
		}
	}
}
