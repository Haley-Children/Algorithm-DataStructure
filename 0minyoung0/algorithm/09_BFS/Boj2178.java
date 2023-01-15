// boj 2178¹ø: ¹Ì·Î Å½»ö
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Boj2178 {
	static int n, m;
	static char[][] maze;
	static int[][] dis;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		maze = new char[n][m];
		for (int i = 0; i < n; i++) {
			String string = br.readLine();
			for (int j = 0; j < m; j++) maze[i][j] = string.charAt(j);
		}
		dis = new int[n][m];
		System.out.print(bfs());
	}
	private static int bfs() {
		Queue<int[]> Q = new LinkedList<>();
		Q.add(new int[] {0,0});
		dis[0][0] = 1;
		while(!Q.isEmpty()) {
			int[] cur = Q.poll();
			int curX = cur[0];
			int curY = cur[1];
			for (int dir = 0; dir < 4; dir++) {
				int nx = curX + dx[dir];
				int ny = curY + dy[dir];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (dis[nx][ny] > 0 || maze[nx][ny] == '0') continue;
				if (nx == n - 1 && ny == m - 1) return dis[curX][curY] + 1;
				Q.add(new int[] {nx,ny});
				dis[nx][ny] = dis[curX][curY] + 1;
			}
		}
		return 0;
	}
}