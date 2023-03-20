package floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14938 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		final int INF = 1000000;
		
		// 지역의 개수
		int n = Integer.parseInt(st.nextToken());
		// 수색범위
		int m = Integer.parseInt(st.nextToken());
		// 길의 개수
		int r = Integer.parseInt(st.nextToken());
		
		int[] items = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		// [i]에서 [j]로의 최단 거리를 저장할 배열
		int[][] dist = new int[n+1][n+1];
		
		// 끊어진 곳은 INF로 표시
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == j) continue;
				dist[i][j] = INF;
			}
		}
		
		// r개의 길 정보 저장
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			dist[u][v] = dist[v][u] = w;
		}
		
		// dist를 최단 거리로 갱신
		for(int mid = 1; mid <= n; mid++) {
			for(int from = 1; from <= n; from++) {
				for(int to = 1; to <= n; to++) {
					dist[from][to] = Math.min(dist[from][to], dist[from][mid] + dist[mid][to]);
				}
			}
		}
		
		// i번 지점에 떨어졌을 때 얻을 수 있는 아이템의 수 중 최댓값을 저장
		int maxPick = 0;
		for(int i = 1; i <= n; i++) {
			int pick = 0;
			for(int j = 1; j <= n; j++) {
				// 탐색범위 안이면 아이템 개수 +
				if(dist[i][j] <= m) {
					pick += items[j];
				}
			}
			
			maxPick = Math.max(maxPick, pick);
		}
		
		System.out.println(maxPick);
	}
}

