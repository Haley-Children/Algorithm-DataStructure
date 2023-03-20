import java.io.*;
import java.util.*;

public class Boj15681 {
	static ArrayList<ArrayList<Integer>> tree;
	static int[] ans, p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

    	// 정점의 개수
    	int n = Integer.parseInt(st.nextToken());
    	// 루트의 번호
    	int r = Integer.parseInt(st.nextToken());
        // 쿼리의 수
    	int q = Integer.parseInt(st.nextToken());
    	
    	// 트리를 인접 리스트로 표현
    	tree = new ArrayList<>();
    	for (int i=0; i<=n; i++) {
    		tree.add(new ArrayList<>());
    	}
    	
    	// 트리에 간선 정보 넣기
    	for (int i=0; i<n-1; i++) {
    		st = new StringTokenizer(br.readLine());
    		int n1 = Integer.parseInt(st.nextToken());
    		int n2 = Integer.parseInt(st.nextToken());
    		tree.get(n1).add(n2);
    		tree.get(n2).add(n1);
    	}
    	
    	// 부모 노드의 값을 저장할 배열
    	p = new int[n+1];
    	
    	// 루트부터 BFS 돌면서 부모 노드 저장
    	Queue<Integer> queue = new ArrayDeque<>();
    	queue.offer(r);
    	while(!queue.isEmpty()) {
    		int cur = queue.poll();
    		for (int x : tree.get(cur)) {
    			if (x == p[cur]) continue;
    			p[x] = cur;
    			queue.offer(x);
    		}
    	}
    	
    	// 루트부터 DFS 돌면서 각 정점에서의 서브트리 계산
    	ans = new int[n+1];
    	DFS(r);

        // 쿼리의 답을 sb에 append
    	for (int i=0; i<q; i++) {
    		int u = Integer.parseInt(br.readLine());
    		sb.append(ans[u]).append('\n');
    	}
    	
        System.out.println(sb);
    }
    
    private static int DFS (int cur) {
    	int temp = 1;
    	for (int x : tree.get(cur)) {
    		if (x == p[cur]) continue;
    		temp += DFS(x);
    	}
    	ans[cur] = temp;
    	return temp;
    }
}