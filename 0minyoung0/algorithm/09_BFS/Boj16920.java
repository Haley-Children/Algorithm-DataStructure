import java.io.*;
import java.util.*;

public class Boj16920 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 확장을 위한 델타 배열
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		
		// 격자판의 크기
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// 플레이어의 수
		int p = Integer.parseInt(st.nextToken());
		
		// 플레이어별 확장 칸 수
		int[] s = new int[p];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<p; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}
		
		// 플레이어별 성의 개수
		int[] ans = new int[p];
		
		// 플레이어별 큐 선언
		Queue<int[]>[] q = new ArrayDeque[p];
		for (int i=0; i<p; i++) {
			q[i] = new ArrayDeque<>();
		}
		
		// map 정보
		char[][] map = new char[n][m];
		for (int i=0; i<n; i++) {
			String row = br.readLine();
			for (int j=0; j<row.length(); j++) {
				map[i][j] = row.charAt(j);
				
				// 빈칸도 아니고 벽도 아니면 플레이어의 성
				if (map[i][j] != '.' && map[i][j] != '#') {
					
					// 해당하는 플레이어의 성의 개수 +1
					ans[map[i][j]-'1']++;
					
					// 해당하는 플레이어의 큐에 성 offer
					q[map[i][j]-'1'].offer(new int[] {i, j, 0});
				}
			}
		}
		
		// BFS를 멈추기 위한 boolean 선언
		boolean keepBFS = true;
		
		// BFS 시작
		while(keepBFS) {
			// boolean 변수를 false로 바꾸기
			keepBFS = false;
			
			// 각 플레이어마다 확장
			for (int i=0; i<p; i++) {
				Queue<int[]> temp = new ArrayDeque<>();
				
				while (!q[i].isEmpty()) {
					// 다음 확장을 위해 최근에 확장한 성 큐에서 뽑아오기
					int[] cur = q[i].poll();
					for (int dir=0; dir<4; dir++) {
						
						// 새로운 확장 후보
						int nx = cur[0] + dx[dir];
						int ny = cur[1] + dy[dir];
						
						// out of index
						if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
						// 빈칸이 아니라서 확장이 불가능 한 경우
						if (map[nx][ny] != '.') continue;
						
						// 확장시키기
						map[nx][ny] = (char)('1' + i);
						ans[map[nx][ny]-'1']++;
						
						// s[i]번째 확장이 아닌경우 플레이어 큐에 넣기
						if (cur[2] != s[i]-1) {
							q[i].offer(new int[] {nx, ny, cur[2]+1});
						}
						// s[i]번쨰 확장인 경우 temp 큐에 넣기
						else {
							temp.offer(new int[] {nx, ny, 0});
							// 다음 루프도 돌아야하므로 keepBFS boolean을 true로 변경
							keepBFS = true;
						}
					}
				}
				// 플레이어 큐에 temp 큐 주소 넘겨주기
				q[i] = temp;
			}
		}
		
		// 정답 출력
		for (int i=0; i<p; i++) {
			System.out.print(ans[i] + " ");
		}
	}
}
