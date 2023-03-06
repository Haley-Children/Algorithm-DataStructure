import java.io.*;
import java.util.*;

public class Boj17471 {
	static int ans, n;
	static int[] pop;
	static boolean[][] adjacent;
	static boolean[] A;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 구역의 개수
        n = Integer.parseInt(br.readLine());
        
        // 구역별 인구 정보
        pop = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
        	pop[i] = Integer.parseInt(st.nextToken());
        }
        
        // 구역별 인접 정보
        adjacent = new boolean[n+1][n+1];
        for (int i=1; i<=n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int ad = Integer.parseInt(st.nextToken());
        	for (int idx=0; idx<ad; idx++) {
        		adjacent[i][Integer.parseInt(st.nextToken())] = true;
        	}
        }
        
        // 각 구역이 A선거구에 포함되는지 저장할 boolean 배열
        A = new boolean[n+1];
        // 1번 구역은 그냥 A 선거구에 넣어버림
        A[1] = true;
        
        // ans를 Integer.MAX_VALUE로 초기화
        ans = Integer.MAX_VALUE;
        
        // 시뮬레이션
        simulation(1);
        
        // ans가 초기값이면 -1로 변경
        if (ans == Integer.MAX_VALUE) {
        	ans = -1;
        }
        
        // 답 출력
        System.out.println(ans);
    }
    
    private static void simulation(int k) {
    	// 선거구 다 뽑았다면
    	if (k==n) {
    		// A 선거구에 포함되는 구역의 수
    		int cntA = 0;
    		for (int i=1; i<=n; i++) {
    			if (A[i]) cntA++;
    		}
    		// A 선거구에 모든 구역이 몰려있으면 바로 리턴
    		if (cntA == n) return;
    		
    		// B 선거구에 포함되는 구역의 수
    		int cntB = n - cntA;
    		
    		// BFS를 위한 방문여부 check boolean 배열
    		boolean[] vis = new boolean[n+1];
    		
    		// A구역끼리 다 연결 되어있는지 확인
    		// 1번 지역부터 BFS 돌리기
    		Queue<Integer> q = new ArrayDeque<>();
    		q.offer(1);
    		vis[1] = true;
    		cntA--;
    		while (!q.isEmpty()) {
    			int cur = q.poll();
    			for (int i=1; i<=n; i++) {
    				// 자기 자신일 때
    				if (i==cur) continue;
    				// 방문 한 경우
    				if (vis[i]) continue;
    				// 인접하지 않은 구역일 때
    				if (!adjacent[cur][i]) continue;
    				// A 선거구에 포함되지 않는 경우
    				if (!A[i]) continue;
    				// A 선거구에 포함되는 경우 큐에 넣고 카운트 감소
    				q.offer(i);
    				vis[i] = true;
    				cntA--;
    			}
    		}
    		// A구역끼리 다 연결되지 않았다면 리턴
    		if (cntA != 0) return;
    		
    		// B구역끼리 다 연결 되어있는지 확인
    		// B구역중 가장 작은 숫자의 지역 하나 찾기
    		int bStart = -1;
    		for (int i=1; i<=n; i++) {
    			if (!A[i]) {
    				bStart = i;
    				break;
    			}
    		}
    		// bStart 지역부터 BFS 돌리기
    		q.offer(bStart);
    		vis[bStart] = true;
    		cntB--;
    		while (!q.isEmpty()) {
    			int cur = q.poll();
    			for (int i=1; i<=n; i++) {
    				// 자기 자신일 때
    				if (i==cur) continue;
    				// 방문 한 경우
    				if (vis[i]) continue;
    				// 인접하지 않은 구역일 때
    				if (!adjacent[cur][i]) continue;
    				// B 선거구에 포함되지 않는 경우
    				if (A[i]) continue;
    				// B 선거구에 포함되는 경우 큐에 넣고 카운트 감소
    				q.offer(i);
    				vis[i] = true;
    				cntB--;
    			}
    		}
    		// B구역끼리 다 연결되지 않았다면 리턴
    		if (cntB != 0) return;
    		
    		// 연결 잘 되어있으면 선거구 인구 차이 계산 후 리턴
    		int temp = 0;
    		for (int i=1; i<=n; i++) {
    			if (A[i]) temp += pop[i];
    			else temp -= pop[i];
    		}
    		temp = temp > 0? temp : -temp;
    		ans = temp < ans? temp : ans;
    		return;
    	}
    	
    	// k+1번 지역의 선거구 뽑기
    	// A 선거구에 넣어보기
    	A[k+1] = true;
    	simulation(k+1);
    	// B 선거구에 넣어보기
    	A[k+1] = false;
    	simulation(k+1);
    }
}