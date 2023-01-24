// 시뮬레이션.boj15683;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15683 {
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int n,m,ans;
	static int cctvNum;
	static int[][] map; // x, y
	static int[][] cctv = new int[3][8]; // column contains type, x, y
	static int[][] cctvDirNum = {{4,1}, {2,2}, {4,2}, {4,3}, {1,4}};
	static int[][][] cctvDxDy = {{{0},{1},{2},{3}},
								{{0,2},{1,3}},
								{{0,1},{1,2},{2,3},{3,0}},
								{{0,1,2},{1,2,3},{2,3,0},{3,0,1}},
								{{0,1,2,3}}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		ans = n * m;
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if (temp != 0 && temp != 6) {
					cctv[0][cctvNum] = temp - 1;
					cctv[1][cctvNum] = i;
					cctv[2][cctvNum++] = j;
				}
			}
		}
		func(0, map);
		System.out.println(ans);
	}
	private static void func (int k, int[][] arr) {
		if (k==cctvNum) {
			int temp = 0;
			for (int i=0; i<n; i++) {
				for (int j=0; j<m; j++) {
					if (arr[i][j] == 0) temp++;
				}
			}
			if (ans > temp) ans = temp;
			return;
		}
		for (int i=0; i<cctvDirNum[cctv[0][k]][0]; i++) { // i는 cctv가 가지는 방향의 케이스 갈래
			int[][] tempArr = new int[n][m];
			for (int x=0; x<n; x++) {
				for (int y=0; y<m; y++) {
					tempArr[x][y] = arr[x][y];
				}
			}
			for (int j=0; j<cctvDirNum[cctv[0][k]][1]; j++) { // j는 한 케이스 내에서 계산할 방향의 종류
				int dir = cctvDxDy[cctv[0][k]][i][j]; // dir는 j 내부의 각 방향
				int curX = cctv[1][k] + dx[dir];
				int curY = cctv[2][k] + dy[dir];
				while (curX>=0 && curX<n && curY>=0 && curY<m) {
					if (tempArr[curX][curY] == 6) break;
					if (tempArr[curX][curY] == 0) tempArr[curX][curY] = 7;
					curX += dx[dir];
					curY += dy[dir];
				}
			}
			func(k+1, tempArr);
		}
	}
}
