import java.io.*;
import java.util.*;

public class Boj1806 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 길이 n짜리 수열에서 연속된 수들의 부분합이 s이상인 것 중 가장 짧은 것의 길이 구하기
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		// 10000이하의 자연수 n개로 이루어진 수열 A
		int[] A = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// 투포인터로 s 이상이면서 최소인 차이 찾기
		int ans = Integer.MAX_VALUE;
		int sum = A[0];
		int y = 0;
		find: for (int x=0; x<n; x++) {
			while (sum < s) {
				if (++y == n) break find;
				sum += A[y];
			}
			
			if (y - x + 1 < ans) ans = y - x + 1;
			
			sum -= A[x];
		}
		
		// 찾지 못한 경우
		if (ans == Integer.MAX_VALUE) ans = 0;
		
		// 답 출력
		System.out.println(ans);
	}
}