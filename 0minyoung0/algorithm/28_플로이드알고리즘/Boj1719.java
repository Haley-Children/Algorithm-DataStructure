import java.io.*;
import java.util.*;

public class Boj1719 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		final int INF = 100000000;
		
		// 집하장의 개수
		int n = Integer.parseInt(st.nextToken());
		// 집하장간 경로의 개수
		int m = Integer.parseInt(st.nextToken());
		
		// 경로 정보
		int[][] route = new int[n+1][n+1];
		for (int i=1; i<=n; i++) {
			Arrays.fill(route[i], INF);
		}
		
		// nxt 배열
		int[][] nxt = new int[n+1][n+1];
		
		// 경로 정보 저장
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			route[n1][n2] = t;
			route[n2][n1] = t;
			nxt[n1][n2] = n2;
			nxt[n2][n1] = n1;
		}
		
		// 플로이드 채우기
		for (int i=1; i<=n; i++) {
			for (int s=1; s<=n; s++) {
				if (i == s) continue;
				for (int d=1; d<=n; d++) {
					if (i == d) continue;
					if (route[s][d] > route[s][i] + route[i][d]) {
						route[s][d] = route[s][i] + route[i][d];
						nxt[s][d] = nxt[s][i];
					}
				}
			}
		}
		
		// nxt 배열 출력
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (i == j) {
					sb.append("- ");
					continue;
				}
				sb.append(nxt[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
