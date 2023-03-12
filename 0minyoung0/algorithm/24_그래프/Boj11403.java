import java.io.*;
import java.util.*;

public class Boj11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점의 개수
        int n = Integer.parseInt(br.readLine());
        
        // 그래프를 인접리스트로 구현
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
        	graph.add(new ArrayList<Integer>());
        }
        
        // 간선 입력
        for (int i=1; i<=n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=1; j<=n; j++) {
        		if(st.nextToken().equals("1")) {
        			graph.get(i).add(j);
        		}
        	}
        }
        
        // 1부터 n까지 각 좌표를 시작점으로 BFS하여 특정 좌표에 도달할 수 있는지 확인
        for (int i=1; i<=n; i++) {
        	// 자기 자신으로 돌아올 수 있는지 확인해야 하므로 vis를 시작점에대해 초기화 하지 않음
        	boolean[] vis = new boolean[n+1];
        	Queue<Integer> q = new ArrayDeque<>();
        	q.add(i);
        	
        	while(!q.isEmpty()) {
        		int cur = q.poll();
        		for (int nx : graph.get(cur)) {
        			if (vis[nx]) continue;
        			q.add(nx);
        			vis[nx] = true;
        		}
        	}
        	
        	// 방문여부를 통해 i행 출력
        	for (int j=1; j<=n; j++) {
        		if (vis[j]) {
        			System.out.print("1 ");
        		}else {
        			System.out.print("0 ");
        		}
        	}System.out.println();
        }
        
    }
}