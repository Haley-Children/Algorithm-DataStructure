package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2573 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int time = 0;
		
		int[][] glacier = new int[n][m];
		
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};

		Queue<Point> q = new LinkedList<>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				glacier[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			// 빙산 개수 확인
			boolean[][] wVis = new boolean[n][m];
			int sector = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {	
					if(glacier[i][j] > 0 && !wVis[i][j]) {
						wVis[i][j] = true;
						q.add(new Point(i, j));
						
						while(!q.isEmpty()) {
							Point cur = q.poll();
							
							for(int dir = 0; dir < 4; dir++) {
								int nx = cur.x + dx[dir];
								int ny = cur.y + dy[dir];
								
								if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
								if(glacier[nx][ny] == 0 || wVis[nx][ny]) continue;
								
								wVis[nx][ny] = true;
								q.add(new Point(nx, ny));
							}	
						} // end of while
						sector++;
					}
				}
			} // end of for
			
			if(sector > 1) {
				break;
			} else if(sector == 0) {
				time = 0;
				break;
			}
			
			// 빙하 녹이기 시작
			boolean[][] vis = new boolean[n][m];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					
					if(glacier[i][j] > 0 && !vis[i][j]) {
						vis[i][j] = true;
						q.add(new Point(i, j));
						
						while(!q.isEmpty()) {
							Point cur = q.poll();
							
							for(int dir = 0; dir < 4; dir++) {
								int nx = cur.x + dx[dir];
								int ny = cur.y + dy[dir];
								
								if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
								if(vis[nx][ny]) continue;
								if(glacier[nx][ny] == 0 && glacier[cur.x][cur.y] > 0) {
									if(--glacier[cur.x][cur.y] == 0) {
										break;
									} else {
										continue;
									}
								}
								
								vis[nx][ny] = true;
								if(glacier[nx][ny] != 0) q.add(new Point(nx, ny));
							}
						} // end of while
					}
				}
			} // end of for
			
			time++;
			
		}
		
		System.out.println(time);
		
	}
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
}

