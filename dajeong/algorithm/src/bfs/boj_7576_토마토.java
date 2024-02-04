package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_7576_토마토 {

    public static int n, m;
    public static int[][] board;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int[][] dist; // -1로 초기화하여 visted 배열 없이 구현
    public static int day = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    queue.offer(new int[]{i,j});
                    dist[i][j] = 0;
                    cnt++;
                } else if (board[i][j] == -1) {
                    cnt++;
                }
            }
        }

        if (cnt != n*m) {
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + cur[0];
                    int ny = dy[i] + cur[1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }
                    if (board[nx][ny] != 0 || dist[nx][ny] != -1) {
                        continue;
                    }
                    queue.offer(new int[]{nx, ny});
                    dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                    cnt++;
                    if (day < dist[nx][ny]) day = dist[nx][ny];
                }
            }

            if (cnt != n * m) {
                System.out.println(-1);
            } else {
                System.out.println(day);
            }

        } else {
            System.out.println(day);
        }

    }

}
