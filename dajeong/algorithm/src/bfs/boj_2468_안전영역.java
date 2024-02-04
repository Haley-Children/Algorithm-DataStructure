package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_2468_안전영역 {

    public static int[][] board;
    public static boolean[][] newBoard;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static boolean[][] visited;
    public static int max = 1;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        int minVal = 101;
        int maxVal = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                int v = board[i][j];
                if (minVal > v) minVal = v;
                if (maxVal < v) maxVal = v;
            }
        }


        for (int i = minVal; i <= maxVal; i++) {
            int tmpCnt = 0;
            newBoard = new boolean[N][N];
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] <= i) {
                        newBoard[r][c] = true; // 잠긴부분은 true
                    }
                }
            }
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!newBoard[r][c] && !visited[r][c]) {
                        bfs(r,c);
                        tmpCnt++;
                    }
                }
            }

            if (tmpCnt > max) max = tmpCnt;
        }

        System.out.println(max);
    }

    private static void bfs(int r, int c) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r,c});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + cur[0];
                int ny = dy[i] + cur[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny] || newBoard[nx][ny]) continue;
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

}
