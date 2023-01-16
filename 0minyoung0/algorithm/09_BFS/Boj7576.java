// boj 7576
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Boj7576 {
	static int m, n;
	static int[][] tom;
	static int[][] day;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static Queue<int[]> Q = new LinkedList<>();
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		tom = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				tom[i][j] = Integer.parseInt(st.nextToken());
				if (tom[i][j] == 1) Q.add(new int[] {i,j});
			}
		}
		day = new int[n][m];
		System.out.println(bfs(Q));
	}
	private static int bfs(Queue<int[]> q) {
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			for (int dir = 0; dir < 4; dir++) {
				int nx = curX + dx[dir];
				int ny = curY + dy[dir];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (tom[nx][ny] != 0 || day[nx][ny] != 0) continue;
				q.add(new int[] {nx,ny});
				day[nx][ny] = day[curX][curY] + 1;
			}
		}
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tom[i][j] == 0 && day[i][j] == 0) return -1;
				result = Math.max(result, day[i][j]);
			}
		}
		return result;
	}
}