import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 뿌요뿌요
 * 1. 각 색깔마다 bfs 돌면서 연결된 같은 색이 4개 이상이면 터뜨리기
 * 2. 모든 색깔 확인 끝낸 후 뿌요 낙하시키기
 * 3. 다시 체크
 * 
 * 메서드
 * boom(BFS), fall
 */
public class BOJ_11559 {

	static final int row = 12;
	static final int col = 6;
	
	static char[][] map = new char[row][col];
	static char[] colors = {'R','G','B','P','Y'};
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		for(int i = 0; i < row; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int chain = 0;
		
		while(true) {
			boolean isBoomed = false;

			for(int color = 0; color < 5; color++) {
				isBoomed |= boom(colors[color]);
			}
			if(!isBoomed) break;
			
			chain++;
			fall();
		}
		
		System.out.println(chain);
		
	}
	
	static boolean boom(char color) {
		int[][] vis = new int[row][col];
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> toBoom = new LinkedList<>();
		
		boolean isBoomed = false;
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(map[i][j] == color && vis[i][j] == 0) {
					vis[i][j] = 1;
					int[] start = new int[] {i, j};
					q.add(start);
					toBoom.add(start);
					int count = 1;

					// 몇 개가 이어져있는지 확인
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						int curx = cur[0];
						int cury = cur[1];
						
						for(int dir = 0; dir < 4; dir++) {
							int nx = curx + dx[dir];
							int ny = cury + dy[dir];
							
							if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue;
							if(map[nx][ny] != color || vis[nx][ny] != 0) continue;
							
							vis[nx][ny] = 1;
							count++;
							int[] point = new int[] {nx, ny};
							q.add(point);
							toBoom.add(point);
						}
					}
					
					// 터뜨리기
					while(!toBoom.isEmpty()) {
						int[] point = toBoom.poll();
						if (count >= 4) {
							map[point[0]][point[1]] = '.';
							isBoomed = true;
						}		
					}
				}
			}
		}
		return isBoomed;
	}
	
	// 모든 열의 바닥부터 .을 가장 처음 만나는 블럭과 교환
	static void fall() {
		
		for(int j = 0; j < col; j++) {
			for(int i = row -1; i >= 0; i--) {
				if(map[i][j] == '.') {
					int r = i - 1;
					while(r >= 0) {
						if(map[r][j] != '.') {
							map[i][j] = map[r][j];
							map[r][j] = '.';
							break;
						}
						r--;
					}
				}
			}
		}
	}
}

