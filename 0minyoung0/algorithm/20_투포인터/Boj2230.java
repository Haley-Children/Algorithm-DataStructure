import java.io.*;
import java.util.*;

public class Boj2230 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// n개의 정수 중 차이가 m 이상이면서 제일 작은 경우를 구하기
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// n개의 정수로 이루어진 수열 A
		int[] A = new int[n];
		for (int i=0; i<n; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		// 수열 정렬
		Arrays.parallelSort(A);
		
		// 투포인터로 m 이상이면서 최소인 차이 찾기
		int ans = Integer.MAX_VALUE;
		int e = 0;
		find: for (int s=0; s<n; s++) {
			while (A[e] - A[s] < m) {
				if (++e == n) break find;
			}
			if (A[e] - A[s] < ans) ans = A[e] - A[s];
		}
		
		// 답 출력
		System.out.println(ans);
	}
}