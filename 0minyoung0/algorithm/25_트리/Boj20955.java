import java.io.*;
import java.util.*;

public class Boj20955 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 뉴런의 개수
		int n = Integer.parseInt(st.nextToken());
		// 시냅스의 개수
		int m = Integer.parseInt(st.nextToken());
		
		// 그래프의 연결 관계를 인접 리스트로 나타내기
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i=0; i<=n; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 시냅스로 연결된 두 뉴런의 정보
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		// 뉴런을 연결하거나 끊은 횟수 *2를 저장할 변수
		int ans2 = -2;
		
		// BFS 시작할때 ans+=2, 사이클을 발견하면 ans++
		boolean[] vis = new boolean[n+1];
		int[] p = new int[n+1];
		for (int i=1; i<=n; i++) {
			if (!vis[i]) {
				ans2 += 2;
				vis[i] = true;
				Queue<Integer> q = new ArrayDeque<>();
				q.offer(i);
				while(!q.isEmpty()) {
					int cur = q.poll();
					for (int x : graph.get(cur)) {
						// 부모노드인 경우
						if (p[cur] == x) continue;
						// 부모노드가 아닌데 방문한 적이 있는 경우 : 사이클
						if (vis[x]) {
							ans2++;
							continue;
						}
						// 자식노드인 경우
						vis[x] = true;
						p[x] = cur;
						q.offer(x);
					}
				}
			}
		}
		
		// 답 출력
		System.out.println(ans2/2);
	}
}
