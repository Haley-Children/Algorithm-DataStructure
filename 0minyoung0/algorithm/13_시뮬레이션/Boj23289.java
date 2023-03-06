import java.io.*;
import java.util.*;

public class Boj23289 {
	static int[][] heat;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 방의 크기
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		// 조사 대상인 칸의 온도가 모두 k 이상이 되면 테스트 중단
		int k = Integer.parseInt(st.nextToken());
		
		// 조사 대상인 칸 리스트에 넣기
		List<int[]> list = new ArrayList<>();
		// 온풍기 정보 리스트에 넣기
		List<int[]> heater = new ArrayList<>();
		for (int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<c; j++) {
				int temp = Integer.parseInt(st.nextToken());
				// 빈칸은 스킵
				if (temp == 0) continue;
				// 조사 대상인 칸
				if (temp == 5) {
					list.add(new int[] {i,j});
				}
				// 온풍기
				else {
					if (temp == 1) {
						heater.add(new int[] {i,j,2});
					}else if (temp == 2) {
						heater.add(new int[] {i,j,0});
					}else if (temp == 3) {
						heater.add(new int[] {i,j,1});
					}else { // temp == 4
						heater.add(new int[] {i,j,3});
					}
				}
			}
		}
		
		// 벽 개수
		int w = Integer.parseInt(br.readLine());
		
		// 벽 정보 (x좌표, y좌표, 벽의방향(좌, 상, 우, 하))
		boolean[][][] wall = new boolean[r][c][4];
		for (int i=0; i<w; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken());
			// 가로벽인 경우
			if (t == 0) {
				wall[x][y][1] = true;
				wall[x-1][y][3] = true;
			}
			// 세로벽인 경우
			else {
				wall[x][y][2] = true;
				wall[x][y+1][0] = true;
			}
		}
		
		// 온풍기에 의해 더해지는 열 정보
		heat = new int[r][c];
		for (int i=0; i<heater.size(); i++) {
			int[] h = heater.get(i);
			int dir = h[2];
			Queue<int[]> q = new ArrayDeque<>();
			boolean[][] vis = new boolean[r][c];
			heat[h[0] + dx[dir]][h[1] + dy[dir]] += 5;
			q.add(new int[] {h[0] + dx[dir], h[1] + dy[dir], 5});
			
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int curX = cur[0];
				int curY = cur[1];
				if (cur[2] == 1) break;
				// 왼쪽 + 진행
				if (!wall[curX][curY][(dir+3)%4]
					&& 0 <= curX+dx[(dir+3)%4] && curX+dx[(dir+3)%4] < r
					&& 0 <= curY+dy[(dir+3)%4] && curY+dy[(dir+3)%4] < c
					&& !wall[curX+dx[(dir+3)%4]][curY+dy[(dir+3)%4]][dir]) {
					int nx = curX+dx[(dir+3)%4]+dx[dir];
					int ny = curY+dy[(dir+3)%4]+dy[dir];
					if (!(nx < 0 || nx >= r || ny < 0 || ny >= c) && !vis[nx][ny]) {
						heat[nx][ny] += cur[2] - 1;
						q.add(new int[] {nx,ny,cur[2]-1});
						vis[nx][ny] = true;
					}
				}
				
				// 진행
				if (!wall[curX][curY][dir]) {
					int nx = curX+dx[dir];
					int ny = curY+dy[dir];
					if (!(nx < 0 || nx >= r || ny < 0 || ny >= c) && !vis[nx][ny]) {
						heat[nx][ny] += cur[2] - 1;
						q.add(new int[] {nx,ny,cur[2]-1});
						vis[nx][ny] = true;
					}
				}
				
				// 오른쪽 + 진행
				if (!wall[curX][curY][(dir+1)%4]
					&& 0 <= curX+dx[(dir+1)%4] && curX+dx[(dir+1)%4] < r
					&& 0 <= curY+dy[(dir+1)%4] && curY+dy[(dir+1)%4] < c
					&& !wall[curX+dx[(dir+1)%4]][curY+dy[(dir+1)%4]][dir]) {
					int nx = curX+dx[(dir+1)%4]+dx[dir];
					int ny = curY+dy[(dir+1)%4]+dy[dir];
					if (!(nx < 0 || nx >= r || ny < 0 || ny >= c) && !vis[nx][ny]) {
						heat[nx][ny] += cur[2] - 1;
						q.add(new int[] {nx,ny,cur[2]-1});
						vis[nx][ny] = true;
					}
				}
			}
		}
		
		// 시뮬레이션
		int[][] map = new int[r][c];
		for (int t=1; t<=100; t++) {
			// 1. 온풍기에서 바람 나옴
			for (int i=0; i<r; i++) {
				for (int j=0; j<c; j++) {
					map[i][j] += heat[i][j];
				}
			}
			
			// 2. 온도가 조절 됨
			int[][] adjust = new int[r][c];
			for (int i=0; i<r-1; i++) {
				for (int j=0; j<c; j++) {
					// 아래칸과 이어지는 가로벽이 없는 경우
					if (!wall[i][j][3]) {
						int temp = (map[i][j] - map[i+1][j]) / 4;
						adjust[i][j] -= temp;
						adjust[i+1][j] += temp;
					}
				}
			}
			for (int i=0; i<r; i++) {
				for (int j=0; j<c-1; j++) {
					// 오른쪽칸과 이어지는 세로벽이 없는 경우
					if (!wall[i][j][2]) {
						int temp = (map[i][j] - map[i][j+1]) / 4;
						adjust[i][j] -= temp;
						adjust[i][j+1] += temp;
					}
				}
			}
			for (int i=0; i<r; i++) {
				for (int j=0; j<c; j++) {
					map[i][j] += adjust[i][j];
				}
			}
			
			// 3. 온도가 1 이상인 바깥쪽 칸의 온도가 1씩 감소
			for (int i=0; i<r; i++) {
				if (map[i][0] > 0) {
					map[i][0]--;
				}
				if (map[i][c-1] > 0) {
					map[i][c-1]--;
				}
			}
			for (int i=1; i<c-1; i++) {
				if (map[0][i] > 0) {
					map[0][i]--;
				}
				if (map[r-1][i] > 0) {
					map[r-1][i]--;
				}
			}
			
			// 4. 조사하는 모든 칸의 온도가 k이상인지 확인, k이상이면 테스트 중단
			boolean terminated = true;
			for (int i=0; i<list.size(); i++) {
				// k미만인 칸이 하나라도 있으면 중단 불가
				if (map[list.get(i)[0]][list.get(i)[1]] < k) {
					terminated = false;
				}
			}
			if (terminated) {
				System.out.println(t);
				return;
			}
			
		}
		
		// 리턴 안됐으면 101 출력
		System.out.println(101);
	}
}
