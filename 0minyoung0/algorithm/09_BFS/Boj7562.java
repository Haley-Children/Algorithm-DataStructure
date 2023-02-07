// boj 7562
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Boj7562 {
	static int[] dx = {-2,-2,-1,-1,1,1,2,2};
	static int[] dy = {-1,1,-2,2,-2,2,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test_case=0; test_case<t; test_case++) {
			int l = Integer.parseInt(br.readLine());
			int[][] board = new int[l][l];
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {x,y});
			board[x][y] = 1;
			st = new StringTokenizer(br.readLine());
			int xx = Integer.parseInt(st.nextToken());
			int yy = Integer.parseInt(st.nextToken());
			if (x==xx && y==yy) {
				System.out.println(0);
				continue;
			}
			label: while(!q.isEmpty()) {
				int[] cur = q.poll();
				int curX = cur[0];
				int curY = cur[1];
				for (int dir=0; dir<8; dir++) {
					int nx = curX + dx[dir];
					int ny = curY + dy[dir];
					if (nx<0 || nx>=l || ny<0 || ny>=l) continue;
					if (nx == xx && ny == yy) {
						System.out.println(board[curX][curY]);
						break label;
					}
					if (board[nx][ny] != 0) continue;
					q.add(new int[] {nx,ny});
					board[nx][ny] = board[curX][curY] + 1;
				}
			}
		}
	}
}
