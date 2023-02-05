// dp.boj9095;

import java.io.*;
import java.util.*;

public class Boj9095 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[] arr = new int[T];
		int mx = 0;
		for (int i=0; i<T; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (arr[i] > mx) mx = arr[i];
		}
		int[] ans = new int[mx+1];
		ans[1] = 1;
		ans[2] = 2;
		ans[3] = 4;
		if (mx > 3) {
			for (int i=4; i<=mx; i++) {
				ans[i] = ans[i-1] + ans[i-2] + ans[i-3];
			}
		}
		for (int i=0; i<T; i++) {
			sb.append(ans[arr[i]]).append("\n");
		}
		System.out.println(sb);
	}
}
