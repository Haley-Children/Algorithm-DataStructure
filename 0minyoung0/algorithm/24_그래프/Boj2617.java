import java.io.*;
import java.util.*;

public class Boj2617 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 구슬의 개수
        int n = Integer.parseInt(st.nextToken());
        // 저울에 올려 본 쌍의 개수
        int m = Integer.parseInt(st.nextToken());
        
        // 그래프를 인접배열로 표현
        int[][] graph = new int[n+1][n+1];
        
        // 저울에 올려본 정보를 그래프에 입력
        // i의 무게가 j의 무게보다 무거우면 graph[i][j] = 1
        // i의 무게가 j의 무게보다 가벼우면 graph[i][j] = -1
        for (int i=0; i<m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int n1 = Integer.parseInt(st.nextToken());
        	int n2 = Integer.parseInt(st.nextToken());
        	graph[n1][n2] = 1;
        	graph[n2][n1] = -1;
        }
        
        // 중간이 될 수 없는 구슬 개수를 셀 변수 선언
        int ans = 0;
        
        for (int i=1; i<=n; i++) {
        	// 해당 구슬보다 가벼운 구슬이 n/2보다 많으면 불가능
        	int cnt = 0;
        	boolean[] vis = new boolean[n+1];
        	Queue<Integer> q = new ArrayDeque<>();
        	vis[i] = true;
        	q.offer(i);
        	while(!q.isEmpty()) {
        		int cur = q.poll();
        		for (int j=1; j<=n; j++) {
        			if (graph[cur][j] != 1) continue;
        			if (vis[j]) continue;
        			cnt++;
        			vis[j] = true;
        			q.offer(j);
        		}
        	}
        	if (cnt > n/2) {
        		ans++;
        		continue;
        	}
        	
        	// 해당 구슬보다 무거운 구슬이 n/2보다 많으면 불가능
        	cnt = 0;
        	vis = new boolean[n+1];
        	vis[i] = true;
        	q.offer(i);
        	while(!q.isEmpty()) {
        		int cur = q.poll();
        		for (int j=1; j<=n; j++) {
        			if (graph[cur][j] != -1) continue;
        			if (vis[j]) continue;
        			cnt++;
        			vis[j] = true;
        			q.offer(j);
        		}
        	}
        	if (cnt > n/2) {
        		ans++;
        	}
        }
        
        // 중간이 될 수 없는 구슬 개수 출력
        System.out.println(ans);
    }
}