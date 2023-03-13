import java.io.*;
import java.util.*;

public class Boj1260 {
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수
        int n = Integer.parseInt(st.nextToken());
        // 간선의 개수
        int m = Integer.parseInt(st.nextToken());
        // 탐색을 시작할 정점의 번호
        int v = Integer.parseInt(st.nextToken());
        
        // 그래프를 인접 리스트로 구현
        graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
        	graph.add(new ArrayList<Integer>());
        }
        
        // 간선 저장
        for (int i=0; i<m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int v1 = Integer.parseInt(st.nextToken());
        	int v2 = Integer.parseInt(st.nextToken());
        	graph.get(v1).add(v2);
        	graph.get(v2).add(v1);
        }
        
        // 간선 정렬
        for (int i=1; i<=n; i++) {
        	Collections.sort(graph.get(i));
        }
        
        // DFS
        vis = new boolean[n+1];
        DFS(v);
		System.out.println();
        
        // BFS
        vis = new boolean[n+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(v);
		vis[v] = true;
		System.out.print(v + " ");
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int nx : graph.get(cur)) {
				if (vis[nx]) continue;
				q.offer(nx);
				vis[nx] = true;
				System.out.print(nx + " ");
			}
		}
    }
    // 재귀로 DFS 돌기
    private static void DFS(int cur) {
		vis[cur] = true;
		System.out.print(cur + " ");
		for (int nx : graph.get(cur)) {
			if (vis[nx]) continue;
			DFS(nx);
		}
    }
}