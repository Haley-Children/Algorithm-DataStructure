import java.io.*;
import java.util.*;

public class Boj2003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// n개의 수로 이루어진 수열에서 i번째 수에서 j번째 수까지의 합이 m이 되는 경우의 수 구하기
		int n = Integer.parseInt(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		
		// 수열
		int[] A = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// 투포인터로 경우의 수 구하기
		int ans = 0;
		int sum = A[0];
		int j = 0;
		find: for (int i=0; i<n; i++) {
			while (sum < m) {
				if (++j == n) break find;
				sum += A[j];
			}
			if (sum == m) ans++;
			sum -= A[i];
		}
		
		// 답 출력
		System.out.println(ans);
	}
}