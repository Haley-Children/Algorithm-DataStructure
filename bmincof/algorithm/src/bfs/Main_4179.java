package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4179 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		char[][] maze = new char[n][m];
		int[] dx = new int[] {1,-1,0,0};
		int[] dy = new int[] {0,0,1,-1};
		
		int[][] fire = new int[n][m];
		int[][] jihun = new int[n][m];
		
		Queue<Point> f = new LinkedList<>();
		Queue<Point> q = new LinkedList<>();
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < m; j++) {
				maze[i][j] = line.charAt(j);
				if(maze[i][j] == 'F') {
					fire[i][j] = 1;
					f.add(new Point(i,j));
				}
				else if(maze[i][j] == 'J') {
					jihun[i][j] = 1;
					q.add(new Point(i,j));
				}
			}
		}
		br.close();
		
		while(!f.isEmpty()) {
			Point cur = f.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if(maze[nx][ny] == '#' || fire[nx][ny] > 0) continue;
				
				fire[nx][ny] = fire[cur.x][cur.y] + 1;
				f.add(new Point(nx,ny));
			}
		}
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
					System.out.println(jihun[cur.x][cur.y]);
					return;
				}
				if(maze[nx][ny] == '#' || jihun[nx][ny] > 0) continue;
				if(jihun[cur.x][cur.y] + 1 >= fire[nx][ny] && fire[nx][ny] != 0) continue;
				jihun[nx][ny] = jihun[cur.x][cur.y] + 1;
				q.add(new Point(nx,ny));
			}
		}
		
		System.out.println("IMPOSSIBLE");
		
	}
	
	static class Point {
		int x; int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
}

