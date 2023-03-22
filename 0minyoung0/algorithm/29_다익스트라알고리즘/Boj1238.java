import java.io.*;
import java.util.*;

public class Boj1238 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		final int INF = 100000000;
		
		// 마을(학생)의 수
		int n = Integer.parseInt(st.nextToken());
		// 도로의 개수
		int m = Integer.parseInt(st.nextToken());
		// 파티가 열리는 마을의 번호
		int x = Integer.parseInt(st.nextToken());
		
		// 도로 정보
		// road1은 원래 도로 정보
		// road2는 뒤집힌 도로 정보
		List<List<int[]>> road1 = new ArrayList<>();
		List<List<int[]>> road2 = new ArrayList<>();
		for (int i=0; i<=n; i++) {
			road1.add(new ArrayList<>());
			road2.add(new ArrayList<>());
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			road1.get(s).add(new int[] {t, e});
			road2.get(e).add(new int[] {t, s});
		}
		
		// 최단 거리를 표현할 배열 선언
		int[] ans1 = new int[n+1];
		int[] ans2 = new int[n+1];
		Arrays.fill(ans1, INF);
		Arrays.fill(ans2, INF);
		ans1[x] = 0;
		ans2[x] = 0;
		
		// 다익스트라를 위한 우선순위 큐 (거리, 정점)
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[0]!=o2[0]) return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		
		// 우선순위 큐에 시작 정점 넣기
		pq.add(new int[] {0, x});
		
		// 다익스트라 알고리즘
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 하나 빼오기
			int[] cur = pq.poll();
			
			// 최단거리를 갱신한 간선 정보가 아니라면 continue
			if (cur[0] != ans1[cur[1]]) continue;
			
			// 갱신한 정점과 연결된 간선 확인
			for (int[] r : road1.get(cur[1])) {
				// 최단거리를 갱신할 수 없다면 continue
				if (cur[0] + r[0] >= ans1[r[1]]) continue;
				
				// 우선순위 큐에 넣어주기
				pq.offer(new int[] {cur[0] + r[0], r[1]});
				
				// 최단거리 갱신
				ans1[r[1]] = cur[0] + r[0];
			}
		}
		
		// 우선순위 큐에 시작 정점 넣기
		pq.add(new int[] {0, x});
		
		// 다익스트라 알고리즘
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 하나 빼오기
			int[] cur = pq.poll();
			
			// 최단거리를 갱신한 간선 정보가 아니라면 continue
			if (cur[0] != ans2[cur[1]]) continue;
			
			// 갱신한 정점과 연결된 간선 확인
			for (int[] r : road2.get(cur[1])) {
				// 최단거리를 갱신할 수 없다면 continue
				if (cur[0] + r[0] >= ans2[r[1]]) continue;
				
				// 우선순위 큐에 넣어주기
				pq.offer(new int[] {cur[0] + r[0], r[1]});
				
				// 최단거리 갱신
				ans2[r[1]] = cur[0] + r[0];
			}
		}
		
		// 답 출력
		int ans = 0;
		for (int i=1; i<=n; i++) {
			if (ans1[i] + ans2[i] > ans) ans = ans1[i] + ans2[i];
		}
		System.out.println(ans);
	}
}
