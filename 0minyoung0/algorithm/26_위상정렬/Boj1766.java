import java.io.*;
import java.util.*;

public class Boj1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 문제의 수
        int n = Integer.parseInt(st.nextToken());
        // 정보의 개수
        int m = Integer.parseInt(st.nextToken());
        
        // 먼저 푸는 것이 좋은 문제 정보를 담을 그래프
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
        	graph.add(new ArrayList<>());
        }
        
        // indegree 배열
        int[] indegree = new int[n+1];
        
        // 정보를 그래프와 indegree 배열에 담기
        for (int i=0; i<m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	graph.get(a).add(b);
        	indegree[b]++;
        }
        
        // 풀어야 할 문제를 뽑아낼 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // indegree가 0인 문제는 우선순위 큐에 넣기
        for (int i=1; i<=n; i++) {
        	if (indegree[i] == 0) {
        		pq.add(i);
        	}
        }
        
        // 우선순위 큐가 빌때까지 문제 풀기
        while(!pq.isEmpty()) {
        	int cur = pq.poll();
        	sb.append(cur).append(" ");
        	for (int nx : graph.get(cur)) {
        		if (--indegree[nx] == 0) {
        			pq.add(nx);
        		}
        	}
        }
        
        // 답 출력
        System.out.println(sb);
    }
}