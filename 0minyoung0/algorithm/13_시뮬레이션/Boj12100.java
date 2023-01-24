// 시뮬레이션.boj12100;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj12100 {
	static int n, mx;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		int[][] board = new int[n][n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		func(board, 0);
		System.out.println(mx);
	}
	private static void func(int[][] arr, int level) {
		if (level == 5) {
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					if (arr[i][j] > mx) mx = arr[i][j];
				}
			}
			return;
		}
		for (int dir=0; dir<4; dir++) {
			// 어레이 하나 만들기
			int[][] newArr = new int[n][n];
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					newArr[i][j] = arr[i][j];
				}
			}
			// 해당 dir에 맞게 데이터 처리
			newArr = adjustArr(newArr, dir);
			// 호출 (새 어레이, level하나 올려서)
			func(newArr, level+1);
		}
	}
	private static int[][] adjustArr(int[][] arr, int dir){
		for (int i=0; i<n; i++) {
			int[][] temp = new int[2][n]; // [0][]에는 데이터, [1][]에는 합쳐진 값인지 여부 (0:안합쳐짐,1:합쳐짐)
			if (dir == 0) { // 위
				// for문으로 일단 한줄을 temp에 복사
				for (int j=0; j<n; j++) {
					temp[0][j] = arr[j][i];
				}
			}
			else if (dir == 1) { // 오른쪽
				for (int j=0; j<n; j++) {
					temp[0][j] = arr[i][n-j-1];
				}
			}
			else if (dir == 2) { // 아래
				for (int j=0; j<n; j++) {
					temp[0][j] = arr[n-j-1][i];
				}
			}
			else { // dir == 3 // 왼쪽
				for (int j=0; j<n; j++) {
					temp[0][j] = arr[i][j];
				}
			}
			// 연속해서 같은 숫자 있으면 합치기
			int pre = 0;
			for (int j=1; j<n; j++) {
				if (temp[0][pre] != 0 && temp[0][j] == temp[0][pre] && temp[1][pre] == 0) {
					temp[0][pre] *= 2;
					temp[0][j] = 0;
					temp[1][pre] = 1;
				}
				else if (temp[0][j] != 0) {
					pre = j;
				}
			}
			// temp 배열 앞쪽으로 몰아주기
			int idx = 0;
			for (int j=0; j<n; j++) {
				if (temp[0][j] != 0) temp[0][idx++] = temp[0][j];
			}
			for (int j=idx; j<n; j++) {
				temp[0][j] = 0;
			}
			// dir 방향으로 배열에 넣어주기
			if (dir == 0) {
				for (int j=0; j<n; j++) {
					arr[j][i] = temp[0][j];
				}
			}
			else if (dir == 1) {
				for (int j=0; j<n; j++) {
					arr[i][n-j-1] = temp[0][j];
				}
			}
			else if (dir == 2) {
				for (int j=0; j<n; j++) {
					arr[n-j-1][i] = temp[0][j];
				}
			}
			else {
				for (int j=0; j<n; j++) {
					arr[i][j] = temp[0][j];
				}
			}
		}
		return arr;
	}
}
