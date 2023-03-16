import java.io.*;
import java.util.*;

public class Boj2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 나무의 수
		int n = Integer.parseInt(st.nextToken());
		// 필요한 나무의 길이
		int m = Integer.parseInt(st.nextToken());
		
		// 나무 높이 정보
		int[] tree = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		long s = 0;
		long e = 1000000000;
		while (s < e) {
			// 탐색해볼 자르는 높이
			long mid = (s + e + 1) / 2;
			// 얻을 수 있는 나무 길이
			long temp = 0;
			for (int i=0; i<n; i++) {
				temp += tree[i]>mid? tree[i]-mid : 0;
			}
			// 나무가 모자른 경우
			if (temp < m) e = mid - 1;
			// 나무가 모자르지 않으면
			else s = mid;
		}
		
		// 답 출력
		System.out.println(s);
	}
}
