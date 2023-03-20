import java.io.*;
import java.util.*;

public class Boj2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 집의 개수
		int n = Integer.parseInt(st.nextToken());
		// 공유기의 개수
		int c = Integer.parseInt(st.nextToken());

		// 집의 좌표
		int[] house = new int[n];
		for (int i=0; i<n; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		Arrays.parallelSort(house);
		
		// 이분탐색으로 적절한 최소거리 찾기
		int s = 1;
		int e = 1000000000;
		while (s < e) {
			// 탐색해볼 최소거리
			int mid = (s + e + 1) / 2;
			
			// 순회하면서 공유기 개수 세기
			int temp = 1;
			int pre = 0;
			for (int i=1; i<n; i++) {
				if (house[i] - house[pre] >= mid) {
					temp++;
					pre = i;
				}
			}
			
			// 공유기를 c개 놓을 수 없는 경우
			if (temp < c) {
				// 더 짧은 최소 거리로 설정
				e = mid - 1;
			}
			
			// 공유기를 c개 놓을 수 있는 경우
			else {
				// 같거나 더 긴 최소 거리로 설정
				s = mid;
			}
		}
		
		// 답 출력
		System.out.println(s);
	}
}
