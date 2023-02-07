// 그리디.boj2217;

import java.io.*;
import java.util.*;

public class Boj2217 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<Integer> ropes = new ArrayList<>();
		for (int i=0; i<n; i++) {
			ropes.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(ropes);
		int ans = 0;
		for (int i=0; i<n; i++) {
			int temp = ropes.get(i) * (n - i);
			if (temp > ans) {
				ans = temp;
			}
		}
		System.out.println(ans);
	}
}
