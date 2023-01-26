import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 감시
 * 1. 처음 입력받을 때 모든 CCTV의 종류와 위치정보를 함께 저장, 맵 복사해서 사용
 * 2. CCTV 종류마다 확인해야 하는 방향 배열 미리 만들기(회전까지 포함)
 * 3. 모든 경우의 수에 대해 CCTV가 관찰한 경로 갱신
 * 4. 마지막 CCTV 까지 경로를 갱신했다면, 사각지대의 크기를 계산하여 갱신
 */
public class BOJ_15683 {
	
	static int[][] dirsX = {{},
			{1,0,-1,0},
			{1,-1,0,0},
			{0,1,0,-1},
			{-1,0,1,0},
			{1,0,-1,0}};
	static int[][] dirsY = {{},
			{0,-1,0,1},
			{0,0,1,-1},
			{-1,0,1,0},
			{0,-1,0,1},
			{0,1,0,-1}};
	
	static int[] sight = {0,1,2,2,3,4};
	static int[][] cctv = new int[9][3];		// cctv[i] = {cctv_i 행, cctv_i 열, cctv_i 종류}
	static int cnt = 0;							// cctv의 개수
	
	static int n, m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(0 < map[i][j] && map[i][j] < 6) {
					cctv[cnt++] = new int[] {i, j, map[i][j]};
				}
			}
		}
		
		int minArea = bt(0, map);
		System.out.println(minArea);
	}
	
	static int bt(int k, int[][] map) {
		
		if(k > cnt) {
			int area = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(map[i][j] == 0) area++;
				}
			}
			return area;
		}
		
		int minArea = Integer.MAX_VALUE;
		
		int curX = cctv[k][0];
		int curY = cctv[k][1];
		int type = cctv[k][2];
		int watch = sight[type];
		
		int[][] nMap = new int[n][];
		

		int dir = 0;
		// cctv의 모든 회전에 대해 볼 수 있는 곳 체크
		while(dir < 4) {
			
			// 이전에 넘겨받은 시야 상황 복사
			for(int i = 0; i < n; i++) {
				nMap[i] = Arrays.copyOf(map[i], m);
			}
			
			// cctv 시야 방향에 대해 볼 수 있는 곳 모두 체크
			for(int w = 0; w < watch; w++) {
				int dx = dirsX[type][(dir + w) % 4];
				int dy = dirsY[type][(dir + w) % 4];
				
				int nx = curX + dx;
				int ny = curY + dy;
				
				while(!(nx < 0 || nx >= n || ny < 0 || ny >= m)) {
					if(nMap[nx][ny] == 6) break;
					if(nMap[nx][ny] == 0) nMap[nx][ny] = -1;
					nx += dx;
					ny += dy;
				}
			}
			
			// 사각지대의 개수가 더 적은 것을 minArea에 저장
			minArea = Math.min(minArea, bt(k + 1, nMap));
			dir += type != 2 ? 1 : 2;
		}
		
		
		
		return minArea;
	}

}

