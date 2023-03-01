import java.io.*;
import java.util.*;

public class Boj17825 {
	static int[] input; // 주사위에서 나올 수
	static int[] arr = new int[10]; // 말 뽑힌 순서
	static int ans;
	static int[] mapPoint;
	static int[][] subMapPoint;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 인풋 받기
        input = new int[10];
        for (int i=0; i<10; i++) {
        	input[i] = Integer.parseInt(st.nextToken());
        }
        
        // 칸에 적혀있는 점수
        mapPoint = new int[64];
        for (int i=1; i<=20; i++) {
        	mapPoint[i] = 2 * i;
        }
        mapPoint[31] = 13;
        mapPoint[32] = 16;
        mapPoint[33] = 19;
        mapPoint[41] = 22;
        mapPoint[42] = 24;
        mapPoint[51] = 28;
        mapPoint[52] = 27;
        mapPoint[53] = 26;
        mapPoint[61] = 25;
        mapPoint[62] = 30;
        mapPoint[63] = 35;
        
        // 백트래킹으로 모든 경우에 대해서 시뮬레이션
        func(0);
        
        // 답 출력
        System.out.println(ans);
    }
    
    // 백트래킹으로 모든 경우에 대해서 시뮬레이션 하는 메소드
    private static void func (int k) {
    	// 10개 다 골랐으면 시뮬레이션 후 리턴
    	if (k == 10) {
    		// 각 말의 위치 정보
    		// 초기위치:0, 메인맵:1~20, 서브맵:31~33, 41~42, 51~53, 61~63
    		int[] pos = new int[4];
    		
    		// 점수를 저장할 변수
    		int score = 0;
    		
    		// 10턴동안 게임 진행
    		for (int t=0; t<10; t++) {
    			// 현재 움직일 말과 현재 주사위 수
    			int curToken = arr[t];
    			int curDice = input[t];
    			
    			// 도착한 말을 골랐다면 불가능이므로 리턴
    			if (pos[curToken] == 99) return;
    			
    			// 다음 이동할 칸을 저장할 변수
    			int nx = pos[curToken] + curDice;
    			
    			// 파란색 칸 위에서 출발하는지 확인
    			if (pos[curToken] == 5) {
    				nx = 30 + curDice;

    			}else if (pos[curToken] == 10) {
    				nx = 40 + curDice;

    			}else if (pos[curToken] == 15) {
    				nx = 50 + curDice;
    			}
    			
    			// 이동할 칸이 서브맵 (중간 지름길)위에 있는지 확인
    			if (33 < nx && nx < 40) nx += 27;
    			else if (42 < nx && nx < 50) nx += 18;
    			else if (53 < nx && nx < 60) nx += 7;
    			if (nx == 64) nx = 20;
    			
    			// 도착이면 도착 위치로 옮기고 continue
    			if ((20 < nx && nx < 30) || (63 < nx && nx < 70)) {
    				pos[curToken] = 99;
    				continue;
    			}
    			
    			// 이동하려는 공간에 다른 말이 있으면 불가능이므로 리턴
    			for (int i=0; i<4; i++) {
    				if (i != curToken && pos[i] == nx) return;
    			}
    			
    			// 이동할 수 있는 상황이라면 이동하고 점수를 합산
    			pos[curToken] = nx;
    			score += mapPoint[nx];
    		}
    		
    		// 점수 계산 후 리턴
    		ans = ans > score? ans : score;
    		return;
    	}
    	
    	// 다음 말 고르기
    	for (int i=0; i<4; i++) {
    		arr[k] = i;
    		func(k+1);
    	}
    }
}