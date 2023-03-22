import java.io.*;
import java.util.*;

public class Boj1261 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		// 미로의 가로 크기
		int m = Integer.parseInt(st.nextToken());
		// 미로의 세로 크기
		int n = Integer.parseInt(st.nextToken());
		
		// 시작점이 종료 점인 경우 : 조기 종료
		if (m == 1 && n == 1) {
			System.out.println(0);
			return;
		}
		
		// 미로 벽 정보
		char[][] wall = new char[n+1][m+1];
		for (int i=1; i<=n; i++) {
			String s = br.readLine();
			for (int j=1; j<=m; j++) {
				wall[i][j] = s.charAt(j-1);
			}
		}
		
		// 미로 방문 여부
		boolean[][] vis = new boolean[n+1][m+1];
		vis[1][1] = true;
		
		// 0-1 BFS를 위한 덱
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {0, 1, 1});
		
		// 0-1 BFS
		while (!dq.isEmpty()) {
			// 덱에서 하나 뽑기
			int[] cur = dq.poll();
			
			// 델타 탐색으로 다음 좌표 구하기
			for (int dir=0; dir<4; dir++) {
				int nx = cur[1] + dx[dir];
				int ny = cur[2] + dy[dir];
				
				// out of index
				if (nx < 1 || nx > n || ny < 1 || ny > m) continue;
				
				// 방문 한 적 있는 칸이면 continue;
				if (vis[nx][ny]) continue;
				
				// 종료 지점에 도달했으면 출력 후 BFS 종료
				if (nx == n && ny == m) {
					System.out.println(cur[0]);
					return;
				}
				
				// 벽이 없다면 덱의 front에 넣기
				if (wall[nx][ny] == '0') {
					dq.offerFirst(new int[] {cur[0], nx, ny});
				}
				// 벽이 있다면 덱의 back에 넣기
				else {
					dq.offer(new int[] {cur[0] + 1, nx, ny});
				}
				
				// 방문 여부 표시
				vis[nx][ny] = true;
			}
		}
	}
}
