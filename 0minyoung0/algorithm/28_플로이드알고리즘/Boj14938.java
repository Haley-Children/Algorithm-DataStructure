import java.io.*;
import java.util.*;

public class Boj14938 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 지역의 개수
		int n = Integer.parseInt(st.nextToken());
		// 예은이의 수색 범위
		int m = Integer.parseInt(st.nextToken());
		// 길의 개수
		int r = Integer.parseInt(st.nextToken());
		
		// 각 구역에 있는 아이템의 수
		int[] item = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=n; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		// 길 정보
		int[][] road = new int[n+1][n+1];
		for (int i=1; i<=n; i++) {
			Arrays.fill(road[i], 1500);
			road[i][i] = 0;
		}
		for (int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			if (road[a][b] > l) {
				road[a][b] = l;
				road[b][a] = l;
			}
		}
		
		// 플로이드 알고리즘
		for (int i=1; i<=n; i++) {
			for (int s=1; s<=n; s++) {
				if (i == s) continue;
				for (int t=1; t<=n; t++) {
					if (i == t) continue;
					if (road[s][t] > road[s][i] + road[i][t]) {
						road[s][t] = road[s][i] + road[i][t];
					}
				}
			}
		}
		
		// 답 출력
		int ans = 0;
		for (int i=1; i<=n; i++) {
			int temp = 0;
			for (int j=1; j<=n; j++) {
				if (road[i][j] <= m) {
					temp += item[j];
				}
			}
			if (temp > ans) ans = temp;
		}
		System.out.println(ans);
	}
}
