import java.io.*;
import java.util.*;

public class Boj3197 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 행, 열
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		// 백조의 위치
		int[][] pos = new int[2][2];
		pos[0][0] = -1;
		
		// 탐색할 물의 좌표를 저장할 큐
		Queue<int[]> water = new ArrayDeque<>();
		
		// 호수 정보
		char[][] lake = new char[R][C];
		for (int i=0; i<R; i++) {
			String s = br.readLine();
			for (int j=0; j<C; j++) {
				lake[i][j] = s.charAt(j);
				
				// 백조인 경우
				if (lake[i][j] == 'L') {
					
					// 첫번째 백조인 경우
					if (pos[0][0] == -1) {
						pos[0][0] = i;
						pos[0][1] = j;
					}
					// 두번째 백조인 경우
					else {
						pos[1][0] = i;
						pos[1][1] = j;
					}
					lake[i][j] = '.';
				}
				
				// 물인 경우
				if (lake[i][j] == '.') {
					water.offer(new int[] {i, j});
				}
			}
		}
		
		// 방문 여부를 체크할 boolean 배열
		boolean[][] vis = new boolean[R][C];
		vis[pos[0][0]][pos[0][1]] = true;
		
		// 첫번째 백조를 큐에 넣기
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {pos[0][0], pos[0][1]});
		
		// 델타배열
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		// 시뮬레이션
		for (int ans=0; ; ans++) {
			
			// 탐색시 만나는 얼음을 저장할 큐
			Queue<int[]> ice = new ArrayDeque<>();
			
			// BFS로 탐색
			while (!q.isEmpty()) {

				int[] cur = q.poll();
				
				for (int dir=0; dir<4; dir++) {
					int nx = cur[0] + dx[dir];
					int ny = cur[1] + dy[dir];
					
					// out of index
					if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
					
					// 두번째 백조를 만난 경우
					if (nx == pos[1][0] && ny == pos[1][1]) {
						
						// 답 출력 후 리턴
						System.out.println(ans);
						return;
					}
					
					// 이미 방문한 경우
					if (vis[nx][ny]) continue;
					
					// 얼음을 만난 경우
					if (lake[nx][ny] == 'X') {
						vis[nx][ny] = true;
						ice.offer(new int[] {nx, ny});
						continue;
					}
					
					// 물을 만난 경우
					vis[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
				
			}
			
			// ice 큐를 q에 할당
			q = ice;
			
			// water 큐의 주변에 있는 얼음 녹이기
			int size = water.size();
			while (size-- > 0) {
				int[] cur = water.poll();
				
				for (int dir=0; dir<4; dir++) {
					int nx = cur[0] + dx[dir];
					int ny = cur[1] + dy[dir];
					
					// out of index
					if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
					
					// 얼음이면 녹이고 water 큐에 저장
					if (lake[nx][ny] == 'X') {
						lake[nx][ny] = '.';
						water.offer(new int[] {nx, ny});
					}
				}
			}
		}
	}
}
