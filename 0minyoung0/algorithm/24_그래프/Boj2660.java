import java.io.*;
import java.util.*;

public class Boj2660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 회원의 수
        int n = Integer.parseInt(br.readLine());
        
        // 그래프를 인접리스트로 구현
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) {
        	graph.add(new ArrayList<Integer>());
        }
        
        // 친구 관계 입력
        while (true) {
        	st = new StringTokenizer(br.readLine());
        	int n1 = Integer.parseInt(st.nextToken());
        	int n2 = Integer.parseInt(st.nextToken());
        	
        	// 입력 단계 종료조건
        	if (n1 == -1) break;
        	
        	// 인접리스트에 친구관계 입력
        	graph.get(n1).add(n2);
        	graph.get(n2).add(n1);
        }
        
        // 회장 후보의 점수를 저장할 변수
        int score = n;
        
        // 회장 후보를 저장할 리스트
        List<Integer> list = new ArrayList<>();
        
        // BFS로 회장 후보 찾기
        for (int i=1; i<=n; i++) {
        	int curScore = 1;
        	Queue<Integer> q = new ArrayDeque<>();
        	int[] dis = new int[n+1];
        	q.add(i);
        	dis[i] = 1;
        	while(!q.isEmpty()) {
        		int cur = q.poll();
        		for (int nx : graph.get(cur)) {
        			if (dis[nx] > 0) continue;
        			q.add(nx);
        			dis[nx] = dis[cur] + 1;
        			curScore = dis[cur];
        		}
        	}
        	
        	// 기존 회장 후보 점수보다 점수가 작거나 같을 때
        	if (curScore <= score) {
	        	// 기존 회장 후보 점수보다 점수가 작은 경우
	        	if (curScore < score) {
	        		// 점수 갱신
	        		score = curScore;
	        		// 리스트 클리어
	        		list.clear();
	        	}
	        	
	        	// 리스트에 회장 후보 추가
	        	list.add(i);
        	}
        }
        
        // 회장 후보의 점수와 후보의 수 출력
        System.out.println(score + " " + list.size());
        // 회장 후보를 오름차순으로 모두 출력
        for (int c : list) {
        	System.out.print(c + " ");
        }
    }
}