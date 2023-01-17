// boj 4179
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Boj4179 {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		char[][] maze = new char[r][c];
		int[][] timeF = new int[r][c];
		int[][] timeJ = new int[r][c];
		Queue<int[]> QF = new LinkedList<>();
		Queue<int[]> QJ = new LinkedList<>();
		for (int i = 0; i < r; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				maze[i][j] = temp[j];
				if (maze[i][j] == 'F') {
					QF.add(new int[] {i,j});
					timeF[i][j] = 1;
				}
				else if (maze[i][j] == 'J') {
					QJ.add(new int[] {i,j});
					timeJ[i][j] = 1;
				}
			}
		}
		br.close();
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		while(!QF.isEmpty()) {
			int[] cur = QF.poll();
			int curX = cur[0];
			int curY = cur[1];
			for (int dir = 0; dir < 4; dir++) {
				int nx = curX + dx[dir];
				int ny = curY + dy[dir];
				if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
				if (maze[nx][ny] == '#' || timeF[nx][ny] >= 1) continue;
				QF.add(new int[] {nx,ny});
				timeF[nx][ny] = timeF[curX][curY] + 1;
			}
		}
		while(!QJ.isEmpty()) {
			int[] cur = QJ.poll();
			int curX = cur[0];
			int curY = cur[1];
			for (int dir = 0; dir < 4; dir++) {
				int nx = curX + dx[dir];
				int ny = curY + dy[dir];
				if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
					System.out.println(timeJ[curX][curY]);
					return;
				}
				if (maze[nx][ny] != '.' || timeJ[nx][ny] >= 1) continue;
				if (timeF[nx][ny] != 0 && timeJ[curX][curY] + 1 >= timeF[nx][ny]) continue;
				QJ.add(new int[] {nx,ny});
				timeJ[nx][ny] = timeJ[curX][curY] + 1;
			}
		}
		System.out.println("IMPOSSIBLE");
	}
}