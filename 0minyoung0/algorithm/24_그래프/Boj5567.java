import java.io.*;
import java.util.*;

public class Boj5567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 상근이의 동기의 수
        int n = Integer.parseInt(br.readLine());
        // 친구 관계 리스트의 길이
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
        
        // 상근이부터 BFS하며 친구와 친구의 친구 숫자 세기
        int ans = 0;
        int[] dis = new int[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        dis[1] = 1;
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	// 만약 친구의 친구라면 더이상 탐색하지 않음
        	if (dis[cur] == 3) break;
        	// 아직 탐색하지 않은 친구를 찾아서 큐에 추가
        	for (int nx : graph.get(cur)) {
        		if (dis[nx] > 0) continue;
        		q.offer(nx);
        		dis[nx] = dis[cur] + 1;
        		ans++;
        	}
        }
        
        // 답 출력
        System.out.println(ans);
    }
}