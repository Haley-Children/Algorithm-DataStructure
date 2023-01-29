// 시뮬레이션.boj14889;

import java.io.*;
import java.util.*;

public class Boj14889 {
	static int n;
	static int ans = Integer.MAX_VALUE;
	static int[][] stats;
	static boolean[] isPicked;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		stats = new int[n][n];
		isPicked = new boolean[n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				stats[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		func(0, new int[n], -1);
		System.out.println(ans);
	}
	// 백트래킹. k는 현재 뽑힌 사람의 수, pre는 이전 단계에서 뽑은 사람의 번호
	// arr 0번부터 n/2-1번까지 스타트팀, arr n/2번부터 n-1번까지 링크팀
	private static void func (int k, int[] curTeam, int pre) {
		// 스타트팀을 모두 뽑았다면 남은 사람을 모두 링크팀에 넣고 계산 후 리턴
		if (k == n/2) {
			// 여태 안뽑힌 n/2명을 링크팀에 집어넣기
			int index = n/2;
			for (int i=0; i<n; i++) {
				if (!isPicked[i]) {
					curTeam[index++] = i;
				}
			}
			// 팀 배정이 끝났으면 스탯 계산, stats[a][a]는 항상 0이므로 예외처리 안하고 그냥 합산함
			int temp = 0;
			for (int i=0; i<n/2; i++) {
				for (int j=0; j<n/2; j++) {
					temp += stats[curTeam[i]][curTeam[j]];
				}
			}
			for (int i=n/2; i<n; i++) {
				for (int j=n/2; j<n; j++) {
					temp -= stats[curTeam[i]][curTeam[j]];
				}
			}
			temp = Math.abs(temp);
			if (temp < ans) ans = temp;
			return;
		}
		// 스타트팀에 들어갈 팀원 뽑기
		// pre는 이전 레벨에서 뽑았던 사람의 번호를 인자로 넘겨줘서 중복 조합을 방지
		for (int i=pre+1; i<=n/2+k; i++) {
			if (!isPicked[i]) {
				isPicked[i] = true;
				curTeam[k] = i;
				func(k+1, curTeam, i);
				isPicked[i] = false;
			}
		}
	}
}