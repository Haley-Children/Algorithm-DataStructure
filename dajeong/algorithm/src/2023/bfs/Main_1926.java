package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * // Integer.MIN_VALUE 로 두었었는데, 문제 조건상 그림이 없을 경우 max = 0이라고 했으므로 틀렸었다. 문제를 잘 읽자!!!
 */
class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main_1926 {
    static int[][] board, vis;
    static int[] dix = {1, 0, -1, 0};
    static int[] diy = {0, 1, 0, -1};
    static int n, m;

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
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0 || vis[i][j] != 0) continue;
                int area = bfs(new Point(i, j));
                cnt++;
                max = Math.max(area, max);
            }
        }
        System.out.println(cnt);
        System.out.println(max);

    }

    private static int bfs(Point point) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(point);
        vis[point.x][point.y] = 1;
        int area = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            area++;
            for (int i = 0; i < 4; i++) {
                int nx = dix[i] + cur.x;
                int ny = diy[i] + cur.y;

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (vis[nx][ny] == 1 || board[nx][ny] == 0) continue;
                queue.offer(new Point(nx, ny));
                vis[nx][ny] = 1;

            }
        }
        return area;
    }

}
