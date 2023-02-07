import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		char[][] map = new char[n][];
		
		for(int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int[][] can = new int[n][n];
		int[][] cant = new int[n][n];
		
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		Queue<Point> q = new LinkedList<>();
		
		int canArea = 0;
		int cantArea = 0;
		
		// 아닌사람
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				
				// 파란색 영역
				// 둘 다 ++
				if(map[i][j] == 'B' && can[i][j] == 0) {
					q.add(new Point(i, j));
					can[i][j] = 1;
					
					while(!q.isEmpty()) {
						Point cur = q.poll();
						
						for(int dir = 0; dir < 4; dir++) {
							int nx = cur.x + dx[dir];
							int ny = cur.y + dy[dir];
							
							if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
							if(map[nx][ny] != 'B' || can[nx][ny] != 0) continue;
							
							can[nx][ny] = cant[nx][ny] = 1;
							q.add(new Point(nx, ny));
						}
					}
					
					canArea++;
					cantArea++;
				}
				
				// 초록색 영역
				// 구분 가능 ++
				else if(map[i][j] == 'G' && can[i][j] == 0) {
					q.add(new Point(i, j));
					can[i][j] = 1;
					
					while(!q.isEmpty()) {
						Point cur = q.poll();
						
						for(int dir = 0; dir < 4; dir++) {
							int nx = cur.x + dx[dir];
							int ny = cur.y + dy[dir];
							
							if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
							if(map[nx][ny] != 'G' || can[nx][ny] != 0) continue;
							
							can[nx][ny] = 1;
							q.add(new Point(nx, ny));
						}
					}
					
					canArea++;
				}
				
				// 빨간색 영역
				// 구분 가능 ++
				else if(map[i][j] == 'R' && can[i][j] == 0) {
					q.add(new Point(i, j));
					can[i][j] = 1;
					
					while(!q.isEmpty()) {
						Point cur = q.poll();
						
						for(int dir = 0; dir < 4; dir++) {
							int nx = cur.x + dx[dir];
							int ny = cur.y + dy[dir];
							
							if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
							if(map[nx][ny] != 'R' || can[nx][ny] != 0) continue;
							
							can[nx][ny] = 1;
							q.add(new Point(nx, ny));
						}
					}
					
					canArea++;
				}
				
				// 초록, 빨간색 영역
				// 구분 불가능 ++
				if(map[i][j] != 'B' && cant[i][j] == 0) {
					q.add(new Point(i, j));
					cant[i][j] = 1;
					
					while(!q.isEmpty()) {
						Point cur = q.poll();
						
						for(int dir = 0; dir < 4; dir++) {
							int nx = cur.x + dx[dir];
							int ny = cur.y + dy[dir];
							
							if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
							if(map[nx][ny] == 'B' || cant[nx][ny] != 0) continue;
							
							cant[nx][ny] = 1;
							q.add(new Point(nx, ny));
						}
					}
					
					cantArea++;
				}
				
			}
		}
		
		System.out.println(canArea + " " + cantArea);
		
	}
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}



