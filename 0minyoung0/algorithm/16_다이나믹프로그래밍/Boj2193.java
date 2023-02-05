// dp.boj2193;

import java.io.*;
import java.util.*;

public class Boj2193 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] cnt = new long[n+1][2];
		cnt[1][1] = 1;
		for (int i=2; i<=n; i++) {
			cnt[i][0] = cnt[i-1][0] + cnt[i-1][1];
			cnt[i][1] = cnt[i-1][0];
		}
		System.out.println(cnt[n][0] + cnt[n][1]);
	}
}
