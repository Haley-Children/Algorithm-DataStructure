// boj 5427
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Boj5427 {
	static char[][] map;
	static int w, h;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		label: for (int test_case=0; test_case<t; test_case++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			Queue<int[]> qF = new LinkedList<>();
			Queue<int[]> qS = new LinkedList<>();
			int[][] timeF = new int[h][w];
			int[][] timeS = new int[h][w];
			for (int i=0; i<h; i++) {
				String s = br.readLine();
				for (int j=0; j<w; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '*') {
						qF.add(new int[] {i,j});
						timeF[i][j] = 1;
					}
					else if (map[i][j] == '@') {
						qS.add(new int[] {i,j});
						timeS[i][j] = 1;
					}
				}
			}
			while(!qF.isEmpty()) {
				int[] cur = qF.poll();
				int curX = cur[0];
				int curY = cur[1];
				for (int dir=0; dir<4; dir++) {
					int nx = curX + dx[dir];
					int ny = curY + dy[dir];
					if (nx<0 || nx>=h || ny<0 || ny>=w) continue;
					if (map[nx][ny]=='#' || timeF[nx][ny]!=0) continue;
					qF.add(new int[] {nx,ny});
					timeF[nx][ny] = timeF[curX][curY] + 1;
				}
			}
			while(!qS.isEmpty()) {
				int[] cur = qS.poll();
				int curX = cur[0];
				int curY = cur[1];
				for (int dir=0; dir<4; dir++) {
					int nx = curX + dx[dir];
					int ny = curY + dy[dir];
					if (nx<0 || nx>=h || ny<0 || ny>=w) {
						System.out.println(timeS[curX][curY]);
						continue label;
					}
					if (map[nx][ny]=='#' || (timeF[nx][ny]!=0 && timeS[curX][curY]+1>=timeF[nx][ny])) continue;
					qS.add(new int[] {nx,ny});
					timeS[nx][ny] = timeS[curX][curY] + 1;
				}
			}
			System.out.println("IMPOSSIBLE");
		}
	}
}
