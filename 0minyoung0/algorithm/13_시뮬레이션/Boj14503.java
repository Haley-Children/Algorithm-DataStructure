// 시뮬레이션.boj14503;

import java.io.*;
import java.util.*;

public class Boj14503 {
	static int n,m,r,c,d,ans;
	static int[][] place;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		place = new int[n][m];
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				place[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clean();
		System.out.println(ans);
	}
	private static void clean() {
		boolean isStuck = false;
		boolean cleanable = true;
		while(!isStuck) {
			if (cleanable && place[r][c] == 0) {
				place[r][c] = 2;
				ans++;
				cleanable = false;
			}
			if (place[r+dx[adj(d-1)]][c+dy[adj(d-1)]] == 0) {
				d = adj(d-1);
				r += dx[d];
				c += dy[d];
				cleanable = true;
				continue;
			}
			if (place[r+dx[adj(d-2)]][c+dy[adj(d-2)]] == 0) {
				d = adj(d-2);
				r += dx[d];
				c += dy[d];
				cleanable = true;
				continue;
			}
			if (place[r+dx[adj(d-3)]][c+dy[adj(d-3)]] == 0) {
				d = adj(d-3);
				r += dx[d];
				c += dy[d];
				cleanable = true;
				continue;
			}
			if (place[r+dx[d]][c+dy[d]] == 0) {
				r += dx[d];
				c += dy[d];
				cleanable = true;
				continue;
			}
			if (place[r-dx[d]][c-dy[d]] != 1) {
				r -= dx[d];
				c -= dy[d];
				continue;
			}
			isStuck = true;
		}
	}
	private static int adj(int n) { // 인덱스 mod4로 정리
		return (n+4)%4;
	}
}
