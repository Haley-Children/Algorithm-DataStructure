import java.io.*;
import java.util.*;

public class Boj14267 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 회사의 직원 수
		int n = Integer.parseInt(st.nextToken());
		// 최초의 칭찬의 횟수
		int m = Integer.parseInt(st.nextToken());
		
		// 트리를 인접 리스트로 표현
		ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
		for (int i=0; i<=n; i++) {
			tree.add(new ArrayList<>());
		}
		
		// 부모노드를 저장할 배열
		int[] p = new int[n+1];
		
		// 인접 관계와 부모노드 저장
		st = new StringTokenizer(br.readLine());
		Integer.parseInt(st.nextToken());
		for (int i=2; i<=n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
			tree.get(p[i]).add(i);
		}
		
		// 각 직원이 받은 칭찬의 수치를 저장할 배열
		int[] ans = new int[n+1];
		
		// 각 직원이 직속 상사에게 받은 칭찬의 수치
		for (int j=0; j<m; j++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			ans[i] += w;
		}
		
		// 사장부터 BFS 돌면서 부하직원에게 내리칭찬하기
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int x : tree.get(cur)) {
				ans[x] += ans[cur];
				q.offer(x);
			}
		}
		
		// 답 출력
		for (int i=1; i<=n; i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb);
	}
}
