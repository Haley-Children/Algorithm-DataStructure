import java.io.*;
import java.util.*;

public class Boj11967 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 헛간의 크기
		int n = Integer.parseInt(st.nextToken());
		// 스위치 개수
		int m = Integer.parseInt(st.nextToken());
		
		// List[][]에 스위치 정보를 저장
		List<int[]>[][] lightSwitch = new List[n+1][n+1];
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				lightSwitch[i][j] = new ArrayList<>();
			}
		}
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			lightSwitch[x][y].add(new int[] {a, b});
		}
		
		// 불이 켜진 방을 표시하기 위한 boolean[][]
		boolean[][] lightOn = new boolean[n+1][n+1];
		
		// 불이 켜진 방의 개수를 셀 변수
		int ans = 0;
		
		// 방문한 방을 표시하기 위한 boolean[][]
		boolean[][] vis = new boolean[n+1][n+1];
		
		// BFS를 위한 Queue
		Queue<int[]> q = new ArrayDeque<>();
		
		// (1,1)에서부터 초기 조건
		lightOn[1][1] = true;
		ans++;
		vis[1][1] = true;
		q.offer(new int[] {1, 1});
		
		// BFS 탐색을 위한 델타배열
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		// BFS 탐색
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			// 스위치 켜기
			for (int[] x : lightSwitch[cur[0]][cur[1]]) {
				
				// 이미 켜져있으면 continue
				if (lightOn[x[0]][x[1]]) continue;
				
				lightOn[x[0]][x[1]] = true;
				ans++;
				
				// 불 켠 곳이 방문 했던 곳과 인접해있으면 방문표시 남기고 큐에 넣기
				for (int dir=0; dir<4; dir++) {
					int nx = x[0] + dx[dir];
					int ny = x[1] + dy[dir];
					if (nx<1 || nx>n || ny<1 || ny>n) continue;
					if (vis[nx][ny]) {
						vis[x[0]][x[1]] = true;
						q.offer(new int[] {x[0], x[1]});
						break;
					}
				}
			}
			
			// 주변 4방향 불 켜져 있으면 바로 방문하기
			for (int dir=0; dir<4; dir++) {
				int nx = cur[0] + dx[dir];
				int ny = cur[1] + dy[dir];
				
				// out of index
				if (nx<1 || nx>n || ny<1 || ny>n) continue;
				
				// 방문 한적 있는 경우
				if (vis[nx][ny]) continue;
				
				// 불 안켜져 있는 경우
				if (!lightOn[nx][ny]) continue;
				
				// 방문표시 남기고 큐에 넣기
				vis[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
		
		// 답 출력
		System.out.println(ans);
	}
}
