import java.io.*;
import java.util.*;

public class Boj2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 컴퓨터의 수
        int n = Integer.parseInt(br.readLine());
        // 간선의 개수
        int m = Integer.parseInt(br.readLine());
        
        // 그래프를 인접리스트로 구현
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
        	graph.add(new ArrayList<Integer>());
        }
        
        // 간선 입력
        for (int i=0; i<m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int n1 = Integer.parseInt(st.nextToken());
        	int n2 = Integer.parseInt(st.nextToken());
        	graph.get(n1).add(n2);
        	graph.get(n2).add(n1);
        }
        
        // 1번 컴퓨터에서 BFS
        int ans = 0;
        boolean[] vis = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        vis[1] = true;
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	for (int nx : graph.get(cur)) {
        		if (vis[nx]) continue;
        		q.offer(nx);
        		vis[nx] = true;
        		ans++;
        	}
        }
        
        // 답 출력
        System.out.println(ans);
    }
}