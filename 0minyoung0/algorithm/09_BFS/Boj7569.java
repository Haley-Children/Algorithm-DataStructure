// boj 7569
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Boj7569 {
	static int[][][] tom;
	static int[][][] day;
	static int[] dx = {1,-1,0,0,0,0};
	static int[] dy = {0,0,1,-1,0,0};
	static int[] dz = {0,0,0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		tom = new int[h][n][m];
		day = new int[h][n][m];
		Queue<int[]> q = new LinkedList<>();
		for (int i=0; i<h; i++) {
			for (int j=0; j<n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k=0; k<m; k++) {
					tom[i][j][k] = Integer.parseInt(st.nextToken());
					if (tom[i][j][k] == 1) q.add(new int[] {i,j,k});
					else if (tom[i][j][k] == 0) day[i][j][k] = -1;
				}
			}
		}
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			int curZ = cur[2];
			for (int dir=0; dir<6; dir++) {
				int nx = curX + dx[dir];
				int ny = curY + dy[dir];
				int nz = curZ + dz[dir];
				if (nx<0 || nx>=h || ny<0 || ny>=n || nz<0 || nz>=m) continue;
				if (tom[nx][ny][nz] != 0 || day[nx][ny][nz] != -1) continue;
				q.add(new int[] {nx,ny,nz});
				day[nx][ny][nz] = day[curX][curY][curZ] + 1;
			}
		}
		int ans = 0;
		label: for (int i=0; i<h; i++) {
			for (int j=0; j<n; j++) {
				for (int k=0; k<m; k++) {
					if (day[i][j][k] == -1) {
						ans = -1;
						break label;
					}
					if (day[i][j][k] > ans) ans = day[i][j][k];
				}
			}
		}
		System.out.println(ans);
	}
}
