package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소
// 벽 3개로 만들 수 있는 안전영역의 최댓값을 구하라.
// 0 빈칸(이동 가능) 1 벽 2 바이러스 (상하좌우로 움직임 -> 바이러스의 퍼지는 방향과 가중치를 고려해보았을 때 bfs)
// 바이러스는 2개 이상, 10 이하
// 1. 바이러스 각각 따로 동시에 움직인다. 큐에 다 넣고 한번에 시작해야 한다. -> 주의) 큐를 static으로 선언해서 관리하면 안됨.
// 2. 벽 3개로 0의 갯수를 최대로 구하는 법.
// 비어있는 포인트들을 다 가져와서, 백트래킹으로 가능한 3가지 벽 설치 조합 가짓수를 구한 뒤 bfs를 호출한다.
// 원본 board와 복사본 board를 따로 두어서 각각의 경우 안전영역을 구한다. 이때 얕은 복사가 아닌 "깊은 복사"를 이용하여 원본과 복사본이 서로 영향을 주지 않도록 한다.

class Points {

	int x;
	int y;

	public Points(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_14502 {

	static int n, m;
	static int[][] board;
	static int[] dix = {1, 0, -1, 0};
	static int[] diy = {0, 1, 0, -1};
	static int maxSafeArea = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];

		// 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);
		System.out.println(maxSafeArea);

	}

	// 가능한 벽의 조합 수 구하기 (dfs/백트래킹 이용)
	private static void dfs(int d) {
		if (d == 3) {
			bfs();
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 0) {
					board[i][j] = 1;
					dfs(d+1);
					board[i][j] = 0;
				}
			}
		}
	}

	// 벽 3개 설치 후 bfs 실행 -> 바이러스 퍼뜨려서 안전영역 구하기
	// 큐, copyBoard를 매번 정의해야 벽 조합에 따른 안전영역을 별도로 구할 수 있음
	private static void bfs() {

		Queue<Points> queue = new LinkedList<>();

		// 바이러스 지점 큐에 넣어두기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 2) {
					queue.add(new Points(i,j));
				}
			}
		}

		// 깊은 복사
		int[][] copyBoard = new int[n][m];

		for (int i = 0; i < board.length; i++) {
			System.arraycopy(board[i], 0, copyBoard[i], 0, board[i].length);
		}


		while (!queue.isEmpty()) {
			Points cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = dix[i] + cur.x;
				int ny = diy[i] + cur.y;

				if (nx <0 || nx >= n || ny < 0 || ny >=m) continue;
				if (copyBoard[nx][ny] != 0) continue;
				queue.add(new Points(nx,ny));
				copyBoard[nx][ny] = 2;
			}
		}
		checkSafeZone(copyBoard);
	}

	private static void checkSafeZone(int[][] copyBoard) {
		int area = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (copyBoard[i][j] == 0) area++;
			}
		}
		maxSafeArea = Math.max(area, maxSafeArea);
	}

}
