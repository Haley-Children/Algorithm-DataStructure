import java.io.*;
import java.util.*;

public class Boj16234 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        
        // 땅 크기
        int n = Integer.parseInt(st.nextToken());
        // 두 나라 인구 차이가 l이상 r이하라면 국경열림
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        
        // 인구수를 배열에 저장
        int[][] pop = new int[n][n];
        for (int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=0; j<n; j++) {
        		pop[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 인구 이동이 며칠동안 발생하는지 저장할 변수
        int day = -1;
        
        // while문을 제어하기 위한 종료조건 boolean
        boolean terminated = false;
        
        // BFS 탐색에 사용할 Queue를 Deque으로 선언
        Deque<int[]> q = new ArrayDeque<>();
        // 인구수를 평균으로 맞춰주기 위한 Queue를 Deque으로 선언
        Deque<int[]> q2 = new ArrayDeque<>();
        
        while (!terminated) {
        	day++;
        	terminated = true;
        	
        	// BFS 탐색에 사용할 방문 여부 판단 boolean 배열
        	boolean[][] vis = new boolean[n][n];
        	for (int i=0; i<n; i++) {
        		for (int j=0; j<n; j++) {
        			// 방문한적 없는 나라라면 BFS 시작
        			if (!vis[i][j]) {
        				// 연합의 인구수를 합칠 변수 선언
        				int totalPop = pop[i][j];
        				
        				q.offer(new int[] {i,j});
        				q2.offer(new int[] {i,j});
        				vis[i][j] = true;
        				while(!q.isEmpty()) {
        					int[] cur = q.poll();
        					for (int dir=0; dir<4; dir++) {
        						int nx = cur[0] + dx[dir];
        						int ny = cur[1] + dy[dir];
        						// out of index
        						if (nx<0 || nx>=n || ny<0 || ny>=n) continue;
        						// 이미 방문 한 경우
        						if (vis[nx][ny]) continue;
        						// 국경 열리는 조건이 안 맞는 경우
        						if (Math.abs(pop[cur[0]][cur[1]] - pop[nx][ny]) < l
        						|| Math.abs(pop[cur[0]][cur[1]] - pop[nx][ny]) > r) continue;
        						// 국경 열리는 경우
        						totalPop += pop[nx][ny];
        						q.offer(new int[] {nx, ny});
        						q2.offer(new int[] {nx, ny});
        						vis[nx][ny] = true;
        					}
        				}
        				
        				// q2에 저장된 모든 연합국의 인구수를 맞춰줌
        				int newPop = totalPop / q2.size();
        				while (!q2.isEmpty()) {
        					// 인구 변동이 있었으면
        					if (pop[q2.peek()[0]][q2.peek()[1]] != newPop) {
        						pop[q2.peek()[0]][q2.peek()[1]] = newPop;
        						terminated = false;
        					}
        					q2.poll();
        				}
        			}
        		}
        	}
        }
        
        // 답 출력
        System.out.println(day);
    }
}