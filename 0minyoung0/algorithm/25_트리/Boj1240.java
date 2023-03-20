import java.io.*;
import java.util.*;

public class Boj1240 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 노드 개수
		int n = Integer.parseInt(st.nextToken());
		// 거리를 알고 싶은 노드 쌍 개수
		int m = Integer.parseInt(st.nextToken());
		
		// 트리를 인접 리스트로 표현
		ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
		for (int i=0; i<=n; i++) {
			tree.add(new ArrayList<>());
		}
		
		// 거리 정보를 저장할 배열
		int[][] ans = new int[n+1][n+1];
		
		// 트리상에 연결된 두 점과 거리 저장
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			tree.get(n1).add(n2);
			tree.get(n2).add(n1);
			ans[n1][n2] = d;
			ans[n2][n1] = d;
		}
		
		// 각 노드에서 BFS로 순회하며 ans 배열 채우기
		for (int i=1; i<=n; i++) {
			int[] p = new int[n+1];
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(i);
			while(!q.isEmpty()) {
				int cur = q.poll();
				for (int x : tree.get(cur)) {
					if (p[cur] == x) continue;
					if (ans[i][x] == 0) {
						ans[i][x] = ans[i][cur] + ans[cur][x];
					}
					p[x] = cur;
					q.offer(x);
				}
			}
		}
		
		// 거리를 알고 싶은 노드 쌍
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			sb.append(ans[n1][n2]).append('\n');
		}
		
		// 답 출력
		System.out.println(sb);
	}
}
