import java.io.*;
import java.util.*;

public class Boj2467 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 전체 용액의 수
		int n = Integer.parseInt(br.readLine());
		
		// 용액의 특성 값
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 각 용액에 대해서 순회하면서 합계가 0이 되는 값을 삽입할 수 있는 인덱스 찾기
		int sol = Integer.MAX_VALUE;
		int ans1 = 0;
		int ans2 = 0;
		for (int i=0; i<n; i++) {
			int target = -arr[i];
			int s = i + 1;
			int e = n;
			while (s < e) {
				// 탐색할 인덱스
				int mid = (s + e) / 2;
				// 찾는 값인 경우
				if (arr[mid] == target) {
					// 답 출력 후 리턴
					System.out.println(-target + " " + target);
					return;
				}
				// 찾는 값보다 작은 경우
				else if (arr[mid] < target) {
					s = mid + 1;
				}
				// 찾는 값보다 큰 경우
				else {
					e = mid;
				}
			}
			
			// s-1번째 인덱스에 대해서 최소값 체크
			if (i != s - 1 && sol > Math.abs(arr[i] + arr[s - 1])) {
				sol = Math.abs(arr[i] + arr[s - 1]);
				ans1 = arr[i];
				ans2 = arr[s-1];
			}
			
			// s번째 인덱스에 대해서 최소값 체크
			if (s == n) continue;
			if (i != s && sol > Math.abs(arr[i] + arr[s])) {
				sol = Math.abs(arr[i] + arr[s]);
				ans1 = arr[i];
				ans2 = arr[s];
			}
		}
		
		// 답 출력
		System.out.println(ans1 + " " + ans2);
	}
}
