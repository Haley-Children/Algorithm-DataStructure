package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 치킨 배달
 * 1. 입력받을 때 치킨집 좌표 배열, 집 좌표 배열 만들기
 * 2. 각 M개의 치킨집을 뽑는 경우 마다 bfs
 * 3. 처음 만나면 치킨 거리 구해서 누적
 * 
 * 백트래킹? / bfs
 * isUsed / queue, visited
 */
public class Main_15686 {
	
	static int hCnt = 0;					// 집의 개수
	static int cCnt = 0;
	
	static int[][] chickn = new int[20][];
	static char[][] map;
	
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	static int n, m;
	static int minimum = Integer.MAX_VALUE;
	
	static Queue<int[]> q;
	static int[][] arr = new int[20][];
	static boolean[] isUsed = new boolean[20];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][n];
		
		// initialize
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == '1') hCnt++;
				else if(map[i][j] == '2') chickn[cCnt++] = new int[] {i, j};
			}
		}
		
		// 모든 경우의 수에 대해 bfs
		int minLength = bt(0, 0);
		System.out.println(minLength);
				
	}
	
	// M개의 치킨집을 선택하는 경우의 수마다 bfs로 최단거리 측정
	static int bt(int k, int index) {
		// M개를 선택하면 선택한 치킨집을 큐에 담아 bfs시작
		if(k == m) {
			q = new LinkedList<>();
			int dist[][] = new int[n][n];
			for(int i = 0; i < m; i++) {
				dist[arr[i][0]][arr[i][1]] = 1;
				q.add(arr[i]);
			}
			return bfs(dist);
		}
		
		for(int i = index; i < cCnt; i++) {
			if(!isUsed[i]) {
				arr[k] = chickn[i];
				isUsed[i] = true;
				minimum = Math.min(minimum ,bt(k + 1, i + 1));
				isUsed[i] = false;
			}
		}
		
		return minimum;
	}
	
	// bfs하면서 가장 처음 가정집을 만났을 때의 거리를 더해 준다.
	static int bfs(int[][] dist) {
		int totalLength = 0;
		int count = 0;

		while(!q.isEmpty() && count < hCnt) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if(dist[nx][ny] > 0) continue;
				
				dist[nx][ny] = dist[x][y] + 1;
				if(map[nx][ny] == '1') {
					count++;
					totalLength += dist[nx][ny] - 1;
					if(totalLength > minimum) break;
				}
				q.add(new int[] {nx, ny});
			}
		}
		
		return totalLength;
	}
	
}

