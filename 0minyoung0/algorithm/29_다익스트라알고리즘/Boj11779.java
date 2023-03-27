import java.io.*;
import java.util.*;

public class Boj11779 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int INF = 200000000;
		
		// 도시의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 버스의 개수
		int m = Integer.parseInt(br.readLine());
		
		// 버스 정보
		List<List<int[]>> bus = new ArrayList<>();
		for (int i=0; i<=n; i++) {
			bus.add(new ArrayList<>());
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			bus.get(a).add(new int[] {c, b});
		}
		
		// 출발점과 도착점의 도시 번호
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 최단 거리를 표현할 배열 선언
		int[] ans = new int[n+1];
		Arrays.fill(ans, INF);
		ans[start] = 0;
		
		// 경로 복원을 위한 pre 배열 선언
		int[] pre = new int[n+1];
		
		// 다익스트라를 위한 우선순위 큐 (비용, 정점)
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[0]!=o2[0]) return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		
		// 우선순위 큐에 시작 정점 넣기
		pq.add(new int[] {0, start});
		
		// 다익스트라 알고리즘
		while (!pq.isEmpty()) {
			// 우선순위 큐에서 하나 빼오기
			int[] cur = pq.poll();
			
			// 최단거리를 갱신한 정점 정보가 아니라면 continue
			if (cur[0] != ans[cur[1]]) continue;
			
			// 갱신한 정점에서 출발하는 버스 확인
			for (int[] b : bus.get(cur[1])) {
				// 최단 거리를 갱신할 수 없다면 continue
				if (cur[0] + b[0] >= ans[b[1]]) continue;

				// 우선순위 큐에 넣어주기
				pq.offer(new int[] {cur[0] + b[0], b[1]});
				
				// 최단 거리 갱신
				ans[b[1]] = cur[0] + b[0];
				
				// pre에 경로 남기기
				pre[b[1]] = cur[1];
			}
		}
		
		// 경로 복원
		Deque<Integer> route = new ArrayDeque<>();
		int cur = end;
		while (cur != 0) {
			route.offerFirst(cur);
			cur = pre[cur];
		}
		
		// 답 출력
		StringBuilder sb = new StringBuilder();
		// 최소 비용
		sb.append(ans[end]).append("\n");
		// 최소 비용 경로의 도시의 개수
		sb.append(route.size()).append("\n");
		// 최소 비용 경로의 도시
		while (!route.isEmpty()) {
			sb.append(route.poll()).append(" ");
		}
		System.out.println(sb);
	}
}
