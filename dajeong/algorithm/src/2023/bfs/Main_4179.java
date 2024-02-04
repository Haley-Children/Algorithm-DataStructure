package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// BOJ 불!
/*
 * 시작점이 두 개인 bfs
 * 이 문제는 시작점 별 bfs 전파가 서로에게 영향을 주지 않으므로 (상호작용x) 각각 bfs를 돌아도 된다.
 * 하지만 서로 독립적이지 않다면 시간 순으로 각각 동시에 진행시켜야 한다. (백트래킹 등 개념 추가 이용 - 백준 18809번)
 *
 * 깨달은 점) 문제를 잘 읽고 반례도 함께 생각하자!
 * 지훈이와 달리 불은 0개 이상이다. 즉, 화재가 일어나지 않았을 수도 있고, 화재가 두 군데 이상에서 발생할 수도 있는 것이다.
 * -> 화재가 일어나는 지점 ('F')을 Queue에 모두 담은 뒤 bfs 로직을 실행해야 한다.
 * -> 화재가 일어나지 않았을 경우, (지훈이 이동한 시간 >= 화재 이동 시간) 이 항상 만족하므로 초기값이 0인 배열을 사용하면 안된다.
 */
public class Main_4179 {

	static int r, c;
	static char[][] board;
	static int[][] visJ, visF;
	static Queue<Point> queueJ, queueF;
	static int[] dix = {1, 0, -1, 0};
	static int[] diy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		r = Integer.parseInt(s[0]);
		c = Integer.parseInt(s[1]);
		board = new char[r][c];
		visJ = new int[r][c];
		visF = new int[r][c];
		queueJ = new LinkedList<>();
		queueF = new LinkedList<>();

		// 입출력
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				board[i][j] = str.charAt(j);
			}
		}

		// 전체 보드 탐색
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (board[i][j] == 'F') {
					queueF.offer(new Point(i, j));
					visF[i][j] = 1;
				}
				if (board[i][j] == 'J') {
					queueJ.offer(new Point(i, j));
					visJ[i][j] = 1;
				}
			}
		}

		bfsF();

		int answer = bfsJ();
		if (answer == 0) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(answer - 1); // 초기값을 1로 지정했었음.
		}
	}

	private static int bfsJ() {
		while (!queueJ.isEmpty()) {
			Point cur = queueJ.poll();
			for (int i = 0; i < 4; i++) {
				int nx = dix[i] + cur.x;
				int ny = diy[i] + cur.y;

				if (nx < 0 || nx >= r || ny < 0 || ny >= c) { // 탈출 성공
					return visJ[cur.x][cur.y] + 1;
				}
				if (board[nx][ny] == '#' || visJ[nx][ny] > 0) {
					continue;
				}
				if (visF[nx][ny] > 0 && visF[nx][ny] <= visJ[cur.x][cur.y] + 1) {
					continue; // 반례! 불이 지나간 부분만 체크해야 한다. + 지훈이는 아직 nx,ny 기록 전이므로 cur 사용해야 함.
				}

				visJ[nx][ny] = visJ[cur.x][cur.y] + 1;
				queueJ.offer(new Point(nx, ny));
			}
		}

		return 0;
	}

	private static void bfsF() {
		while (!queueF.isEmpty()) {
			Point cur = queueF.poll();
			for (int i = 0; i < 4; i++) {
				int nx = dix[i] + cur.x;
				int ny = diy[i] + cur.y;

				if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
					continue;
				}
				if (board[nx][ny] == '#' || visF[nx][ny] > 0) {
					continue;
				}

				visF[nx][ny] = visF[cur.x][cur.y] + 1;
				queueF.offer(new Point(nx, ny));
			}
		}
	}

	private static class Point {

		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
