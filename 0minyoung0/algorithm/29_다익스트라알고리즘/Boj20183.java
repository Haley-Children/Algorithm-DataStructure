import java.io.*;
import java.util.*;

public class Boj20183 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		final long INF = 10000000000000000L;
		
		// 교차로 개수
		int N = Integer.parseInt(st.nextToken());
		// 골목 개수
		int M = Integer.parseInt(st.nextToken());
		// 시작 교차로 번호
		int A = Integer.parseInt(st.nextToken());
		// 도착 교차로 번호
		int B = Integer.parseInt(st.nextToken());
		// 가진 돈
		long C = Long.parseLong(st.nextToken());
		
		// 골목 정보
		List<int[]>[] route = new List[N+1];
		for (int i=1; i<=N; i++) {
			route[i] = new ArrayList<>();
		}
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			route[n1].add(new int[] {cost, n2});
			route[n2].add(new int[] {cost, n1});
		}
		
		// 다익스트라를 위한 우선순위 큐
		PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
			public int compare(long[] o1, long[] o2) {
				if (o1[0] < o2[0]) return -1;
				else if (o1[0] > o2[0]) return 1;
				return (int)(o1[1] - o2[1]);
			}
		});
		
		// 한 골목에서 내야하는 요금의 최솟값 찾기
		int s = 1;
		int e = 1000000001;
		while (s < e) {
			
			// 비용이 mid 이하인 골목만 다니기
			int mid = (s + e) / 2;
			
			// 최단 비용을 저장할 배열
			long[] cost = new long[N+1];
			Arrays.fill(cost, INF);
			cost[A] = 0;
			
			// 시작점을 힙에 넣기
			pq.add(new long[] {0, A});
			
			// 다익스트라
			while (!pq.isEmpty()) {
				long[] cur = pq.poll();
				// 최단 비용을 갱신한 정보와 다르면 continue
				if (cur[0] != cost[(int)cur[1]]) continue;
				
				// 연결된 간선
				for (int[] nx : route[(int)cur[1]]) {
					// mid 초과인 간선은 continue
					if (nx[0] > mid) continue;
					
					// 갱신 할 수 없으면 continue
					if (cur[0] + nx[0] >= cost[(int)nx[1]]) continue;
					
					// 갱신하기
					cost[(int)nx[1]] = cur[0] + nx[0];
					
					// 힙에 넣기
					pq.add(new long[] {cost[(int)nx[1]], nx[1]});
					
				}
			}
			
			// mid 이하인 골목만 다녔을때 도착할 수 없다면
			if (cost[B] > C) s = mid + 1;
			// mid 이하인 골목만 다녔을때 도착할 수 있다면
			else e = mid;
		}
		
		// 갈 수 없는 경우
		if (s == 1000000001) s = -1;
		
		// 답 출력
		System.out.println(s);
		
	}
}
