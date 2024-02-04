package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_4179_불 {

    public static int n, m;

    public static char[][] board;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int[][] jTime; // 지훈이 걸리는 최소 시간
    public static int[][] fTime; // 불 최소 확산 시간
    public static boolean[][] jVisited; // 지훈 방문 배열
    public static boolean[][] fVisited; // 불 방문 배열


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        jTime = new int[n][m];
        fTime = new int[n][m];
        jVisited = new boolean[n][m];
        fVisited = new boolean[n][m];

        // 불이 아예 안오는 경우를 구분해주고자 -1로 채우기
        for (int i = 0; i < n; i++) {
            Arrays.fill(fTime[i], -1);
        }

        // 1. 불 시작점들 큐에 넣기
        ArrayDeque<int[]> fQueue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'F') {
                    fQueue.offer(new int[]{i, j});
                    fTime[i][j] = 0;
                    fVisited[i][j] = true;
                }
            }
        }

        // 2. 불 bfs
        while (!fQueue.isEmpty()) {
            int[] cur = fQueue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + cur[0];
                int ny = dy[i] + cur[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (board[nx][ny] == '#' || fVisited[nx][ny]) {
                    continue;
                }
                fQueue.offer(new int[]{nx, ny});
                fVisited[nx][ny] = true;
                fTime[nx][ny] = fTime[cur[0]][cur[1]] + 1;
            }
        }

        // 3. 지훈 bfs
        ArrayDeque<int[]> jQueue = new ArrayDeque<>();
        int minTime = Integer.MAX_VALUE;
        loop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'J') {
                    jQueue.offer(new int[]{i, j});
                    jVisited[i][j] = true;
                    while (!jQueue.isEmpty()) {
                        int[] cur = jQueue.poll();
                        int nT = jTime[cur[0]][cur[1]] + 1;

                        // nx, ny에 탈출조건을 넣으면, cur이 탈출조건일 때를 확인하지 못한다!
                        if (cur[0] == 0 || cur[0] == n - 1 || cur[1] == 0 || cur[1] == m - 1) {
                            minTime = nT;
                            break loop;
                        }

                        for (int k = 0; k < 4; k++) {
                            int nx = dx[k] + cur[0];
                            int ny = dy[k] + cur[1];

                            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                                continue;
                            }
                            if (jVisited[nx][ny] || board[nx][ny] == '#') {
                                continue;
                            }
                            if (fTime[nx][ny] >= 0 && fTime[nx][ny] <= nT) {
                                continue;
                            }

                            jQueue.offer(new int[]{nx, ny});
                            jVisited[nx][ny] = true;
                            jTime[nx][ny] = nT;
                        }
                    }
                }
            }
        }

        if (minTime == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(minTime);
        }


    }

}
