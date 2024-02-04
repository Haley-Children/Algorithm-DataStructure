package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2573_빙산 {

    public static int n, m;
    public static int[][] board;
    public static int[][] newBoard;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        int zeroCnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    zeroCnt++;
                }
            }
        }


        int time = 0;
        boolean flag = false;
        // 반복 조건을 단순히 최대 높이 이하로 잡아서 틀렸었음
        while (zeroCnt < n*m) {
            time++;
            newBoard = new int[n][m];
            visited = new boolean[n][m];
            // 빙산 녹이기
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    int cnt = 0;
                    for (int j = 0; j < 4; j++) {
                        int nx = r + dx[j];
                        int ny = c + dy[j];
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] != 0) {
                            continue;
                        }
                        cnt++;
                    }
                    int diff = board[r][c] - cnt;
                    if (diff >= 0) {
                        newBoard[r][c] = diff;
                    }
                }
            }

            // 분리된 빙산 세기 (bfs)
            int areaCnt = 0;
            zeroCnt = 0;
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    if (newBoard[r][c] ==0) zeroCnt++;
                    if (newBoard[r][c] > 0 && !visited[r][c]) {
                        bfs(r, c);
                        areaCnt++;
                    }
                }
            }

            // 2개 이상인지 확인
            if (areaCnt >= 2) {
                flag = true;
                break;
            }
            board = newBoard;
        }

        // 가능 / 불가능 체크를 해주지 않고 time을 출력하여 틀렸었음
        if (flag) {
            System.out.println(time);
        } else {
            System.out.println(0);
        }
    }

    private static void bfs(int r, int c) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + cur[0];
                int ny = dy[i] + cur[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (visited[nx][ny] || newBoard[nx][ny] <= 0) {
                    continue;
                }
                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

}
