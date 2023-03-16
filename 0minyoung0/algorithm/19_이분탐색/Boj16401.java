import java.io.*;
import java.util.*;

public class Boj16401 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 조카의 수
		int m = Integer.parseInt(st.nextToken());
		// 과자의 수
		int n = Integer.parseInt(st.nextToken());
		
		// 과자 길이 정보
		int[] snack = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			snack[i] = Integer.parseInt(st.nextToken());
		}
		
		// 매개변수 탐색
		int s = 0;
		int e = 1000000000;
		while (s < e) {
			// 탐색해볼 과자의 길이
			int mid = (s + e + 1) / 2;
			// 줄 수 있는 과자 개수
			int cnt = 0;
			for (int i=0; i<n; i++) {
				cnt += snack[i] / mid;
			}
			// 과자가 부족하면
			if (cnt < m) e = mid - 1;
			// 과자가 부족하지 않으면
			else s = mid;
		}
		
		// 답 출력
		System.out.println(s);
	}
}
