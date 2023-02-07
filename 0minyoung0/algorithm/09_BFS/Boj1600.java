// bfs.boj1600;

import java.io.*;
import java.util.*;

public class Boj1600 {
    static int[][] map;
    static int[][][] dis;
    static int[] dx = {1,-1,0,0,2,2,-2,-2,1,1,-1,-1};
    static int[] dy = {0,0,1,-1,1,-1,1,-1,2,-2,2,-2};
    static int k, w, h, ans;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        dis = new int[h][w][k+1];
        // 특정 칸에 더 늦게 도달하더라도 특수 이동 횟수가 더 많다면 결과가 달라질 수 있음
        for (int i=0; i<h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0,0,0});
        dis[0][0][0] = 1;
        while(!q.isEmpty()) {
        	int[] cur = q.poll();
        	int curX = cur[0];
        	int curY = cur[1];
        	int curK = cur[2];
        	// 매번 큐에서 뽑을 때 남은 특수 이동 횟수를 체크함 -> 0이면 그냥 4방향, 1이상이면 4+8방향
        	for (int dir=0; dir<4; dir++) {
        		int nx = curX + dx[dir];
        		int ny = curY + dy[dir];
        		int nk = curK;
        		if (nx<0 || nx>=h || ny<0 || ny>=w) continue;
        		if (map[nx][ny]==1 || dis[nx][ny][nk]>0) continue;
        		q.add(new int[] {nx,ny,nk});
        		dis[nx][ny][nk] = dis[curX][curY][curK] + 1;
        	}
        	if (curK < k) {
        		for (int dir=4; dir<12; dir++) {
        			int nx = curX + dx[dir];
            		int ny = curY + dy[dir];
            		// 8방향 이동시 dis의 3번째 인덱스를 1 증가 시켜서 저장함
            		int nk = curK + 1;
            		if (nx<0 || nx>=h || ny<0 || ny>=w) continue;
            		if (map[nx][ny]==1 || dis[nx][ny][nk]>0) continue;
            		q.add(new int[] {nx,ny,nk});
            		dis[nx][ny][nk] = dis[curX][curY][curK] + 1;
        		}
        	}
        }
        // bfs가 끝나면 dis[h-1][w-1][0]부터 dis[h-1][w-1][k]까지 탐색해서 0이 아닌애들 중 제일 작은거 ans에 대입
        ans = Integer.MAX_VALUE;
        for(int i=0; i<=k; i++) {
        	if (dis[h-1][w-1][i] > 0 && ans > dis[h-1][w-1][i]-1) {
        		ans = dis[h-1][w-1][i]-1;
        	}
        }
        // ans가 Integer.MAX_VALUE이면 도착 못한거니까 ans를 -1로 변경
        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        System.out.println(ans);
    }
}