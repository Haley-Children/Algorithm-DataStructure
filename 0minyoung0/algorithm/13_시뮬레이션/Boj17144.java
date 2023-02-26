import java.io.*;
import java.util.*;

public class Boj17144 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 방의 크기
    	int r = Integer.parseInt(st.nextToken());
    	int c = Integer.parseInt(st.nextToken());
    	// 시간
    	int T = Integer.parseInt(st.nextToken());
    	
    	// 공기청정기 좌표
    	int A = -1;
    	
    	// 방정보
    	int[][] map = new int[r][c];
    	for (int i=0; i<r; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j=0; j<c; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			// 공기청정기 좌표 (아래쪽)
    			if (map[i][j] == -1) {
    				A = i;
    			}
    		}
    	}
    	
    	// 미세먼지 확산을 위한 델타 배열
    	int[] dx = {-1,1,0,0};
    	int[] dy = {0,0,-1,1};
    	
    	// T초동안 시뮬레이션 진행
    	for (int t=0; t<T; t++) {
    		// 변화한 상황을 저장할 배열
    		int[][] newMap = new int[r][c];
    		// 공기청정기 좌표 입력
    		newMap[A-1][0] = -1;
    		newMap[A][0] = -1;
    		
    		// 미세먼지 확산
    		for (int i=0; i<r; i++) {
    			for (int j=0; j<c; j++) {
    				// 미세먼지가 있으면 확산 시키기
    				if (map[i][j] > 0) {
    					int cnt = 0;
    					for (int dir=0; dir<4; dir++) {
    						// 주변 좌표
    						int nx = i + dx[dir];
    						int ny = j + dy[dir];
    						
    						// out of index
    						if (nx<0 || nx>=r || ny<0 || ny>=c) continue;
    						// 공기청정기가 있는 경우
    						if (map[nx][ny] == -1) continue;
    						// 확산이 일어나는 경우
    						cnt++;
    						newMap[nx][ny] += map[i][j] / 5;
    					}
    					newMap[i][j] += map[i][j] - map[i][j] / 5 * cnt;
    				}
    			}
    		}
    		
    		// 공기청정기 가동
    		// 공기청정기 좌표는 (A-1,0)과 (A,0)
    		// 공기청정기 위에부터 가동
    		int x1 = A-2;
    		int y1 = 0;
    		int x2 = A-1;
    		int y2 = 0;
    		// 0,0까지
    		while (x1 != 0) {
    			x2 = x1--;
    			newMap[x2][y2] = newMap[x1][y1];
    		}
    		x2 = 0;
    		y1 = 1;
    		newMap[x2][y2] = newMap[x1][y1];
    		// 0,c-1까지
    		while (y1 != c-1) {
    			y2 = y1++;
    			newMap[x2][y2] = newMap[x1][y1];
    		}
    		y2 = c-1;
    		x1 = 1;
    		newMap[x2][y2] = newMap[x1][y1];
    		// A-1,c-1까지
    		while (x1 != A-1) {
    			x2 = x1++;
    			newMap[x2][y2] = newMap[x1][y1];
    		}
    		x2 = A-1;
    		y1 = c-2;
    		newMap[x2][y2] = newMap[x1][y1];
    		// A-1,1까지
    		while (y1 != 1) {
    			y2 = y1--;
    			newMap[x2][y2] = newMap[x1][y1];
    		}
    		newMap[A-1][1] = 0;
    		
    		// 공기청정기 아래꺼 가동
    		x1 = A+1;
    		y1 = 0;
    		x2 = A;
    		y2 = 0;
    		// r-1,0까지
    		while (x1 != r-1) {
    			x2 = x1++;
    			newMap[x2][y2] = newMap[x1][y1];
    		}
    		x2 = r-1;
    		y1 = 1;
    		newMap[x2][y2] = newMap[x1][y1];
    		// r-1,c-1까지
    		while (y1 != c-1) {
    			y2 = y1++;
    			newMap[x2][y2] = newMap[x1][y1];
    		}
    		y2 = c-1;
    		x1 = r-2;
    		newMap[x2][y2] = newMap[x1][y1];
    		// A,c-1까지
    		while (x1 != A) {
    			x2 = x1--;
    			newMap[x2][y2] = newMap[x1][y1];
    		}
    		x2 = A;
    		y1 = c-2;
    		newMap[x2][y2] = newMap[x1][y1];
    		// A,1까지
    		while (y1 != 1) {
    			y2 = y1--;
    			newMap[x2][y2] = newMap[x1][y1];
    		}
    		newMap[A][1] = 0;
    		
    		// map에 newMap을 할당
    		map = newMap;
    	}

    	// 방에 남아있는 미세먼지의 양 출력
    	int ans = 2;
    	for (int i=0; i<r; i++) {
    		for (int j=0; j<c; j++) {
    			ans += map[i][j];
    		}
    	}
    	System.out.println(ans);
    }
}