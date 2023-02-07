import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Maaaaaaaaaze
 * 5개의 판을 회전, 나열 하는 모든 경우의 수에 대해 탐색
 * 1. k번째 위치에 놓을 판을 선택					// 5!
 * 2. k번째 판을 0~3번 회전 시키고 k+1번째 판 선택	// 4
 * 3. k == 5가 되면 bfs로 최단경로 탐색 시작
 * 4. 모든 경우의 최단경로 출력
 * 
 * 필요한 메서드
 * 1. rotate
 * 2. bt
 * 3. bfs
 */
public class BOJ_16985 {
	

	static int n = 5;
	
	static int[][][] plates = new int[n][n][n];
	static boolean[] isUsed = new boolean[n];
	
	static int[][][] maze = new int[n][n][n];
	
	static int[] dx = {1,-1,0,0,0,0};
	static int[] dy = {0,0,1,-1,0,0};
	static int[] dz = {0,0,0,0,1,-1};
	
	static int minDist = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < n; k++) {
					plates[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		bt(0);
		System.out.println(minDist == Integer.MAX_VALUE ? -1 : minDist);
	}
	
	static void bt(int k) {
		if(k == n) {
			minDist = Math.min(minDist ,bfs());
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!isUsed[i]) {
				isUsed[i] = true;
				
				maze[k] = plates[i];
				bt(k + 1);

				maze[k] = rotate(plates[i]);
				bt(k + 1);
				
				maze[k] = rotate(rotate(plates[i]));
				bt(k + 1);
				
				maze[k] = rotate(rotate(rotate(plates[i])));
				bt(k + 1);
				
				isUsed[i] = false;
			}
		}
	}
	
	// 90도 시계방향 회전
	static int[][] rotate(int[][] plate) {
		int[][] rotated = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				rotated[i][j] = plate[n-1-j][i];
			}
		}
		
		return rotated;
	}
	
	static int bfs() {
		int[][][] dist = new int[n][n][n];
		boolean canReach = false;

		Queue<Point> q = new LinkedList<>();
		
		if(maze[0][0][0] == 1) {
			q.add(new Point(0,0,0));
			dist[0][0][0] = 1;
		} else return Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int dir = 0; dir < 6; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				int nz = cur.z + dz[dir];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n || nz < 0 || nz >= n) continue;
				if(maze[nx][ny][nz] == 0 || dist[nx][ny][nz] > 0) continue;
				
				dist[nx][ny][nz] = dist[cur.x][cur.y][cur.z] + 1;
				if(nx == n-1 && ny == n-1 && nz == n-1) {
					canReach = true;
					break;
				}
				q.add(new Point(nx, ny, nz));
			}
		}
		return canReach ? dist[n-1][n-1][n-1] - 1 : Integer.MAX_VALUE;
	}
	
	static class Point {
		int x,y,z;
		Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
}

