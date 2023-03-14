import java.io.*;
import java.util.*;

public class Boj2252 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 학생의 수
		int n = Integer.parseInt(st.nextToken());
		// 키를 비교한 횟수
		int m = Integer.parseInt(st.nextToken());
		
		// 그래프를 인접 리스트로 표현
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i=0; i<=n; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 키를 비교한 정보
		int[] indegree = new int[n+1];
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			indegree[b]++;
		}
		
		// 큐에 indegree가 0인 학생 모두 넣기
		Queue<Integer> q = new ArrayDeque<>();
		for (int i=1; i<=n; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		// 큐가 빌때까지 BFS
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");
			for (int x : graph.get(cur)) {
				if(--indegree[x] == 0) {
					q.offer(x);
				}
			}
		}
		
		// 답 출력
		System.out.println(sb);
	}
}
