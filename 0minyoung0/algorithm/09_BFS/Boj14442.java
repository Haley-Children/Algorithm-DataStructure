import java.io.*;
import java.util.*;

public class Boj14442 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // map 사이즈
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // 부술 수 있는 벽의 개수
        int k = Integer.parseInt(st.nextToken());
        
        // n, m이 모두 1일때 예외처리
        if (n == 1 && m == 1) {
        	System.out.println(1);
        	return;
        }
        
        // map 정보
        boolean[][] isWall = new boolean[n][m];
        for (int i=0; i<n; i++) {
        	String s = br.readLine();
        	for (int j=0; j<m; j++) {
        		if (s.charAt(j) == '1') {
        			isWall[i][j] = true;
        		}
        	}
        }
        
        // 거리 정보
        int[][][] dis = new int[n][m][k+1];
        
        // 델타 배열 선언
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        
        // BFS를 위한 초기 작업 - 큐 선언, 초기값 처리 후 큐에 넣기
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, k});
        dis[0][0][k] = 1;
        
        // BFS 돌리면서 찾으면 바로 출력 후 리턴
        while (!q.isEmpty()) {
        	int[] cur = q.poll();
        	for (int dir=0; dir<4; dir++) {
        		int nx = cur[0] + dx[dir];
        		int ny = cur[1] + dy[dir];
        		
        		// out of index
        		if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
        		
        		// 도착한 경우 -> 출력 후 리턴
        		if (nx == n - 1 && ny == m - 1) {
        			System.out.println(dis[cur[0]][cur[1]][cur[2]] + 1);
        			return;
        		}
        		
        		// 그냥 이동할 수 있는 경우
        		if (!isWall[nx][ny] && dis[nx][ny][cur[2]] == 0) {
        			q.offer(new int[] {nx, ny, cur[2]});
        			dis[nx][ny][cur[2]] = dis[cur[0]][cur[1]][cur[2]] + 1;
        		}
        		
        		// 벽을 부수고 이동할 수 있는 경우
        		else if (cur[2] > 0 && isWall[nx][ny] && dis[nx][ny][cur[2]-1] == 0) {
        			q.offer(new int[] {nx, ny, cur[2]-1});
        			dis[nx][ny][cur[2]-1] = dis[cur[0]][cur[1]][cur[2]] + 1;
        		}
        	}
        }
        
        // BFS 끝나도 도착 못했으면 -1 출력
        System.out.println(-1);
    }
}