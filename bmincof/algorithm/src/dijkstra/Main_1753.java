package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1753 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		final int INF = v * 10 + 1;

		// 시작 정점 번호
		int start = Integer.parseInt(br.readLine());
		// 시작점으로부터 [i]까지 최단 거리
		int[] dist = new int[v+1];
		Arrays.fill(dist, INF);
		
		
		// 해당 정점에서 뻗어나가는 간선들
		List<int[]>[] edges = new ArrayList[v+1];
		
		for(int i = 0; i <= v; i++) {
			edges[i] = new ArrayList<>();
		}
		
		// 간선 저장
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges[from].add(new int[] {w, to});
		}
		
		// 더 가중치가 낮은 간선부터 뽑는 우선순위 큐
		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(e1, e2) -> e1[0] - e2[0]);
		// 시작점에 연결된 간선 모두 연결
		dist[start] = 0;
		pq.offer(new int[] {0, start});
		
		// 간선 꺼내기
		// 최소 거리가 확정된 곳은 건너뛰기
		while(!pq.isEmpty()) {
			int[] curEdge = pq.poll();
			
			int cur = curEdge[1];
			int weight = curEdge[0];
			if(dist[cur] < weight) continue;
			
			// 이번에 최단거리를 확정한 정점에 연결된 간선들을 큐에 추가
			// 추가할 때 이번에 결정한 최단 거리만큼을 간선 가중치에 추가
			for(int[] edge : edges[cur]) {
				int newWeight = weight + edge[0];
				if(dist[edge[1]] <= newWeight) continue;
				
				// 최단거리 갱신
				dist[edge[1]] = newWeight;
				pq.offer(new int[] {newWeight, edge[1]});
			}
		}
		
		for(int i = 1; i <= v; i++) {
			sb.append(dist[i] == INF ? "INF" : dist[i]).append("\n");
		}
		
		System.out.println(sb);
	}
}

