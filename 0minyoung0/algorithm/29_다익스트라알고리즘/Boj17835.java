import java.io.*;
import java.util.*;

public class Boj17835 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		final long INF = 1000000000000L;
		
		// 도시의 수
		int n = Integer.parseInt(st.nextToken());
		// 도로의 수
		int m = Integer.parseInt(st.nextToken());
		// 면접장의 수
		int k = Integer.parseInt(st.nextToken());
		
		// 도로 정보를 인접 리스트로 저장
		List<long[]>[] lists = new ArrayList[n+1];
		for (int i=1; i<=n; i++) {
			lists[i] = new ArrayList<>();
		}
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			lists[v].add(new long[] {(long)c, (long)u});
		}
		
		// 다익스트라를 위한 우선순위큐 (거리, 정점)
		PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
			public int compare(long[] o1, long[] o2) {
				if (o1[0] != o2[0]) {
					if (o1[0] < o2[0]) return -1;
					else return 1;
				}
				return (int)(o1[1] - o2[1]);
			}
		});
		
		// 최단 거리를 표현할 배열
		long[] ans = new long[n+1];
		Arrays.fill(ans, INF);
		
		// 면접장이 배치된 도시를 거리 0으로 하여 우선순위큐에 저장하고 최단거리 0으로 저장
		st = new StringTokenizer(br.readLine());
		while (k-- > 0) {
			int x = Integer.parseInt(st.nextToken());
			pq.add(new long[] {0, x});
			ans[x] = 0;
		}
		
		// 다익스트라 알고리즘
		while (!pq.isEmpty()) {
			
			// 우선 순위 큐에서 하나 뽑아오기
			long[] cur = pq.poll();
			
			// 뽑아온게 최단거리를 갱신한 정보가 아니면 continue
			if (cur[0] != ans[(int)cur[1]]) continue;
			
			// cur에 해당하는 정점과 연결된 간선들 확인하기
			for (long[] nx : lists[(int)cur[1]]) {
				
				// 최단거리 갱신이 안된다면 continue
				if (cur[0] + nx[0] >= ans[(int)nx[1]]) continue;
				
				// 최단거리 갱신
				ans[(int)nx[1]] = cur[0] + nx[0];
				
				// 우선순위 큐에 넣기
				pq.add(new long[] {ans[(int)nx[1]], nx[1]});
			}
		}
		
		// 최단거리를 순회하며 가장 큰 인덱스 찾기
		int idx = 1;
		for (int i=2; i<=n; i++) {
			if (ans[i] > ans[idx]) idx = i;
		}
		
		// 거리가 가장 먼 도시의 번호와 면접장까지의 거리 출력
		System.out.println(idx);
		System.out.println(ans[idx]);
	}
}
