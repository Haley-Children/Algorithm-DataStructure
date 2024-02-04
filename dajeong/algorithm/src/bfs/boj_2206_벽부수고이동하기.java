package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_2206_벽부수고이동하기 {

    public static int N, M;
    public static int[][] board;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int[][][] dist;
    public static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        dist = new int[2][N][M];
        visited = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j)-'0';
            }
        }

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visited[1][0][0] = true;
        dist[1][0][0] = 1;
        queue.offer(new int[]{1, 0, 0});

        int ans = -1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int k = cur[0]; // 벽을 부술 수 있는 횟수 (남은 횟수)
            int x = cur[1]; // 행
            int y = cur[2]; // 열
            if (x == N-1 && y == M-1) {
                ans = dist[k][x][y];
                break; // 탈출 조건
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx <0 || ny < 0 || nx >= N || ny >= M) continue;
                if (board[nx][ny] == 0) { // 길을 만날 때: bfs 큐에 넣기
                    if (visited[k][nx][ny]) continue;
                    queue.offer(new int[] {k,nx,ny});
                    visited[k][nx][ny] = true;
                    dist[k][nx][ny] = dist[k][x][y] + 1;
                } else { // 벽을 만날 때
                    if (k>0) { // 벽 부술 수 있으면 벽 부수고 k 이동
                        // 밑 칸을 이미 방문한 상황이면 bfs 큐에 먼저 들어온 것이 최단거리이므로 더이상 진행할 필요 x
                        if (visited[k-1][nx][ny]) continue;
                        queue.offer(new int[] {k-1, nx, ny});
                        visited[k-1][nx][ny] = true;
                        dist[k-1][nx][ny] = dist[k][x][y] + 1;
                    }
                }
            }
        }
        System.out.println(ans);

    }

}
