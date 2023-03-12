import java.io.*;
import java.util.*;

public class Boj11724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수
        int n = Integer.parseInt(st.nextToken());
        // 간선의 개수
        int m = Integer.parseInt(st.nextToken());
        
        // 그래프를 인접 리스트로 구현
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
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
        
        // BFS로 연결 요소 개수 세기
        int ans = 0;
        boolean[] vis = new boolean[n+1];
        for (int i=1; i<=n; i++) {
        	if (!vis[i]) {
        		ans++;
        		Queue<Integer> q = new ArrayDeque<>();
        		q.offer(i);
        		vis[i] = true;
        		while(!q.isEmpty()) {
        			int cur = q.poll();
        			for (int j=0; j<graph.get(cur).size(); j++) {
        				if (vis[graph.get(cur).get(j)]) continue;
        				q.offer(graph.get(cur).get(j));
        				vis[graph.get(cur).get(j)] = true;
        			}
        		}
        	}
        }
        
        // 답 출력
        System.out.println(ans);
    }
}