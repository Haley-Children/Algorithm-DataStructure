package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj_2146_다리만들기 {

    public static int N;
    public static int isIdx = 1;
    public static int[][] board;
    public static int[][] map; // 섬 idx 표시한 지도
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static Dist[][] dist;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        map = new int[N][N];
        dist = new Dist[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = new Dist(0, -1);
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 idx 표시 && 경계지역 땅은 큐에 넣기 (bfs 수행)
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1 && dist[i][j].d == -1) {
                    bfs(i, j, isIdx++, queue);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int tmpMin = Integer.MAX_VALUE;;
            boolean flag = false;
            while (size-->0) {
                int[] cur = queue.poll();
                int cIdx = cur[0];
                int cx = cur[1];
                int cy = cur[2];
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + cx;
                    int ny = dy[i] + cy;
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                        continue;
                    }

                    // 방문한적 없는 바다일 경우
                    if (map[nx][ny] == 0 && dist[nx][ny].idx == 0) {
                        dist[nx][ny] = new Dist(cIdx, dist[cx][cy].d + 1);
                        queue.offer(new int[]{cIdx, nx,ny});
                    }
                    // 이미 다른섬에서 출발해서 방문한적 있는 바다일 경우
                    if (dist[nx][ny].d > 0 && cIdx != dist[nx][ny].idx &&  dist[nx][ny].idx != 0) {
                        flag = true;
                        if (tmpMin > dist[nx][ny].d + dist[cx][cy].d) {
                            tmpMin = dist[nx][ny].d + dist[cx][cy].d;
                        }
                    }

                    // 다른 섬일 경우
                    if (cIdx != map[nx][ny] && map[nx][ny] != 0) {
                        flag = true;
                        if (tmpMin > dist[cx][cy].d) {
                            tmpMin = dist[cx][cy].d;
                        }
                    }
                }
            }
            if (flag) {
                min = tmpMin;
                break;
            }

        }
        System.out.println(min);


    }

    private static void bfs(int i, int j, int isIdx, ArrayDeque<int[]> queue) {
        ArrayDeque<int[]> tmpQ = new ArrayDeque<>();
        tmpQ.offer(new int[]{i, j});
        dist[i][j].d = 0;
        map[i][j] = isIdx;

        while (!tmpQ.isEmpty()) {
            int[] cur = tmpQ.poll();
            boolean flag = false;
            for (int k = 0; k < 4; k++) {
                int nx = dx[k] + cur[0];
                int ny = dy[k] + cur[1];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (dist[nx][ny].d != -1) {
                    continue;
                }
                if (board[nx][ny] == 0) {
                    flag = true;
                } else {
                    tmpQ.offer(new int[]{nx, ny});
                    dist[nx][ny].d = 0;
                    dist[nx][ny].idx = isIdx;
                    map[nx][ny] = isIdx;
                }
            }
            // 경계 지역 queue에 저장
            if (flag) {
                queue.offer(new int[]{isIdx, cur[0], cur[1]});
            }
        }
    }

    private static class Dist {

        int idx;
        int d;

        public Dist(int idx, int d) {
            this.idx = idx;
            this.d = d;
        }
    }
}
