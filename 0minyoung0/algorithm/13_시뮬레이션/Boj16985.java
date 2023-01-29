// 시뮬레이션.boj16985;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Boj16985 {
	static int[][][] input = new int[5][5][5];
	static int[] dx = {1,-1,0,0,0,0};
	static int[] dy = {0,0,1,-1,0,0};
	static int[] dz = {0,0,0,0,1,-1};
	static int ans = 125;
	static int[][][] vertex = {{{0,0,0},{4,4,4}},{{0,0,4},{4,4,0}},{{0,4,0},{4,0,4}},{{4,0,0},{0,4,4}}}; // [4][2][3]
	static boolean[] isUsed = new boolean[5];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int x=0; x<5; x++) {
			for (int y=0; y<5; y++) {
				st = new StringTokenizer(br.readLine());
				for (int z=0; z<5; z++) {
					input[x][y][z] = Integer.parseInt(st.nextToken());
				}
			}
		}
		func(0, new int[5][5][5]);
		if (ans==125) ans = -1;
		System.out.println(ans);
	}
	private static void func(int k, int[][][] maze) {
		if (k==5) {
			// 시작-도착점쌍이 모두 1이라면 bfs 시도
			label: for (int v=0; v<4; v++) {
				int[][][] dis = new int[5][5][5];
				if (maze[vertex[v][0][0]][vertex[v][0][1]][vertex[v][0][2]] == 1
				&& maze[vertex[v][1][0]][vertex[v][1][1]][vertex[v][1][2]] == 1) {
					Queue<int[]> q = new LinkedList<>();
					q.add(new int[] {vertex[v][0][0],vertex[v][0][1],vertex[v][0][2]});
					dis[vertex[v][0][0]][vertex[v][0][1]][vertex[v][0][2]] = 1;
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						int curX = cur[0];
						int curY = cur[1];
						int curZ = cur[2];
						for (int dir=0; dir<6; dir++) {
							int nx = curX + dx[dir];
							int ny = curY + dy[dir];
							int nz = curZ + dz[dir];
							if (nx<0||nx>4||ny<0||ny>4||nz<0||nz>4) continue;
							if (maze[nx][ny][nz]==0||dis[nx][ny][nz]!=0) continue;
							if (nx==vertex[v][1][0]&&ny==vertex[v][1][1]&&nz==vertex[v][1][2]) {
								if (ans > dis[curX][curY][curZ]) {
									ans = dis[curX][curY][curZ];
								}
								continue label;
							}
							q.add(new int[] {nx,ny,nz});
							dis[nx][ny][nz] = dis[curX][curY][curZ] + 1;
						}
					}
				}
			}
			return;
		}
		if (k!=4) {
			for (int plate=0; plate<5; plate++) {
				if(!isUsed[plate]) {
					isUsed[plate] = true;
					// 재귀호출
					for (int i=0; i<5; i++) {
						for (int j=0; j<5; j++) {
							maze[k][i][j] = input[plate][i][j];
						}
					}
					func(k+1, maze);
					for (int i=0; i<5; i++) {
						for (int j=0; j<5; j++) {
							maze[k][i][j] = r(input[plate])[i][j];
						}
					}
					func(k+1, maze);
					for (int i=0; i<5; i++) {
						for (int j=0; j<5; j++) {
							maze[k][i][j] = r(r(input[plate]))[i][j];
						}
					}
					func(k+1, maze);
					for (int i=0; i<5; i++) {
						for (int j=0; j<5; j++) {
							maze[k][i][j] = r(r(r(input[plate])))[i][j];
						}
					}
					func(k+1, maze);
					isUsed[plate] = false;
				}
			}
		}
		else { // k == 4
			for (int plate=0; plate<5; plate++) {
				if(!isUsed[plate]) {
					isUsed[plate] = true;
					// 재귀호출
					for (int i=0; i<5; i++) {
						for (int j=0; j<5; j++) {
							maze[k][i][j] = input[plate][i][j];
						}
					}
					func(k+1, maze);
					isUsed[plate] = false;
				}
			}
		}
	}
	private static int[][] r(int[][] arr){
		int[][] ans = new int[5][5];
		for (int i=0; i<5; i++) {
			for (int j=0; j<5; j++) {
				ans[i][j] = arr[4-j][i];
			}
		}
		return ans;
	}
}
