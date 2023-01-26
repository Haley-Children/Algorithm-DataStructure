// 정렬.boj15688;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj15688 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] cnt = new int[2000001];
		for (int i=0; i<n; i++) {
			cnt[Integer.parseInt(br.readLine())+1000000]++;
		}
		for (int i=-1000000; i<=1000000; i++) {
			for (int j=0; j<cnt[i+1000000]; j++) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb);
	}
}