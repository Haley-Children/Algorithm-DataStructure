// 시뮬레이션.boj14502;

import java.io.*;
import java.util.*;

public class Boj14502 {
    static int[][] map;
    static int n,m,ans; // 가로, 세로, 답
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[][] virus;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        int vCount = 0;
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) vCount++;
            }
        }
        virus = new int[vCount][2];
        int temp = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (map[i][j] == 2) {
                    virus[temp][0] = i;
                    virus[temp++][1] = j;
                }
            }
        }
        func(0, -1, map);
        System.out.println(ans);
    }
    private static void func(int k, int pre, int[][] curMap) { // pre는 x,y 좌표 한번에 나타내야함 (xn+y꼴)
        if (k==3) {
            // bfs 돌려서 바이러스 퍼트리기
            Queue<int[]> q = new LinkedList<>();
            boolean[][] vis = new boolean[n][m];
            for (int i=0; i<virus.length; i++) {
                q.add(new int[] {virus[i][0], virus[i][1]});
                vis[virus[i][0]][virus[i][1]] = true;
            }
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                int curX = cur[0];
                int curY = cur[1];
                for (int dir=0; dir<4; dir++) {
                    int nx = curX + dx[dir];
                    int ny = curY + dy[dir];
                    if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
                    if (vis[nx][ny] || curMap[nx][ny]!=0) continue;
                    q.add(new int[] {nx,ny});
                    vis[nx][ny] = true;
                }
            }
            // 0 개수 세서 ans보다 크면 ans에 대입하기
            int temp = 0;
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    if (!vis[i][j] && curMap[i][j]==0) temp++;
                }
            }
            if (ans<temp) ans=temp;
            return;
        }
        for (int i=pre+1; i<n*m; i++) {
            int newX = i / m;
            int newY = i % m;
            if (curMap[newX][newY] == 0) {
                int[][] newMap = new int[n][m];
                for (int x=0; x<n; x++) {
                    for (int y=0; y<m; y++) {
                        newMap[x][y] = curMap[x][y];
                    }
                }
                newMap[newX][newY] = 1;
                func(k+1, i, newMap);
            }
        }
    }
}