// 시뮬레이션.boj15686;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15686 {
	static int n,m,c;
	static int ans = Integer.MAX_VALUE;
	static int[][] chi = new int[13][2];
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int[][] vil = new int[n][n];
		m = Integer.parseInt(st.nextToken());
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				vil[i][j] = temp;
				if (temp == 2) {
					chi[c][0] = i;
					chi[c++][1] = j;
				}
			}
		}
		func(vil, 0, -1);
		System.out.println(ans);
	}
	// 선택된 치킨집은 3으로 표시
	private static void func(int[][] arr, int level, int pre) {
		if (level == m) { // 치킨집 m개를 모두 골랐다면 계산 후 리턴
			ans = Math.min(ans, calCDistance(arr));
			return;
		}
		for (int i=pre+1; i<=c-m+level; i++) {
			int[][] newArr = new int[n][n];
			for (int x=0; x<n; x++) {
				for (int y=0; y<n; y++) {
					newArr[x][y] = arr[x][y];
				}
			}
			newArr[chi[i][0]][chi[i][1]] = 3;
			func(newArr, level+1, i);
		}
	}
	private static int calCDistance(int[][] arr) { // bfs로 치킨집으로부터의 거리 계산
		int ans = 0;
		int[][] dis = new int[n][n];
		Queue<int[]> q = new LinkedList<>();
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (arr[i][j] == 3) {
					q.add(new int[] {i,j});
				}
			}
		}
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curX = cur[0];
			int curY = cur[1];
			for (int dir=0; dir<4; dir++) {
				int nx = curX + dx[dir];
				int ny = curY + dy[dir];
				if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
				if (dis[nx][ny] != 0 || arr[nx][ny] == 3) continue;
				q.add(new int[] {nx,ny});
				dis[nx][ny] = dis[curX][curY] + 1;
				if (arr[nx][ny] == 1) ans += dis[nx][ny];
			}
		}
		return ans;
	}
}
