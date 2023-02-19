package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 연구소
 * 1. 입력받으면서 바이러스가 있는 위치를 따로 저장, 맵도 저장후 복사해서 사용
 * 2. 3개의 기둥을 놓을 수 있는 모든 경우의 수를 구현
 * 3. 기둥을 모두 놓았으면 모든 0인 지점에 대해 bfs하며 넓이를 계산한다.
 * 3-1. bfs하다가 바이러스(2)를 만나면 넓이 갱신 x
 * 3-2. 바이러스를 만나지않고 끝까지 진행하면 최대 넓이를 갱신한다.
 */
public class Main_14502 {
	
	static int n, m;
	static int[][] map;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int maxSize = 0;
		
		map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int wr1 = 0; wr1 < n; wr1++) {
			for(int wc1 = 0; wc1 < m; wc1++) {
				if(map[wr1][wc1] != 0) continue;
				map[wr1][wc1] = 1;
				
				for(int wr2 = 0; wr2 < n; wr2++) {
					for(int wc2 = 0; wc2 < m; wc2++) {
						if(map[wr2][wc2] != 0) continue;
						map[wr2][wc2] = 1;
						
						for(int wr3 = 0; wr3 < n; wr3++) {
							for(int wc3 = 0; wc3 < m; wc3++) {
								if(map[wr3][wc3] != 0) continue;
								map[wr3][wc3] = 1;
								maxSize = Math.max(maxSize, bfs());
								map[wr3][wc3] = 0;
							}
						}
						map[wr2][wc2] = 0;
					}
				}
				map[wr1][wc1] = 0;
			}
		}	// end of for
		
		System.out.println(maxSize);
		
	}	// end of main
	
	static int bfs() {
		
		Queue<int[]> q = new LinkedList<>();
		int[][] vis = new int[n][m];
		
		int totalArea = 0;
		
		// initialize
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 0 && vis[i][j] == 0) {
					boolean hasVirus = false;
					
					q.add(new int[] {i,j});
					vis[i][j] = 1;
					int size = 1;
					
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						int curX = cur[0];
						int curY = cur[1];
						
						for(int dir = 0; dir < 4; dir++) {
							int nx = curX + dx[dir];
							int ny = curY + dy[dir];
							
							if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
							if(map[nx][ny] == 2) hasVirus = true;
							if(vis[nx][ny] == 1 || map[nx][ny] != 0) continue;
							
							size++;
							vis[nx][ny] = 1;
							q.add(new int[] {nx,ny});
						}
					}// end of while
					totalArea += hasVirus ? 0 : size;
				}
				
			}
		}
	
		return totalArea;
	}
	
}

