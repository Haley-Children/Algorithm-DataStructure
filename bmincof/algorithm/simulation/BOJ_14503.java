import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 로봇 청소기
 * 1. 현재 위치 청소 (2로 마크)
 * 2. 현재 보고 있는 방향 기준 왼쪽 탐색
 * 3. 0이면 왼쪽으로 회전 후 전진 후 1번 부터
 * 4. 왼쪽이 벽이거나 이미 청소 했으면 2번 부터
 * 5. 모든 방향이 벽이거나 청소된 곳이면 보고 있는 방향 그대로 한 칸 후진 후 2번
 * 6. 모든 방향 청소된 곳이거나 벽이면서 뒤쪽도 벽인 경우 정지
 */
public class BOJ_14503 {

	static int direction[] = {0,1,2,3};			// 북 동 남 서 == 회전 방향과 반대, dx, dy의 시작인덱스
	static int[] dx = {0, -1, 0, 1};			// 보고있는 방향에서 왼쪽 (서 북 동 남)
	static int[] dy = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		boolean isMovable = true;
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int cDir = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		map[x][y] = 10;
		while(isMovable) {
									// 현재 위치 청소 후 체크
			boolean isMoved = false;
			
			// 왼쪽으로 한 번 씩 회전하면서 움직일 수 있는지 확인
			for(int dir = 0; dir < 4; dir++) {
				int turn = (cDir - dir + 4) % 4;						// 서쪽으로 회전할 때 마다 dx, dy의 방향
				
				int nx = x + dx[turn];
				int ny = y + dy[turn];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if(map[nx][ny] == 0) {
					cDir = (cDir + 3 - dir) % 4;						// 회전하고 바라보는 방향은 처음 바라본 방향 + 방향 수 - 회전한 수
					map[nx][ny] = map[x][y] + 1;
					x = nx;
					y = ny;
					isMoved = true;
					break;
				} 
				else {
					continue;
				}
			}
			if(!isMoved) {
				int back = (cDir + 1 + 2) % 4;							// 마지막 방향을 체크하고 갈 수 없으면 회전하지 않으므로 총 3번 회전한 상태
				int nx = x + dx[back];
				int ny = y + dy[back];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 1) isMovable = false;
				else {
					x = nx;
					y = ny;
				}
			}
		}	
		
		int count = 0;
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] != 1 && map[i][j] != 0) count++;
			}
		}
		System.out.println(count);
		
	}
		
}

