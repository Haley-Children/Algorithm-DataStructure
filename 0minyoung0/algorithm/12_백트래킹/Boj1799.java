import java.io.*;
import java.util.*;

public class Boj1799 {
	static int n, ans1, ans2;
	static boolean[][] isPossible;
	static int[][] dupCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 체스판의 크기
		n = Integer.parseInt(br.readLine());
		
		// 비숍을 놓을 수 있는 자리인지 여부 저장
		isPossible = new boolean[n][n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				if (st.nextToken().equals("1")) {
					isPossible[i][j] = true;
				}
			}
		}
		
		// 다른 비숍에 의해 놓일 수 없는 제약이 생길때마다 카운트를 올리는 배열
		dupCnt = new int[n][n];
		
		func(0, 0);
		func(1, 0);
		
		System.out.println(ans1 + ans2);
	}
	
	// 체스판에 비숍을 놓을 수 있는 최대 값을 구해서 ans에 넣어주는 메소드
	// x+y=k인 대각선 판단, nBishop개의 비숍이 놓인 상태
	private static void func(int k, int nBishop){
		// x+y = 2n-3 or 2n-2인 대각선까지 카운팅 했다면
		if (k == 2*n-1) {
			// ans1 최신화 후 리턴
			ans1 = Math.max(ans1, nBishop);
			return;
		} else if (k == 2*n) {
			// ans2 최신화 후 리턴
			ans2 = Math.max(ans2, nBishop);
			return;
		}
		
		for (int x=0; x<=k; x++) {
			// 비숍 놓기 가능한지 확인
			if (x>=0 && k-x>=0 && x<n && k-x<n) {
				if (isPossible[x][k-x] && dupCnt[x][k-x] == 0) {
					// 비숍 놓기
					// 차가 2x-k인 지점에 카운트 증가
					for (int i=0; i<n; i++) {
						if (i>=0 && i-2*x+k>=0 && i<n && i-2*x+k<n) {
							dupCnt[i][i-2*x+k]++;
						}
					}
					
					// 다음 대각선 확인을 위해 재귀 호출
					func(k+2, nBishop+1);
					
					// 비숍 빼기
					// 차가 2x-k인 지점에 카운트 감소
					for (int i=0; i<n; i++) {
						if (i>=0 && i-2*x+k>=0 && i<n && i-2*x+k<n) {
							dupCnt[i][i-2*x+k]--;
						}
					}
				}
			}
		}
		// 비숍을 놓지 않은 케이스에 대해서도 다음 대각선 확인을 위해 재귀 호출
		func(k+2, nBishop);
	}
}
