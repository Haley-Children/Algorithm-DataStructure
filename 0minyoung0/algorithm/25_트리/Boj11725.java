import java.io.*;
import java.util.*;

public class Boj11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 노드의 개수
        int n = Integer.parseInt(br.readLine());
        
        // 그래프를 인접 리스트로 구현
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
        	graph.add(new ArrayList<Integer>());
        }
        
        // 정점 연결 정보 저장
        for (int i=0; i<n-1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int n1 = Integer.parseInt(st.nextToken());
        	int n2 = Integer.parseInt(st.nextToken());
        	graph.get(n1).add(n2);
        	graph.get(n2).add(n1);
        }
        
        // 부모노드를 저장할 배열
        int[] p = new int[n+1];
        
        // 1번 노드부터 BFS 돌면서 부모 노드 저장하기
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	for (int nx : graph.get(cur)) {
        		// cur의 부모 노드인 경우 continue
        		if (nx == p[cur]) continue;
        		// nx의 부모노드 정보를 cur로 저장
        		p[nx] = cur;
        		q.add(nx);
        	}
        }
        
        // 부모노드 정보 출력
        StringBuilder sb = new StringBuilder();
        for (int i=2; i<=n; i++) {
        	sb.append(p[i] + "\n");
        }
        System.out.println(sb);
    }
}