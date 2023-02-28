import java.io.*;
import java.util.*;

public class Boj16986 {
	static int n, k;
	static int[][] A;
	static int[][] data;
	static boolean[] used;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 손동작의 종류 수
        n = Integer.parseInt(st.nextToken());
        // 우승을 위해 필요한 승 수
        k = Integer.parseInt(st.nextToken());
        
        // 상성에 대한 정보 (1-indexed)
        A = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=1; j<=n; j++) {
        		A[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 지우, 경희, 민호가 경기에 참가할 때 내는 손동작 정보
        data = new int[3][20];
        // 경희, 민호 정보만 배열에 미리 받음
        for (int i=1; i<=2; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=0; j<20; j++) {
        		data[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 지우가 사용한 손동작을 나타내기 위한 int 배열, boolean 배열
        used = new boolean[n+1];
        
        // 결과 출력
        if (possible(0)) {
        	System.out.println(1);
        }else {
        	System.out.println(0);
        }
    }
    
    // 백트래킹으로 지우가 모든 손동작을 다르게 내어서 우승할 수 있는지 반환하는 메소드
    private static boolean possible(int level) {
    	// n개의 손동작을 모두 골랐다면 게임 진행 후 결과 리턴
    	if (level==n) {
    		// 각 친구가 이긴 횟수
    		int[] win = new int[3];
    		// 지우, 경희, 민호가 낼 손동작 인덱스
    		int[] cur = new int[3];
    		// 전경기 승자와 도전자의 번호
    		int preWinner = 0;
    		int challenger = 1;
    		
    		// 게임 진행 (종료조건 : 누군가가 우승하거나, 지우가 손동작을 n번 낸 경우)
    		while (win[0] < k && win[1] < k && win[2] < k && cur[0] < n) {
    			// 이겼던 애가 또 이기는 경우
    			if (A[data[preWinner][cur[preWinner]]][data[challenger][cur[challenger]]] == 2
    			|| (data[preWinner][cur[preWinner]] == data[challenger][cur[challenger]] 
    														&& preWinner > challenger)) {
    				cur[preWinner]++;
    				cur[challenger]++;
    				challenger = 3 - preWinner - challenger;
    			}
    			// 도전자가 이기는 경우
    			else { 
    				cur[preWinner]++;
    				cur[challenger]++;
    				challenger = 3 - preWinner - challenger;
    				preWinner = 3 - preWinner - challenger;
    			}
    			// 승자의 win 카운트 증가
    			win[preWinner]++;
    		}
    		
    		// 지우가 이겼다면 true 리턴
    		if (win[0] == k) {
    			return true;
    		}
    		// 그 외에는 false 리턴
    		return false;
    	}
    	
    	// level번째 손동작 정하기
    	for (int i=1; i<=n; i++) {
    		// i 손동작이 아직 사용되지 않았다면
    		if (!used[i]) {
    			// 사용 정보 남기기
    			data[0][level] = i;
    			used[i] = true;
    			
    			// 다음 재귀 호출해서 true면 true 리턴
    			if (possible(level+1)) return true;
    			
    			// 사용 정보 없애기
    			used[i] = false;
    		}
    	}
    	
    	// true를 리턴하지 못했다면 false 리턴
    	return false;
    }
}