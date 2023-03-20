import java.io.*;
import java.util.*;

public class Boj7453 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 배열의 크기
		int n = Integer.parseInt(br.readLine());
		
		// 배열 선언
		int[] A = new int[n];
		int[] B = new int[n];
		int[] C = new int[n];
		int[] D = new int[n];
		
		// 배열에 값 넣기
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		
		// AB의 합 배열과 CD의 합 배열 만들기
		int[] AB = new int[n*n];
		int[] CD = new int[n*n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				AB[i*n+j] = A[i] + B[j];
				CD[i*n+j] = C[i] + D[j];
			}
		}
		
		// 합 배열을 정렬
		Arrays.parallelSort(AB);
		Arrays.parallelSort(CD);
		
		// 합이 0이 되는 쌍의 개수 구하기
		long ans = 0;
		for (int i=0; i<n*n; i++) {
			int find = -AB[i];
			
			// lower bound 찾기
			int s = 0;
			int e = n*n-1;
			while (s < e) {
				int mid = (s + e) / 2;

				// 찾는 값보다 작은 경우
				if (CD[mid] < find) {
					s = mid + 1;
				}
				// 찾는 값보다 크거나 같은 경우
				else {
					e = mid;
				}
			}
			int lowerBound = s;
			
			// upper bound 찾기
			s = 0;
			e = n*n-1;
			while (s < e) {
				int mid = (s + e) / 2;
				
				// 찾는 값보다 큰 경우
				if (CD[mid] > find) {
					e = mid;
				}
				// 찾는 값보다 작거나 같은 경우
				else {
					s = mid + 1;
				}
			}
			int upperBound = s;
			
			ans += upperBound - lowerBound;
		}
		
		// 답 출력
		System.out.println(ans);
	}
}
