import java.io.*;
import java.util.*;

public class Boj4803 {
	static int[][] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int case_num=1; ; case_num++) {
        	st = new StringTokenizer(br.readLine());
        	// 정점의 개수
        	int n = Integer.parseInt(st.nextToken());
        	// 간선의 개수
        	int m = Integer.parseInt(st.nextToken());
        	
        	// 종료 조건
        	if (n == 0) break;
        	
        	// 그래프를 인접 리스트로 선언
        	ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        	for (int i=0; i<=n; i++) {
        		graph.add(new ArrayList<Integer>());
        	}
        	
        	// 간선 정보
        	for (int i=0; i<m; i++) {
        		st = new StringTokenizer(br.readLine());
        		int n1 = Integer.parseInt(st.nextToken());
        		int n2 = Integer.parseInt(st.nextToken());
        		graph.get(n1).add(n2);
        		graph.get(n2).add(n1);
        	}
        	
        	// 각 노드의 부모 노드를 나타낼 배열
        	int[] p = new int[n+1];
        	
        	// 방문 여부 확인할 배열
        	boolean[] vis = new boolean[n+1];
        	
        	// 트리의 개수를 셀 변수
        	int ans = 0;
        	
        	// 방문한적 없는 노드에 대해서 BFS할때 방문한 적 있는 노드에 방문하지 않아야 트리임
        	for (int i=1; i<=n; i++) {
        		if (!vis[i]) {
        			boolean isTree = true;
        			vis[i] = true;
        			Queue<Integer> q = new ArrayDeque<>();
        			q.offer(i);
        			while(!q.isEmpty()) {
        				int cur = q.poll();
        				for (int x : graph.get(cur)) {
        					// 부모노드는 무시
        					if (x == p[cur]) continue;
        					// 방문한 적 있는 노드면 트리가 아님
        					if (vis[x]) {
        						isTree = false;
        						continue;
        					}
        					// 부모노드 표시, 방문 여부 체크, 큐에 넣기
        					p[x] = cur;
        					vis[x] = true;
        					q.offer(x);
        				}
        			}
        			if (isTree) {
        				ans++;
        			}
        		}
        	}
        	
        	// ans의 값에 따라서 답을 스트링 빌더에 append
        	sb.append("Case ").append(case_num).append(": ");
        	if (ans == 0) {
        		sb.append("No trees.\n");
        	}else if (ans == 1) {
        		sb.append("There is one tree.\n");
        	}else {
        		sb.append("A forest of ").append(ans).append(" trees.\n");
        	}
        }
        
        System.out.println(sb);
    }
}