import java.io.*;
import java.util.*;

public class Boj16236 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	// 공간의 크기
    	int n = Integer.parseInt(br.readLine());
    	
    	// 아기상어의 좌표
    	int[] s_pos = {-1, -1};
    	// 각 물고기의 크기별 마리 수 (1-indexed)
    	int[] fishesCnt = new int[7];
    	
    	// 공간의 상태
    	int[][] space = new int[n][n];
    	for (int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j=0; j<n; j++) {
    			space[i][j] = Integer.parseInt(st.nextToken());
    			if (space[i][j] == 9) {
    				s_pos = new int[] {i, j};
    			}else if (space[i][j] != 0) {
    				fishesCnt[space[i][j]]++;
    			}
    		}
    	}
    	
    	// 아기상어의 크기
    	int s_size = 2;
    	// 아기상어가 먹은 물고기 수 (크기만큼 모으면 크기 +1, exp는 0으로 초기화)
    	int exp = 0;
    	
    	// 아기상어가 먹을 수 있는 크기의 물고기 수
    	int edible = fishesCnt[1];
    	
    	// 아기상어가 움직인 시간
    	int time = 0;
    	
    	// BFS탐색을 위한 델타 배열 선언
    	int[] dx = {-1,1,0,0};
    	int[] dy = {0,0,-1,1};
    	
    	// 먹을 수 있는 크기의 물고기가 공간에 있다면
    	while (edible != 0) {
    		// BFS에 사용할 Queue선언
    		Queue<int[]> q = new ArrayDeque<>();
    		// BFS에 사용할 거리 배열 선언
    		int[][] dis = new int[n][n];
    		
    		// 아기상어의 위치를 큐에 넣기
    		q.offer(s_pos);
    		// 아기상어의 위치를 거리 1로 저장
    		dis[s_pos[0]][s_pos[1]] = 1;
    		
    		// 먹으러 갈 물고기의 후보를 저장할 current_target 선언
    		// int[]에는 거리, x좌표, y좌표 저장
    		int[] current_target = new int[] {Integer.MAX_VALUE, -1, -1};
    		
    		// BFS 탐색
    		while(!q.isEmpty()) {
    			int[] cur = q.poll();
    			// 만약 cur의 dis값이 current_target의 거리와 같거나 커지면 break
    			if (current_target[1] != -1 && dis[cur[0]][cur[1]] >= current_target[0]) {
    				break;
    			}
    			
    			// 상하좌우 4방향 탐색
    			for (int dir=0; dir<4; dir++) {
    				int nx = cur[0] + dx[dir];
    				int ny = cur[1] + dy[dir];
    				// out of index
    				if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
    				
    				// 방문한 적 있거나 s_size보다 크면
    				if (dis[nx][ny] > 0 || space[nx][ny] > s_size) continue;
    				
    				// 먹을 수 있는 물고기면 current_target과 비교 저장
    				if (space[nx][ny] != 0 && space[nx][ny] < s_size) {
    					// current_target이 없었거나
    					// x좌표가 더 작거나
    					// x좌표는 같고 y좌표가 더 작으면 갱신
    					if (current_target[1] == -1 || current_target[1] > nx 
    							|| (current_target[1] == nx && current_target[2] > ny)) {
    							current_target = new int[] {dis[cur[0]][cur[1]] + 1, nx, ny};
    					}
    					continue;
    				}
    				
    				// 먹을수는 없지만 지나갈 수 있는 위치는 큐에 저장
    				q.offer(new int[] {nx, ny});
    				dis[nx][ny] = dis[cur[0]][cur[1]] + 1;
    			}
    		}
    		
    		// current_target이 없는 상태로 BFS 탐색이 끝났다면 종료
    		if (current_target[1] == -1) {
    			break;
    		}
    		
    		// current_target으로 이동해서 먹기
    		// 시간 증가
    		time += current_target[0] - 1;
    		// 아기 상어 이동
    		space[s_pos[0]][s_pos[1]] = 0;
    		s_pos = new int[] {current_target[1], current_target[2]};
    		// 먹은 물고기 제거
    		space[s_pos[0]][s_pos[1]] = 0;
    		edible--;
    		// 먹은 물고기 카운트 증가
    		exp++;
    		
    		// 물고기를 자신의 크기만큼 먹었다면
    		if (s_size == exp) {
    			s_size++;
    			exp=0;
    			if (s_size <= 7) {
    				edible += fishesCnt[s_size-1];
    			}
    		}
    	}
    	
    	// 총 시간 출력
    	System.out.println(time);
    }
}