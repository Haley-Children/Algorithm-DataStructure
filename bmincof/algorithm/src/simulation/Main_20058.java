package boj;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main_20058 {
    static int n, q, l;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();

        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        int size = (int) Math.pow(2, n);
        map = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < q; i++) {
            l = sc.nextInt();

            rotate(size, 0, 0);

            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if(map[r][c] == 0) continue;

                    // 0이 아닌 칸의 수
                    int count = 0;
                    for (int dir = 0; dir < 4; dir++) {
                        int nr = r + dx[dir];
                        int nc = c + dy[dir];

                        if(nr < 0 || nr >= size || nc < 0 || nc >= size) continue;
                        if(map[nr][nc] != 0) {
                            count++;
                        }
                    }

                    // 인접한 칸을 모두 살폈을 때 얼음이 없는 칸이 3개 미만이면 녹는다.
                    if(count < 3) {
                        queue.offer(new int[] {r, c});
                    }
                }
            }

            while(!queue.isEmpty()) {
                int[] cur = queue.poll();

                map[cur[0]][cur[1]]--;
            }
        }

        // 남아 있는 얼음에 대해서 bfs를 진행
        // 얼음의 합을 누적
        // 가장 큰 덩어리 갱신
        int maxSize = 0;
        int iceSum = 0;

        boolean[][] vis = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // 얼음이면 bfs 시작
                if(map[i][j] != 0 && !vis[i][j]) {
                    // 얼음의 크기
                    int iceSize = 1;
                    // 얼음 합 누적
                    iceSum += map[i][j];

                    queue.offer(new int[] {i, j});
                    vis[i][j] = true;

                    while(!queue.isEmpty()) {
                        int[] cur = queue.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int nx = cur[0] + dx[dir];
                            int ny = cur[1] + dy[dir];

                            if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
                            if(map[nx][ny] == 0 || vis[nx][ny]) continue;

                            iceSize++;
                            iceSum += map[nx][ny];

                            queue.offer(new int[] {nx, ny});
                            vis[nx][ny] = true;
                        }
                    }

                    maxSize = Math.max(maxSize, iceSize);
                }
            }
        }

        System.out.println(iceSum);
        System.out.println(maxSize);
    }

    static void rotate(int size, int r, int c) {
        if(size == (int) Math.pow(2, l)) {
            int[][] rotated = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    rotated[i][j] = map[r + size - 1 - j][c + i];
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    map[r + i][c + j] = rotated[i][j];
                }
            }
            return;
        }

        int nSize = size / 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                rotate(nSize, r + nSize * i, c + nSize * j);
            }
        }
    }
}

