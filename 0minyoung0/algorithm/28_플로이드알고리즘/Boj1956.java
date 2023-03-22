import java.io.*;
import java.util.*;

public class Boj1956 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 마을의 개수
		int v = Integer.parseInt(st.nextToken());
		// 도로의 개수
		int e = Integer.parseInt(st.nextToken());
		
		// 도로 정보
		int[][] road = new int[v+1][v+1];
		for (int i=1; i<=v; i++) {
			Arrays.fill(road[i], 4000001);
		}
		for (int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			road[a][b] = c;
		}
		
		// 플로이드 알고리즘
		for (int i=1; i<=v; i++) {
			for (int s=1; s<=v; s++) {
				if (i == s) continue;
				for (int t=1; t<=v; t++) {
					if (i == t) continue;
					if (road[s][t] > road[s][i] + road[i][t]) {
						road[s][t] = road[s][i] + road[i][t];
					}
				}
			}
		}
		
		// 답 찾기
		int ans = 4000001;
		for (int i=1; i<=v; i++) {
			if (ans > road[i][i]) ans = road[i][i];
		}
		
		// 불가능인 경우
		if (ans == 4000001) ans = -1;
		
		// 답 출력
		System.out.println(ans);
	}
}
