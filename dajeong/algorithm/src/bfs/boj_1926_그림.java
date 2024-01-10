package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_1926_그림 {

    public static int n,m;
    public static int[][] board;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxArea = 0;
        int cntArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int target = board[i][j];
                if (target == 1 && !visited[i][j]) {
                    int area = bfs(i, j);
                    if (area > maxArea) maxArea = area;
                    cntArea++;
                }
            }
        }

        System.out.println(cntArea);
        System.out.println(maxArea);
    }

    private static int bfs(int r, int c) {
        int area = 0;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r,c});
        visited[r][c] = true;
        area++;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + cur[0];
                int ny = dy[i] + cur[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny] || board[nx][ny] != 1) continue;
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                area++;
            }
        }
        return area;
    }

}
