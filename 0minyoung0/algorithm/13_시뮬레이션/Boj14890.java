// 시뮬레이션.boj14890;

import java.io.*;
import java.util.*;

public class Boj14890 {
	static int n, l;
	static int ans;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 지나갈 수 있는 길의 개수를 세서 ans에 넣어주기
		int[] temp = new int[n];
		for (int i=0; i<n; i++) {
			func(map[i]); // 가로
			for (int j=0; j<n; j++) {
				temp[j] = map[j][i];
			}
			func(temp); // 세로
		}
		System.out.println(ans);
	}
	private static void func(int[] arr) {
		// 경사로가 해당 칸에 이미 놓여있는지 파악하기 위한 배열
		boolean[] isRunway = new boolean[n];
		int pre = arr[0];
		// 이전칸과 다음칸을 비교하며 탐색
		for (int i=1; i<n; i++) {
			// 인접한 두 칸의 높이 차이가 1보다 큰 경우 -> 리턴
			if (Math.abs(pre-arr[i]) > 1) return;
			// 인접한 두 칸의 높이 차이가 1인 경우 -> 경사로 놓을 수 있는지 판단
			if (Math.abs(pre-arr[i]) == 1) {
				// 높이가 1 낮아지는 경우
				if (pre > arr[i]) {
					// 범위를 벗어나는 경우 -> 리턴
					if (i+l-1 >= n) return;
					// 경사로를 놓을 공간의 높이가 일정하지 않은 경우 -> 리턴
					// 경사로를 놓을 공간에 경사로가 이미 놓여있는 경우 -> 리턴
					for (int j=i; j<i+l; j++) {
						if (arr[j] != arr[i]) return;
						if (isRunway[j]) return;
					}
					// 아무 문제 없으니까 경사로를 놓음
					for (int j=i; j<i+l; j++) {
						isRunway[j] = true;
					}
				}
				// 높이가 1 높아지는 경우
				else if (pre < arr[i]) {
					// 범위를 벗어나는 경우 -> 리턴
					if (i-l < 0) return;
					// 경사로를 놓을 공간의 높이가 일정하지 않은 경우 -> 리턴
					// 경사로를 놓을 공간에 경사로가 이미 놓여있는 경우 -> 리턴
					for (int j=i-1; j>=i-l; j--) {
						if (arr[j] != arr[i-1]) return;
						if (isRunway[j]) return;
					}
					// 아무 문제 없으니까 경사로를 놓음
					for (int j=i-1; j>=i-l; j--) {
						isRunway[j] = true;
					}
				}
			}
			// 다음 탐색을 위해 pre에 현재 탐색한 칸을 저장
			pre = arr[i];
		}
		// 리턴 안됐으면 문제 없이 경사로가 놓인 것, ans 1 증가
		ans++;
	}
}
