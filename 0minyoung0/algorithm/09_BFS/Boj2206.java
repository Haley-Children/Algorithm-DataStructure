// bfs.boj2206;

import java.io.*;
import java.util.*;

public class Boj2206 {
	static int n,m,ans;
	static boolean[][] isWall;
	static int[][][] dis;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	isWall = new boolean[n][m];
    	// dis 배열에 시작점, 도착점에서부터 탐색하며 방문한 칸이나 touch한 벽까지의 거리를 표시한다
    	dis = new int[n][m][2];
    	ans = Integer.MAX_VALUE;
    	
    	for (int i=0; i<n; i++) {
    		String s = br.readLine();
    		for (int j=0; j<m; j++) {
    			if (s.charAt(j) == '1') isWall[i][j] = true;
    		}
    	}
    	
    	Queue<int[]> q = new LinkedList<>();
    	// 시작점에서부터의 bfs
    	q.add(new int[] {0,0});
    	dis[0][0][0] = 1;
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int curX = cur[0];
    		int curY = cur[1];
    		for (int dir=0; dir<4; dir++) {
    			int nx = curX + dx[dir];
    			int ny = curY + dy[dir];
    			// 인덱스 벗어나는 경우
    			if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
    			// 방문한칸 or touch한벽
    			if (dis[nx][ny][0] != 0) continue;
    			// 벽을 처음 만났다면 서있는 dis를 벽에 touch!
    			if (isWall[nx][ny]) {
    				dis[nx][ny][0] = dis[curX][curY][0];
    				continue;
    			}
    			// 방문하지 않은 빈 칸인 경우
    			q.add(new int[] {nx,ny});
    			dis[nx][ny][0] = dis[curX][curY][0] + 1;
    		}
    	}
    	// 도착점에 도달했다면 ans를 시작점부터 도착점까지의 거리로 대체
    	if (dis[n-1][m-1][0] != 0) ans = dis[n-1][m-1][0];
    	
    	// 도착점에서부터의 bfs
    	q.add(new int[] {n-1,m-1});
    	dis[n-1][m-1][1] = 1;
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int curX = cur[0];
    		int curY = cur[1];
    		for (int dir=0; dir<4; dir++) {
    			int nx = curX + dx[dir];
    			int ny = curY + dy[dir];
    			// 인덱스 벗어나는 경우
    			if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
    			// 방문한칸 or touch한벽
    			if (dis[nx][ny][1] != 0) continue;
    			// 벽을 처음 만났다면 서있는 dis를 벽에 touch!
    			if (isWall[nx][ny]) {
    				dis[nx][ny][1] = dis[curX][curY][1];
    				continue;
    			}
    			// 방문하지 않은 빈 칸인 경우
    			q.add(new int[] {nx,ny});
    			dis[nx][ny][1] = dis[curX][curY][1] + 1;
    		}
    	}
    	
    	// 전체 영역을 돌면서 양쪽 모두의 touch가 찍힌 벽 중 거리의 합이 가장 작은 벽을 찾아서 비교한다
    	for (int i=0; i<n; i++) {
    		for (int j=0; j<m; j++) {
    			if (dis[i][j][0] != 0 && dis[i][j][1] != 0 && dis[i][j][0] + dis[i][j][1] + 1 < ans) {
    				ans = dis[i][j][0] + dis[i][j][1] + 1;
    			}
    		}
    	}
    	
    	// ans에 변화가 없으면 불가능이므로 -1로 바꾼다
    	if (ans == Integer.MAX_VALUE) ans = -1;
    	// 정답 출력
    	System.out.println(ans);
    }
}