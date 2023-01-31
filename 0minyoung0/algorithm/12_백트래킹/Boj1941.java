// 백트래킹.boj1941;

import java.io.*;
import java.util.*;

public class Boj1941 {
	static char[][] team = new char[5][5];
	static boolean[][] isPicked = new boolean[5][5];
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
	static int ans;
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	for (int i=0; i<5; i++) {
    		String s = br.readLine();
    		for (int j=0; j<5; j++) {
    			team[i][j] = s.charAt(j);
    		}
    	}
    	func(0, -1);
    	System.out.println(ans);
    }
    private static void func(int k, int pre) {
    	// 7명을 모두 뽑았다면
    	if (k == 7) {
    		// S팀이 4명 이상인지 확인 -> 아니면 리턴
    		int s_count = 0;
    		for (int i=0; i<5; i++) {
    			for (int j=0; j<5; j++) {
    				if (isPicked[i][j] && team[i][j] == 'S') {
    					s_count++;
    				}
    			}
    		}
    		if (s_count < 4) return;
    		
    		// bfs로 자리가 모두 붙어있는지 확인 -> 아니면 리턴
    		Queue<int[]> q = new LinkedList<>();
    		boolean[][] vis = new boolean[5][5];
    		int cnt = 1;
    		// 큐에 처음 넣을 사람 찾기
    		label: for (int i=0; i<5; i++) {
    			for (int j=0; j<5; j++) {
    				if (isPicked[i][j]) {
    					q.add(new int[] {i,j});
    					vis[i][j] = true;
    					break label;
    				}
    			}
    		}
    		// bfs 돌려서 7명이 붙었는지 확인
    		while(!q.isEmpty()) {
    			int[] cur = q.poll();
    			int curX = cur[0];
    			int curY = cur[1];
    			for (int dir=0; dir<4; dir++) {
    				int nx = curX + dx[dir];
    				int ny = curY + dy[dir];
    				if (nx<0 || nx>= 5 || ny<0 || ny>=5) continue;
    				if (vis[nx][ny] || !isPicked[nx][ny]) continue;
    				q.add(new int[] {nx,ny});
    				vis[nx][ny] = true;
    				cnt++;
    			}
    		}
    		if (cnt != 7) return;
    		
    		// 위에서 리턴 안했으면 조건을 만족하므로 ans++후 리턴
    		ans++;
    		return;
    	}
    	// 다음 사람 뽑기
    	for (int i=pre+1; i<19+k; i++) {
    		if (!isPicked[i/5][i%5]) {
    			isPicked[i/5][i%5] = true;
    			func(k+1, i);
    			isPicked[i/5][i%5] = false;
    		}
    	}
    }
}