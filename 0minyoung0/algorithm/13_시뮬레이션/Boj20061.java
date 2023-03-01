import java.io.*;
import java.util.*;

public class Boj20061 {
	static int ans1; // 점수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 보드판
    	int[][] blue = new int[4][6];
    	int[][] green = new int[4][6];
        
        // 블록을 놓은 횟수
        int n = Integer.parseInt(br.readLine());
        
        // 블록을 놓은 정보
        for (int i=1; i<=n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int t = Integer.parseInt(st.nextToken());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	// 파란색 보드 시뮬레이션
        	simulation(blue, i, t, x);
        	
        	// 초록색 보드 시뮬레이션
        	if (t != 1) t = 5 - t;
        	simulation(green, i, t, y);
        }
        
        // 파란색 보드와 초록색 보드에 타일이 들어있는 칸의 개수
        int ans2 = 0;
        for (int i=0; i<4; i++) {
        	for (int j=0; j<4; j++) {
        		if (blue[i][j] != 0) {
        			ans2++;
        		}
        		if (green[i][j] != 0) {
        			ans2++;
        		}
        	}
        }
        
        // 답 출력
        System.out.println(ans1);
        System.out.println(ans2);
    }
    
    // 모노미노도미노 시뮬레이션
    private static void simulation(int[][] board, int turn, int t, int x) {
    	// 새 블럭 놓기
    	board[x][4] = turn;
    	if (t==2) {
    		board[x][5] = turn;
    	}else if (t==3) {
    		board[x+1][4] = turn;
    	}
    	
		// 아래칸이 비어있는 경우 블럭 내리기
		if (board[x][3] == 0) {
			// 오른쪽에 같은 블럭이 연결되어있는 경우
			if (t == 3) {
				// 오른쪽 블럭도 아래칸이 비어있다면
				if (board[x+1][3] == 0) {
					// 블럭 내리기
					int cur = 4;
					while (cur != 0 && board[x][cur-1] == 0 && board[x+1][cur-1] == 0) {
						cur--;
					}
					board[x][cur] = turn;
					board[x+1][cur] = turn;
					board[x][4] = 0;
					board[x+1][4] = 0;
				}
			}
			// 그외의 경우
			else {
				// 블럭 내리기
				int cur = 4;
				while (cur != 0 && board[x][cur-1] == 0) {
					cur--;
				}
				board[x][cur] = turn;
				board[x][4] = 0;
				// 위쪽에 같은 블럭이 연결되어있는 경우
				if (t==2) {
					board[x][cur+1] = turn;
					board[x][5] = 0;
				}
			}
		}
    	
    	// 반복문 실행
    	loop: while (true) {
    		// 점수 받는 줄 있는지 체크
    		int scoreLine = -1;
    		line: for (int h=0; h<4; h++) {
    			for (int i=0; i<4; i++) {
    				if (board[i][h] == 0) continue line;
    			}
    			for (int i=0; i<4; i++) {
    				board[i][h] = 0;
    			}
    			scoreLine = h;
    			// 있으면 줄 정리하고 점수 받고 continue
    			if (scoreLine != -1) {
    				for (int j=scoreLine+1; j<6; j++) {
    					for (int i=0; i<4; i++) {
    						board[i][j-1] = board[i][j];
    						board[i][j] = 0;
    					}
    				}
    				ans1++;
    				continue loop;
    			}
    		}
    		
    		// 삐져 나온 줄 있는지 체크
    		int check = 2;
    		line: for (int h=4; h<6; h++) {
    			for (int i=0; i<4; i++) {
    				if (board[i][h] != 0) continue line;
    			}
    			check--;
    		}
    		// 있으면 그만큼 줄 내리고 continue
    		if (check != 0) {
    			for (int h=check; h<6; h++) {
    				for (int i=0; i<4; i++) {
    					board[i][h-check] = board[i][h];
    					board[i][h] = 0;
    				}
    			}
    			continue;
    		}
    		
    		return;
    	}
    }
}