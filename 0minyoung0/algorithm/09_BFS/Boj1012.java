// boj 1012
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Boj1012 {
	static int T, m, n, k;
	static int[][] ground;
	static boolean[][] vis;
	static Queue<int[]> Q = new LinkedList<>();
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int a=0; a<T; a++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			ground = new int[n][m];
			vis = new boolean[n][m];
			for (int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				ground[y][x] = 1;
			}
			int ans = 0;
			for (int i=0; i<n; i++) {
				for (int j=0; j<m; j++) {
					if (ground[i][j] == 1 && !vis[i][j]) {
						Q.add(new int[] {i,j});
						vis[i][j] = true;
						ans++;
						while (!Q.isEmpty()) {
							int[] cur = Q.poll();
							int curX = cur[0];
							int curY = cur[1];
							for (int dir=0; dir<4; dir++) {
								int nx = curX + dx[dir];
								int ny = curY + dy[dir];
								if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
								if (ground[nx][ny]==0 || vis[nx][ny]) continue;
								Q.add(new int[] {nx,ny});
								vis[nx][ny] = true;
							}
						}
					}
				}
			}
			System.out.println(ans);
		}
	}
}
