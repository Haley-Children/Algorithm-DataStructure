import java.io.*;
import java.util.*;

public class Boj1504 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		final int INF = 100000000;
		
		// 정점의 개수
		int n = Integer.parseInt(st.nextToken());
		// 간선의 개수
		int e = Integer.parseInt(st.nextToken());
		
		// 간선 정보
		List<int[]>[] edges = new ArrayList[n+1];
		for (int i=1; i<=n; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(new int[] {c, b});
			edges[b].add(new int[] {c, a});
		}
		
		// 반드시 거쳐야하는 정점 번호
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		// 최소 거리를 저장할 배열
		int[] dis1 = new int[n+1];
		int[] dis2 = new int[n+1];
		Arrays.fill(dis1, INF);
		Arrays.fill(dis2, INF);
		dis1[v1] = 0;
		dis2[v2] = 0;
		
		// 다익스트라를 위한 우선순위 큐
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[0]!=o2[0]) return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		
		// v1을 시작점으로 하는 다익스트라
		pq.offer(new int[] {0, v1});
		while(!pq.isEmpty()) {
			// 우선순위 큐에서 하나 뽑아오기
			int[] cur = pq.poll();
			
			// 해당 정보가 최소 거리에 해당하지 않으면 continue
			if (cur[0] != dis1[cur[1]]) continue;
			
			// 해당 점과 연결된 간선들에 대해서 체크
			for (int[] edge : edges[cur[1]]) {
				// 해당 간선이 갱신 불가능 하다면 continue
				if (cur[0] + edge[0] >= dis1[edge[1]]) continue;
				
				// 최소 거리 갱신
				dis1[edge[1]] = cur[0] + edge[0];
				
				// 우선순위 큐에 넣기
				pq.offer(new int[] {dis1[edge[1]], edge[1]});
			}
		}
		
		// v2을 시작점으로 하는 다익스트라
		pq.offer(new int[] {0, v2});
		while(!pq.isEmpty()) {
			// 우선순위 큐에서 하나 뽑아오기
			int[] cur = pq.poll();
			
			// 해당 정보가 최소 거리에 해당하지 않으면 continue
			if (cur[0] != dis2[cur[1]]) continue;
			
			// 해당 점과 연결된 간선들에 대해서 체크
			for (int[] edge : edges[cur[1]]) {
				// 해당 간선이 갱신 불가능 하다면 continue
				if (cur[0] + edge[0] >= dis2[edge[1]]) continue;
				
				// 최소 거리 갱신
				dis2[edge[1]] = cur[0] + edge[0];
				
				// 우선순위 큐에 넣기
				pq.offer(new int[] {dis2[edge[1]], edge[1]});
			}
		}
		
		// 답 출력
		int ans = dis1[v2] + Math.min(dis1[1] + dis2[n], dis1[n]+dis2[1]);
		if (ans >= INF) ans = -1;
		System.out.println(ans);
	}
}
