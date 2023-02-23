import java.io.*;
import java.util.*;

public class Boj17281 {
	static int n, ans;
	static int[][] result;
	static int[] player; // 0-index
	static boolean[] isPicked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 이닝 수
		n = Integer.parseInt(br.readLine());
		
		// 이닝 별 각 선수가 얻는 결과
		result = new int[n+1][10];
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=9; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// i번 타자 결정
		player = new int[9];
		player[3] = 1; // 1번선수가 4번타자
		
		// 선택된 타자 표시하기 위한 boolean 배열
		isPicked = new boolean[10];
		isPicked[1] = true;
		
		playGame(0);
		
		System.out.println(ans);
	}
	
	private static void playGame(int k) {
		// 8명의 선수를 모두 배치한 경우
		if (k == 8) {
			// 점수 계산 시뮬레이션
			// 현재 타석에 들어설 플레이어
			int curP = 0;
			// 점수를 저장할 변수
			int score = 0;
			for (int inning=1; inning<=n; inning++) {
				// 아웃 카운트
				int out = 0;
				// 주자 유무 여부
				boolean[] isRunner = new boolean[4];
				while(out != 3) {
					// out일때
					if (result[inning][player[curP]] == 0) {
						out++;
					}
					
					else {
						// 안타 친 타자
						isRunner[0] = true;
						for (int i=0; i<result[inning][player[curP]]; i++) {
							// 점수 내기
							if (isRunner[3]) {
								isRunner[3] = false;
								score++;
							}
							// 주자 진행시키기
							for (int j=2; j>=0; j--) {
								if (isRunner[j]) {
									isRunner[j] = false;
									isRunner[j+1] = true;
								}
							}
						}
					}
					
					// 다음 타자 준비
					curP = (curP + 1) % 9;
				}
			}
			// 점수가 ans보다 크면 갱신 후 게임 종료
			ans = Math.max(ans, score);
			return;
		}
		
		// 선수 배치가 덜 끝난 경우
		// 1,2,3번 타자 뽑는 경우`
		if (k <= 2) {
			for (int i=2; i<=9; i++) {
				// i번 선수가 뽑히지 않은 경우
				if (!isPicked[i]) {
					// 뽑았다고 표시
					player[k] = i;
					isPicked[i] = true;
					// 다음 선수 뽑기 위해 재귀 호출
					playGame(k+1);
					// 뽑았다는 표시 제거
					isPicked[i] = false;
				}
			}
		}
		// 5,6,7,8,9번 타자 뽑는 경우
		else { // k >= 3
			for (int i=2; i<=9; i++) {
				// i번 선수가 뽑히지 않은 경우
				if (!isPicked[i]) {
					// 뽑았다고 표시
					player[k+1] = i;
					isPicked[i] = true;
					// 다음 선수 뽑기 위해 재귀 호출
					playGame(k+1);
					// 뽑았다는 표시 제거
					isPicked[i] = false;
				}
			}
		}
	}
}
