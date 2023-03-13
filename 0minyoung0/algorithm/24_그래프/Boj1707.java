import java.io.*;
import java.util.*;

public class Boj1707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 테스트 케이스의 개수
        int k = Integer.parseInt(br.readLine());
        
        // 테스트 케이스별로 실행
        testCase: for (int tc=1; tc<=k; tc++) {
        	st = new StringTokenizer(br.readLine());
        	// 정점의 개수
        	int v = Integer.parseInt(st.nextToken());
        	// 간선의 개수
        	int e = Integer.parseInt(st.nextToken());
        	
        	// 그래프를 인접 리스트로 구현
        	ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        	for (int i=0; i<=v; i++) {
        		graph.add(new ArrayList<Integer>());
        	}
        	
        	// 인접 리스트에 간선 정보 입력
        	for (int i=0; i<e; i++) {
        		st = new StringTokenizer(br.readLine());
        		int v1 = Integer.parseInt(st.nextToken());
        		int v2 = Integer.parseInt(st.nextToken());
        		graph.get(v1).add(v2);
        		graph.get(v2).add(v1);
        	}
        	
        	// BFS 돌면서 거리에 따라 이웃한 정점에 다른 color를 번갈아 저장
        	int[] color = new int[v+1];
        	Queue<Integer> q = new ArrayDeque<>();
        	for (int i=1; i<=v; i++) {
        		// 이미 색칠된 정점인 경우
        		if (color[i] != 0) continue;
        		// 해당 정점을 색칠하고 BFS 돌리기
	    		color[i] = 1;
	    		q.offer(i);
	    		while(!q.isEmpty()) {
	    			int cur = q.poll();
	    			for (int nx : graph.get(cur)) {
	    				// 이미 색칠된 정점이면
	    				if (color[nx] > 0) {
	    					// 다른 색인지 확인
	    					if (color[cur] != color[nx]) continue;
	    					// 같은 색이면 "NO"
	    					System.out.println("NO");
	    					continue testCase;
	    				}
	    				// cur과 다른 색으로 색칠
	    				color[nx] = 3 - color[cur];
	    				q.offer(nx);
	    			}
	    		}
        	}
        	
    		// 위에서 NO를 출력하지 않았으면 문제가 없는거니까 YES 출력
    		System.out.println("YES");
        }
    }
}