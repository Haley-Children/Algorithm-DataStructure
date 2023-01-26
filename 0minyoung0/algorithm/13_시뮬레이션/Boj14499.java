// 시뮬레이션.boj14499;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14499 {
	static int top, bottom, left, right, front, back;
	static int[][] map;
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		// 명령 k개 반복
		for (int i=0; i<k; i++) { // 동1, 서2, 북3, 남4
			int dir = Integer.parseInt(st.nextToken());
			// 주사위가 map 밖으로 나가려고 하는지 여부 확인해서 그럴 경우 무시
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
			// 주사위가 특정 방향으로 구르는거 구현
			rotateDice(dir);
			// 바닥 조건에 따라 두 가지 중 하나 발생
			// 1. map이 0이면 주사위 bottom을 map에 복사
			// 2. map이 0이 아니면 map에서 주사위 bottom으로 복사 후, map을 0으로 만듦
			if (map[nx][ny] == 0) {
				map[nx][ny] = bottom;
			}
			else {
				bottom = map[nx][ny];
				map[nx][ny] = 0;
			}
			// 주사위 윗면을 출력
			sb.append(top).append("\n");
			// x,y를 갱신
			x = nx;
			y = ny;
		}
		System.out.println(sb);
	}
	private static void rotateDice (int dir) {
		int temp = top;
		switch(dir) {
			case 1: top = left;
					left = bottom;
					bottom = right;
					right = temp;
				break;
			case 2: top = right;
					right = bottom;
					bottom = left;
					left = temp;
				break;
			case 3: top = front;
					front = bottom;
					bottom = back;
					back = temp;
				break;
			case 4: top = back;
					back = bottom;
					bottom = front;
					front = temp;
				break;
		}
	}
}