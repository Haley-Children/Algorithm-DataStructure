// bfs.boj2573;

import java.io.*;
import java.util.*;

public class Boj2573 {
	static int n,m;
	static int[][] map;
    static int[][] ice;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
	public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	map = new int[n][m];
    	
    	int iceNum = 0;
    	for (int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j=0; j<m; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			if (map[i][j] != 0) iceNum++;
    		}
    	}
    	
    	int[][] ice = new int[iceNum][2]; // 빙산이 남아있는 좌표
    	int index = 0;
    	for (int i=0; i<n; i++) {
    		for (int j=0; j<m; j++) {
    			if (map[i][j] != 0) ice[index++] = new int[] {i,j};
    		}
    	}
    	
    	int year = 0;

    	// iceNum이 0이 될때까지 루프 or 빙산이 나눠지면 
    	label: do {
    		// 인접한 물 개수만큼 빙산 녹이기
        	// 빙산의 높이가 0되면 int iceNum도 감소. int index는 초기 iceNum의 값 유지
    		int[] temp = new int[index];
        	for (int i=0; i<index; i++) {
        		if (map[ice[i][0]][ice[i][1]] > 0) {
        			for (int dir = 0; dir < 4; dir++) {
        				if (map[ice[i][0]+dx[dir]][ice[i][1]+dy[dir]] == 0) temp[i]++;
        			}
        			if (map[ice[i][0]][ice[i][1]] < temp[i]) temp[i] = map[ice[i][0]][ice[i][1]];
        		}
        	}
        	for (int i=0; i<index; i++) {
        		if (map[ice[i][0]][ice[i][1]] > 0) {
        			map[ice[i][0]][ice[i][1]] -= temp[i];
        			if (map[ice[i][0]][ice[i][1]] == 0) iceNum--;
        		}
        	}
    		// year 증가
    		year++;
    		// bfs로 빙산이 한덩이인지 확인
    		for (int i=0; i<index; i++) {
    			if (map[ice[i][0]][ice[i][1]] > 0) {
    				boolean[][] vis = new boolean[n][m];
    				int sX = ice[i][0];
    				int sY = ice[i][1];
    				Queue<int[]> q = new LinkedList<>();
    				q.add(new int[] {sX, sY});
    				vis[sX][sY] = true;
    				int cnt = 1;
    				while(!q.isEmpty()) {
    					int[] cur = q.poll();
    					int curX = cur[0];
    					int curY = cur[1];
    					for (int dir=0; dir<4; dir++) {
    						int nx = curX + dx[dir];
    						int ny = curY + dy[dir];
    						if (vis[nx][ny]) continue;
    						if (map[nx][ny] == 0) continue;
    						q.add(new int[] {nx,ny});
    						vis[nx][ny] = true;
    						cnt++;
    					}
    				}
    				// 빙산이 한덩이가 아니라면 break
    				if (cnt != iceNum) break label;
    				break;
    			}
    		}
    	} while (iceNum != 0);
    	
    	// iceNum이 0이면 불가능이므로 year = 0
    	if (iceNum == 0) year = 0;
    	
    	System.out.println(year);
    }
}