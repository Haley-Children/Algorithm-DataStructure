import java.io.*;
import java.util.*;

public class Boj13144 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 수열의 길이
		int n = Integer.parseInt(br.readLine());
		
		// 수열을 나타내는 n개의 정수
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 투포인터로 찾기!
		long ans = 0;
		boolean[] v = new boolean[100001];
		int e = 0;
		v[arr[0]] = true;
		for (int s=0; s<n; s++) {
			while (e+1 != n && !v[arr[e+1]]) {
				v[arr[++e]] = true;
			}
			ans += e - s + 1;
			v[arr[s]] = false;
		}
		
		// 답 출력
		System.out.println(ans);
	}
}