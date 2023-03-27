import java.io.*;
import java.util.*;

public class Boj21940 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 도시의 개수
		int n = Integer.parseInt(st.nextToken());
		// 도로의 개수
		int m = Integer.parseInt(st.nextToken());
		
		// 도로 정보
		int[][] road = new int[n+1][n+1];
		for (int i=1; i<=n; i++) {
			Arrays.fill(road[i], 1000000);
			road[i][i] = 0;
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			road[a][b] = t;
		}
		
		// 플로이드 알고리즘
		for (int i=1; i<=n; i++) {
			for (int s=1; s<=n; s++) {
				if (i==s) continue;
				for (int t=1; t<=n; t++) {
					if (i==t) continue;
					if (road[s][t] > road[s][i] + road[i][t]) {
						road[s][t] = road[s][i] + road[i][t];
					}
				}
			}
		}
		
		// 친구들의 총 인원
		int k = Integer.parseInt(br.readLine());
		
		// 친구들이 살고있는 도시 번호
		int[] c = new int[k];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<k; i++) {
			c[i] = Integer.parseInt(st.nextToken());
		}
		
		// 왕복 시간 계산
		int[][] time = new int[k][n+1];
		for (int i=0; i<k; i++) {
			for (int j=1; j<=n; j++) {
				time[i][j] = road[c[i]][j] + road[j][c[i]];
			}
		}
		
		// 답 찾기
		int Time = Integer.MAX_VALUE;
		city: for (int i=1; i<=n; i++) {
			int temp = 0;
			for (int j=0; j<k; j++) {
				if (time[j][i] == 1000000) continue city;
				temp = Math.max(temp, time[j][i]);
			}
			if (temp < Time) Time = temp;
		}
		city: for (int i=1; i<=n; i++) {
			int temp = 0;
			for (int j=0; j<k; j++) {
				if (time[j][i] == 1000000) continue city;
				temp = Math.max(temp, time[j][i]);
			}
			if (temp == Time) System.out.print(i + " ");
		}
		
	}
}
