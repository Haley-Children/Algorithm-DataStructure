import java.io.*;
import java.util.*;

public class Boj2473 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 전체 용액의 수
		int n = Integer.parseInt(br.readLine());
		
		// 용액의 특성 값
		long[] arr = new long[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		// 이중 포문으로 용액 두개 고르고 이분 탐색
		long sol = Long.MAX_VALUE;
		int[] ans = {-1, -1, -1};
		for (int i=0; i<n-2; i++) {
			for (int j=i+1; j<n-1; j++) {
				long target = -arr[i]-arr[j];
				int find = Arrays.binarySearch(arr, j+1, n, target);
				
				// 찾았으면 바로 답 출력 후 리턴
				if (find >= 0) {
					System.out.println(arr[i] + " " + arr[j] + " " + arr[find]);
					return;
				}
				
				// 없으면 주변 인덱스에서 최솟값 찾아보기
				find = -find-1;
				if (find-1 > j && Math.abs(arr[i]+arr[j]+arr[find-1]) < sol) {
					sol = Math.abs(arr[i]+arr[j]+arr[find-1]);
					ans[0] = i;
					ans[1] = j;
					ans[2] = find-1;
				}
				if (find != n && Math.abs(arr[i]+arr[j]+arr[find]) < sol) {
					sol = Math.abs(arr[i]+arr[j]+arr[find]);
					ans[0] = i;
					ans[1] = j;
					ans[2] = find;
				}
			}
		}
		
		// 답 출력
		for (int i=0; i<3; i++) {
			System.out.print(arr[ans[i]] + " ");
		}
	}
}
