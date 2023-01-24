// 시뮬레이션.boj11559;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class Boj11559 {
	static char[][] field = new char[12][6];
	static int[][] connected = new int[12][6];
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i=0; i<12; i++) {
			String s = br.readLine();
			for (int j=0; j<6; j++) {
				field[i][j] = s.charAt(j);
			}
		}
		int ans = 0;
		// 터질게 있는지 확인 (없으면 종료) - 한번에 터트림 - 아래로 떨어트림
		while (isBurstable()) {
			ans++;
			for (int i=0; i<12; i++) {
				for (int j=0; j<6; j++) {
					if (connected[i][j]>=4) field[i][j]='.';
				}
			}
			for (int j=0; j<6; j++) {
				int idx = 11;
				for (int i=11; i>=0; i--) {
					if (field[i][j] != '.') {
						field[idx--][j] = field[i][j];
					}
				}
				for (int i=idx; i>=0; i--) {
					field[i][j] = '.';
				}
			}
//			for (int i=0; i<12; i++) {
//				for (int j=0; j<6; j++) {
//					System.out.print(field[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		System.out.println(ans);
	}
	private static boolean isBurstable() { // bfs를 돌며 각 포인트에 대해 연결된 같은 색 뿌요 수를 센다 (자신 포함)
		int mx = 0;
		for (int i=0; i<12; i++) {
			for (int j=0; j<6; j++) {
				if (field[i][j] == '.') connected[i][j] = 0;
				else {
					char now = field[i][j];
					boolean[][] vis = new boolean[12][6];
					int p = 0;
					Queue<int[]> q = new LinkedList<>();
					q.add(new int[] {i,j});
					vis[i][j] = true;
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						p++;
						int curX = cur[0];
						int curY = cur[1];
						for (int dir=0; dir<4; dir++) {
							int nx = curX + dx[dir];
							int ny = curY + dy[dir];
							if (nx<0 || nx>=12 || ny<0 || ny>=6) continue;
							if (field[nx][ny] != now || vis[nx][ny]) continue;
							q.add(new int[] {nx,ny});
							vis[nx][ny] = true;
						}
					}
					if (p>mx) mx = p;
					connected[i][j] = p;
				}
			}
		}
		if (mx>=4) return true;
		else return false;
	}
}
