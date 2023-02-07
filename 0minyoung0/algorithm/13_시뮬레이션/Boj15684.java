// 시뮬레이션.boj15684;

import java.io.*;
import java.util.*;

public class Boj15684 {
	static int n,m,h;
	static boolean[][] isLine; // 가로선이 놓인 위치
	static int[][] isImpossible; // 새로운 선을 놓을 수 없는 위치 (1 이상이면 불가능)
								 // int로 만들어서 누적해야 인자로 안넘겨주고 처리 가능함
	static int ans = 4;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		isLine = new boolean[h][n-1];
		isImpossible = new int[h][n-1];
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int[] temp = new int[2];
			temp[0] = Integer.parseInt(st.nextToken()) - 1;
			temp[1] = Integer.parseInt(st.nextToken()) - 1;
			isLine[temp[0]][temp[1]] = true;
			if (temp[1] != 0) isImpossible[temp[0]][temp[1]-1] += 1;
			if (temp[1] != n-2) isImpossible[temp[0]][temp[1]+1] += 1;
		}
		func(0);
		if (ans==4) ans = -1;
		System.out.println(ans);
	}
	// 
	private static void func(int k) {
		// 일단 시뮬레이션 돌려서 조작에 성공했으면 ans를 계산하고 리턴
		if(simulation(isLine)) {
			ans = Math.min(k, ans);
			return;
		}
		// k=3 까지 시뮬에 실패했다면 그냥 리턴
		if (k == 3) return;
		// 시뮬레이션에서 실패했으므로 다음 재귀 실행
		for (int i=0; i<h; i++) {
			for (int j=0; j<n-1; j++) {
				// 선을 추가로 그을 수 있는 자리 탐색
				if (!isLine[i][j] && isImpossible[i][j] == 0) {
					isLine[i][j] = true;
					if (j!=0) isImpossible[i][j-1] += 1;
					if (j!=n-2) isImpossible[i][j+1] += 1;
					func(k+1);
					isLine[i][j] = false;
					if (j!=0) isImpossible[i][j-1] -= 1;
					if (j!=n-2) isImpossible[i][j+1] -= 1;
				}
			}
		}
	}
	// 현재 가로선 배열을 입력하면 조작이 성공했는지 boolean으로 반환하는 함수
	private static boolean simulation(boolean[][] line) {
		for (int i=0; i<n; i++) {
			int[] cur = {0, i}; // 사다리를 타고 내려갈 포인터
			while(cur[0] != h) {
				if (cur[1]!=0 && line[cur[0]][cur[1]-1]) cur[1] -= 1;
				else if (cur[1]!=n-1 && line[cur[0]][cur[1]]) cur[1] += 1;
				cur[0] += 1;
			}
			if (cur[1] != i) return false;
		}
		return true;
	}
}