// bfs.boj2146;

import java.io.*;
import java.util.*;

public class Boj2146 {
    static int n,m;
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = Integer.MAX_VALUE;
        
        // 탐색된 대륙인지 확인하기 위한 배열
        boolean[][] isExplored = new boolean[n][n];
        
        // 순차적으로 좌표를 확인하며 1을 발견하면 그 좌표가 포함되는 대륙을 통째로 큐에 넣는다
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
            	
                // 일단 이중 포문으로 1 찾기 (탐색된 대륙이 아닌 경우만)
                if (map[i][j] == 1 && !isExplored[i][j]) {
                	
                    // 탐색을 위한 vis 배열
                    boolean[][] vis = new boolean[n][n];
                    int[][] dis = new int[n][n];
                    // q1을 이용해 bfs로 돌면서 q2에 대륙 모든 좌표 넣기
                    Queue<int[]> q1 = new LinkedList<>();
                    Queue<int[]> q2 = new LinkedList<>();
                    q1.add(new int[] {i,j});
                    vis[i][j] = true;
                    while(!q1.isEmpty()) {
                        int[] cur = q1.poll();
                        q2.add(cur);
                        int curX = cur[0];
                        int curY = cur[1];
                        // q2에 추가하며 해당 좌표의 dis를 1로 초기화
                        dis[curX][curY] = 1;
                        // 다음에 재사용하지 않도록 탐색된 대륙임을 남김
                        isExplored[curX][curY] = true;
                        for (int dir=0; dir<4; dir++) {
                            int nx = curX + dx[dir];
                            int ny = curY + dy[dir];
                            if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
                            if (vis[nx][ny] || map[nx][ny] == 0) continue;
                            q1.add(new int[] {nx,ny});
                            vis[nx][ny] = true;
                        }
                    }
                    // q2에 있는 모든 좌표에 대해 다시 bfs 시작 (물 건너기)
                    label: while(!q2.isEmpty()) {
                    	// 다른 대륙이 나올때까지 bfs를 돌려서 최소 거리 탐색
                    	int[] cur = q2.poll();
                    	int curX = cur[0];
                    	int curY = cur[1];
                    	for (int dir=0; dir<4; dir++) {
                    		int nx = curX + dx[dir];
                    		int ny = curY + dy[dir];
                    		if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
                    		if (vis[nx][ny]) continue;
                    		// 다른 대륙(1)을 찾으면 답을 저장하고 bfs 정지
                    		if (map[nx][ny] == 1) {
                    			if (ans > dis[curX][curY] - 1) ans = dis[curX][curY] - 1;
                    			break label;
                    		}
                    		q2.add(new int[] {nx,ny});
                    		vis[nx][ny] = true;
                    		dis[nx][ny] = dis[curX][curY] + 1;
                    	}
                    }
                }
            }
        }
        System.out.println(ans);
    }
}