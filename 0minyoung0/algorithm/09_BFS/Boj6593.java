import java.io.*;
import java.util.*;

public class Boj6593 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int[] dx = {1,-1,0,0,0,0};
		int[] dy = {0,0,1,-1,0,0};
		int[] dz = {0,0,0,0,1,-1};
		
		String s = br.readLine();
		while (!s.equals("0 0 0")) {
			st = new StringTokenizer(s);
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// BFS를 위한 char 배열, int 배열, 좌표를 저장할 큐를 덱으로 선언
			char[][][] map = new char[l][r][c];
			int[][][] time = new int[l][r][c];
			Deque<int[]> queue = new ArrayDeque<>();
			
			// char배열에 맵 정보 받고 시작점을 큐에 넣음
			for (int i=0; i<l; i++) {
				for (int j=0; j<r; j++) {
					s = br.readLine();
					for (int k=0; k<c; k++) {
						map[i][j][k] = s.charAt(k);
						if (s.charAt(k) == 'S') {
							queue.offer(new int[] {i, j, k});
							time[i][j][k] = 1;
						}
					}
				}
				br.readLine();
			}
			
			boolean isTrapped = true;
			
			BFS: while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				for (int dir=0; dir<6; dir++) {
					int nx = cur[0] + dx[dir];
					int ny = cur[1] + dy[dir];
					int nz = cur[2] + dz[dir];
					
					// 인덱스 벗어나는 경우
					if (nx<0 || nx>=l || ny<0 || ny>=r || nz<0 || nz>=c) continue;
					
					// 탈출하는 경우
					if (map[nx][ny][nz] == 'E') {
						sb.append("Escaped in ").append(time[cur[0]][cur[1]][cur[2]]).append(" minute(s).\n");
						isTrapped = false;
						break BFS;
					}
					
					// 갈수 없는 칸이거나 이미 방문한 경우
					if (map[nx][ny][nz] == '#' || time[nx][ny][nz] != 0) continue;
					
					// 칸 방문하기
					queue.offer(new int[] {nx,ny,nz});
					time[nx][ny][nz] = time[cur[0]][cur[1]][cur[2]] + 1;
				}
			}
			
			// 탈출하지 못했다면
			if (isTrapped) {
				sb.append("Trapped!\n");
			}
			
			// 다음 루프를 위한 readLine
			s = br.readLine();
		}
		System.out.println(sb);
	}
}
