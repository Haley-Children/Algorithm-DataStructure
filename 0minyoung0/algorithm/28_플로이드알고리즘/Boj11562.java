import java.io.*;
import java.util.*;

public class Boj11562 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		final int INF = 100000;
		
		// 건물의 수
		int n = Integer.parseInt(st.nextToken());
		// 길의 수
		int m = Integer.parseInt(st.nextToken());
		
		// 길에 대한 정보
		int[][] road = new int[n+1][n+1];
		for (int i=1; i<=n; i++) {
			Arrays.fill(road[i], INF);
			road[i][i] = 0;
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			road[u][v] = 0;
			// 일방통행인 경우 (비용이 1인 길을 가는것과 같음)
			if (b == 0) road[v][u] = 1;
			// 양방향통행인 경우
			else road[v][u] = 0;
		}
		
		// 플로이드 알고리즘
		for (int i=1; i<=n; i++) {
			for (int s=1; s<=n; s++) {
				if (i == s) continue;
				for (int e=1; e<=n; e++) {
					if (i == e) continue;
					if (road[s][e] > road[s][i] + road[i][e]) {
						road[s][e] = road[s][i] + road[i][e];
					}
				}
			}
		}
		
		// 학생들의 질문의 수
		int k = Integer.parseInt(br.readLine());
		
		// 질문에 대한 답 저장
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			sb.append(road[s][e]).append("\n");
		}
		
		// 답 출력
		System.out.println(sb);
	}
}
