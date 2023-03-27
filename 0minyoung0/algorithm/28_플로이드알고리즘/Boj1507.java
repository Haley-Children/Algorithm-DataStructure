import java.io.*;
import java.util.*;

public class Boj1507 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int INF = 10000000;
		
		// 도시의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 도로 정보
		int[][] road = new int[n+1][n+1];
		int[][] original = new int[n+1][n+1];
		for (int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=n; j++) {
				original[i][j] = Integer.parseInt(st.nextToken());
				road[i][j] = original[i][j];
			}
		}
		
		// 플로이드 되돌리기
		for (int i=1; i<=n; i++) {
			for (int s=1; s<=n; s++) {
				if (i == s) continue;
				for (int d=1; d<=n; d++) {
					if (i == d) continue;
					if (road[s][d] == road[s][i] + road[i][d]) {
						road[s][d] = INF;
					}
				}
			}
		}
		
		// 모든 도로의 시간의 합 계산
		int ans = 0;
		for (int i=1; i<=n; i++) {
			for (int j=i+1; j<=n; j++) {
				if (road[i][j] != INF) ans += road[i][j];
			}
		}
		
		// 플로이드 채우기
		for (int i=1; i<=n; i++) {
			for (int s=1; s<=n; s++) {
				if (i == s) continue;
				for (int d=1; d<=n; d++) {
					if (i == d) continue;
					if (road[s][d] > road[s][i] + road[i][d]) {
						road[s][d] = road[s][i] + road[i][d];
					}
				}
			}
		}
		
		// 원래 도로 정보와 비교
		for (int i=1; i<=n; i++) {
			for (int j=i+1; j<=n; j++) {
				// 다른 부분이 있으면 -1 출력 후 리턴
				if (original[i][j] != road[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		// 도로의 시간의 합 출력
		System.out.println(ans);
		
	}
}
