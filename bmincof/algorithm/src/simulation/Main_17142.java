package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_17142 {
    static int n, m;
    static byte[][] map;
    static int minDist = Integer.MAX_VALUE;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        map = new byte[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextByte();
            }
        }

        bt(0, 0);
        System.out.println(minDist == Integer.MAX_VALUE ? -1 : minDist);
    }

    static void bt(int k, int prev) {
        // m 개의 바이러스를 모두 골랐으면
        if(k == m) {
            bfs();
            return;
        }

        // i번째 선택에서 고를 수 있는 칸의 위치
        // 이전에 고른 칸 + 1 ~ 마지막 칸 - 골라야하는 개수 + 현재 고른 개수
        for (int i = prev; i < (n * n) - m + (k + 1); i++) {

            // 바이러스를 놓을 수 있는 곳이면
            if(map[i / n][i % n] == 2) {
                // 바이러스 놓았다고 체크
                map[i / n][i % n] = 3;
                bt(k + 1, i + 1);
                map[i / n][i % n] = 2;
            }
        }
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[n][n];

        // 바이러스가 퍼질 수 있는 공간의 개수
        int room = n * n;
        // 바이러스가 퍼진 공간
        int count = m;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 3 && dist[i][j] == 0) {
                    q.offer(new int[] {i, j});
                    dist[i][j] = 1;
                }

                if(map[i][j] == 1 || map[i][j] == 2) {
                    room--;
                }
            }
        }

        // 바이러스가 모두 퍼졌을 때 시간
        int time = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(map[nx][ny] == 1 || dist[nx][ny] > 0) continue;

                q.offer(new int[] {nx, ny});
                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                if(map[nx][ny] != 2) {
                    time = Math.max(time, dist[nx][ny]);
                    count++;
                }
            }
        }

        if(room == count) {
            minDist = Math.min(minDist, time - 1);
        }
    }
}

