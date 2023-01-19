// boj 1926
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Boj1926 {
	static int n, m;
	static int[][] pic;
	static boolean[][] vis;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		pic = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) pic[i][j] = Integer.parseInt(st.nextToken());
		}
		vis = new boolean[n][m];
		int numPics = 0;
		int maxSize = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (pic[i][j] == 1 && !vis[i][j]) {
					numPics++;
					maxSize = Math.max(maxSize, bfs(i,j));
				}
			}
		}
		System.out.println(numPics);
		System.out.println(maxSize);
	}
	private static int bfs(int x, int y) {
		Queue<int[]> Q = new LinkedList<>();
		Q.add(new int[] {x,y});
		vis[x][y] = true;
		int size = 1;
		while(!Q.isEmpty()) {
			int[] cur = Q.poll();
			int curX = cur[0];
			int curY = cur[1];
			for (int dir = 0; dir < 4; dir++) {
				int nx = curX + dx[dir];
				int ny = curY + dy[dir];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (vis[nx][ny] || pic[nx][ny] == 0) continue;
				Q.add(new int[] {nx,ny});
				vis[nx][ny] = true;
				size++;
			}
		}
		return size;
	}
}




















