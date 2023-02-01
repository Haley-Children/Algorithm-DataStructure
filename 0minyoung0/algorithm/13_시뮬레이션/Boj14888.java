// 시뮬레이션.boj14888;

import java.io.*;
import java.util.*;

public class Boj14888 {
	static int n;
	static int[] nums;
	static int[] oper;
	static int[] oUsed;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		oper = new int[4];
		oUsed = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}
		calculate(1, nums[0]);
		System.out.println(max);
		System.out.println(min);
	}
	private static void calculate(int k, int curAns) {
		if (k == n) {
			if (max < curAns) max = curAns;
			if (min > curAns) min = curAns;
			return;
		}
		if (oUsed[0] < oper[0]) {
			oUsed[0]++;
			calculate(k+1, curAns+nums[k]);
			oUsed[0]--;
		}
		if (oUsed[1] < oper[1]) {
			oUsed[1]++;
			calculate(k+1, curAns-nums[k]);
			oUsed[1]--;
		}
		if (oUsed[2] < oper[2]) {
			oUsed[2]++;
			calculate(k+1, curAns*nums[k]);
			oUsed[2]--;
		}
		if (oUsed[3] < oper[3]) {
			oUsed[3]++;
			calculate(k+1, curAns/nums[k]);
			oUsed[3]--;
		}
	}
}