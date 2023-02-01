import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5427 {
	
	static int n, m;
	static char[][] map;
	static int[][] fire, time;
	
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int testCase = 0; testCase < t; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			fire = new int[n][m];
			time = new int[n][m];
			
			map = new char[n][];
			for(int i = 0; i < n; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			bfs();
			escape();
		}
	}
	
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] == '*') {
					q.add(new Point(i, j));
					fire[i][j] = 1;
				}
			}	
		}
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if(map[nx][ny] == '#' || fire[nx][ny] > 0) continue;
				
				fire[nx][ny] = fire[cur.x][cur.y] + 1;
				q.add(new Point(nx, ny));
			}
		}
		
	}
	
	static void escape() {
		Queue<Point> q = new LinkedList<>();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] == '@') {
					q.add(new Point(i, j));
					time[i][j] = 1;
				}
			}	
		}
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
					System.out.println(time[cur.x][cur.y]);
					return;
				}
				if(map[nx][ny] == '#' || time[nx][ny] > 0) continue;
				if(fire[nx][ny] != 0 && fire[nx][ny] <= time[cur.x][cur.y] + 1) continue;
				
				time[nx][ny] = time[cur.x][cur.y] + 1;
				q.add(new Point(nx, ny));
			}
		}
		
		System.out.println("IMPOSSIBLE");
	}
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

