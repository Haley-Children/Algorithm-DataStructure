import java.io.*;
import java.util.*;

public class Boj11780 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 도시의 개수
		int n = Integer.parseInt(br.readLine());
		
		// 버스의 개수
		int m = Integer.parseInt(br.readLine());
		
		// 버스 정보를 저장할 배열
		int[][] bus = new int[n+1][n+1];
		// 경로 복원을 위한 nxt 배열
		int[][] nxt = new int[n+1][n+1];

		// 버스 정보
		for (int i=1; i<=n; i++) {
			Arrays.fill(bus[i], 1000000000);
		}
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (bus[a][b] > c) {
				bus[a][b] = c;
				nxt[a][b] = b;
			}
		}
		
		
		// 플로이드 알고리즘
		for (int i=1; i<=n; i++) {
			// s->t를 s->i->t로 갱신 가능한지 확인
			for (int s=1; s<=n; s++) {
				for (int t=1; t<=n; t++) {
					if (i == s || i == t || s == t) continue;
					if (bus[s][t] > bus[s][i] + bus[i][t]) {
						bus[s][t] = bus[s][i] + bus[i][t];
						nxt[s][t] = nxt[s][i];
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		// 도시 i에서 도시 j로 가는 최소 비용
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				// 불가능인 경우는 0으로 바꾸기
				if (bus[i][j] == 1000000000) {
					sb.append("0 ");
				}else {
					sb.append(bus[i][j] + " ");
				}
			}
			sb.append("\n");
		}
		
		// 도시 i에서 도시 j로 가는 경로
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				// 불가능한 경우
				if (nxt[i][j] == 0) {
					sb.append("0\n");
					continue;
				}
				
				// sb2에 i부터 j까지 경로를 저장
				int cnt = 1;
				StringBuilder sb2 = new StringBuilder();
				int cur = i;
				while (cur != j) {
					cnt++;
					sb2.append(cur + " ");
					cur = nxt[cur][j];
				}
				sb2.append(j + "\n");
				
				// sb에 도시의 수와 경로를 저장
				sb.append(cnt + " " + sb2);
			}
		}
		
		// 답 출력
		System.out.println(sb);
	}
}
