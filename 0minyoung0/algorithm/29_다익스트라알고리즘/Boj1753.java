import java.io.*;
import java.util.*;

public class Boj1753 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		final int INF = 100000000;
		
		// 정점의 개수
		int v = Integer.parseInt(st.nextToken());
		// 간선의 개수
		int e = Integer.parseInt(st.nextToken());
		
		// 시작 정점의 번호
		int k = Integer.parseInt(br.readLine());
		
		// 간선 정보
		List<List<int[]>> edge = new ArrayList<>();
		for (int i=0; i<=v; i++) {
			edge.add(new ArrayList<>());
		}
		for (int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int uu = Integer.parseInt(st.nextToken());
			int vv = Integer.parseInt(st.nextToken());
			int ww = Integer.parseInt(st.nextToken());
			edge.get(uu).add(new int[] {ww, vv});
		}
		
		// 최단 거리를 표현할 배열 선언
		int[] ans = new int[v+1];
		Arrays.fill(ans, INF);
		ans[k] = 0;
		
		// 다익스트라를 위한 우선순위 큐 (거리, 정점)
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[0]!=o2[0]) return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		
		// 우선순위 큐에 시작 정점 넣기
		pq.add(new int[] {0, k});
		
		// 다익스트라 알고리즘
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 하나 빼오기
			int[] cur = pq.poll();
			
			// 최단거리를 갱신한 간선 정보가 아니라면 continue
			if (cur[0] != ans[cur[1]]) continue;
			
			// 갱신한 정점과 연결된 간선 확인
			for (int[] ee : edge.get(cur[1])) {
				// 최단거리를 갱신할 수 없다면 continue
				if (cur[0] + ee[0] >= ans[ee[1]]) continue;
				
				// 우선순위 큐에 넣어주기
				pq.offer(new int[] {cur[0] + ee[0], ee[1]});
				
				// 최단거리 갱신
				ans[ee[1]] = cur[0] + ee[0];
			}
		}
		
		// 답 출력
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=v; i++) {
			if (ans[i] == INF) {
				sb.append("INF\n");
			}else {
				sb.append(ans[i]).append("\n");
			}
		}
		System.out.println(sb);
	}
}
