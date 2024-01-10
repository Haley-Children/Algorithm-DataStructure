package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2178_미로탐색 {

    public static int n, m;
    public static int[][] board;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int[][] dist; // -1로 초기화하여 visted 배열 없이 구현

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 && dist[i][j] == -1) {
                    queue.offer(new int[]{i,j});
                    dist[i][j] = 1;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = dx[k] + cur[0];
                            int ny = dy[k] + cur[1];

                            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                            if (dist[nx][ny] != -1 || board[nx][ny] != 1) continue;
                            queue.offer(new int[]{nx, ny});
                            dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                        }
                    }
                }
            }
        }

        System.out.println(dist[n-1][m-1]);
    }
}
