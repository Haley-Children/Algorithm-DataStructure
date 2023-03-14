package topologicalsort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2623 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 총 가수들의 수
		int n = Integer.parseInt(st.nextToken());
		// 출연 순서 목록의 개수
		int m = Integer.parseInt(st.nextToken());
		
		// 방향 그래프
		boolean[][] adj = new boolean[n+1][n+1];
		// indegree 목록
		int[] indeg = new int[n+1];
		
		// 그래프 만들기
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int len = Integer.parseInt(st.nextToken());
			
			int prev = Integer.parseInt(st.nextToken());
			for(int j = 1; j < len; j++) {
				int next = Integer.parseInt(st.nextToken());
				
				adj[prev][next] = true;
				prev = next;
			}
		}
		
		// indeg 갱신
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(adj[i][j]) {
					indeg[j]++;
				}
			}
		}
		
		// indeg가 0인 정점들 큐에 담으면서 시작
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= n; i++) {
			if(indeg[i] == 0) {
				q.offer(i);
			}
		}
		
		// 위상 정렬한 정점의 수
		// n보다 작으면 실패한 것(사이클 존재)
		int count = 0;
		StringBuilder sb = new StringBuilder();
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append("\n");
			count++;
			
			for(int next = 1; next <= n; next++) {
				// 간선이 없으면 스킵
				if(!adj[cur][next]) continue;
				
				// cur를 방문해서 next를 시작할 수 있으면 (indeg가 0이면) 큐에 담기
				if(--indeg[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		if(count == n) {
			System.out.println(sb);
		} else {
			System.out.println(0);
		}
		
	}
}

