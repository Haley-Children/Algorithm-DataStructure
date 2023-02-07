// dp.boj11726;

import java.io.*;
import java.util.*;

public class Boj11726 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if (n == 1) {
			System.out.println(1);
			return;
		}
		else if (n == 2) {
			System.out.println(2);
			return;
		}
		int[] ans = new int[n];
		ans[0] = 1;
		ans[1] = 2;
		for (int i=2; i<n; i++) {
			ans[i] = (ans[i-1] + ans[i-2]) % 10007;
		}
		System.out.println(ans[n-1]);
	}
}
