package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main_1926 { // 다시 풀었는데 틀림.. 고쳐야 함.
    static int[][] board, vis;
    static int[] dix = {1,0,-1,0};
    static int[] diy = {0, 1, 0, -1};
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        vis = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j]==0 || vis[i][j]==1) continue;
                int area = bfs(new Point(i,j));
                cnt++;
                max = Math.max(area, max);
            }
        }
        System.out.println(cnt);
        System.out.println(max);

    }

    private static int bfs(Point point) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(point);
        vis[point.x][point.y]=1;
        int area = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            area++;
            for (int i = 0; i < 4; i++) {
                int nx = dix[i]+cur.x;
                int ny = diy[i]+ cur.y;

                if (nx<0 || nx>=n || ny<0 || ny>=m) continue; // ny>="n"으로 실수함
                if (vis[nx][ny]==1 || board[nx][ny]==0) continue;
                queue.add(new Point(nx,ny));
                vis[nx][ny] =1;

            }
        }

        return area;
    }

}
