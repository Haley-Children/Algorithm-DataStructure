package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600 {
    // BFS 이용
    // 1. 현재 위치에서 상하좌우로 이동한다.
    // 2. 점프 횟수가 남았으면 현재 위치에서 점프한다.
    // 각 이동 시마다
    // - 벽이면 방문하지 않는다.
    // - 이미 방문한 적 있는 곳일 경우, 이전 방문보다 점프 횟수가 많으면 재방문한다.
    // - 방문한 적 없으면 그냥 방문한다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 인접한 칸 이동 4 + 점프 8 방향
        int[] dx = {1, -1, 0, 0, -2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {0, 0, 1, -1, 1, 2, 2, 1, -1, -2, -2, -1};

        // 점프 가능한 횟수
        int k = Integer.parseInt(br.readLine());

        // 너비 높이 입력
        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        // 지도 저장할 배열
        int[][] map = new int[h][w];
        // 방문 당시 움직인 횟수와 방문 당시 남은 점프 횟수
        // [][][0] : 이동 횟수 , [][][1] : 남은 점프 횟수
        int[][][] dist = new int[h][w][2];

        // 지도 입력받기
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j][1] = -1;
            }
        }

        // bfs 시작
        // 0,0 -> h-1, w-1
        Queue<Point> q = new LinkedList<>();

        dist[0][0][0] = 1;
        dist[0][0][1] = k;

        q.offer(new Point(0,0,k));

        move:
        while(!q.isEmpty()) {
            Point cur = q.poll();

            // 이동하기
            for(int dir = 0; dir < 12; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                // 점프했으면 점프 횟수 - 1
                int remainJump = cur.remainJump + (dir >= 4 ? - 1 : 0);

                // 범위 밖이면 스킵
                if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                // 이동하려는 곳이 벽이거나 이미 점프를 지금보다 적게하고 방문한 곳이면 방문/재방문하지 않는다.
                if(map[nx][ny] == 1 || (map[nx][ny] == 0 && dist[nx][ny][1] >= remainJump)) continue;

                q.offer(new Point(nx, ny, remainJump));
                // 이전의 이동 수 + 1
                dist[nx][ny][0] = dist[cur.x][cur.y][0] + 1;
                dist[nx][ny][1] = remainJump;

                // 도착했다면
                if(nx == h - 1 && ny == w - 1) break move;
            }
        }

        System.out.println(dist[h-1][w-1][0] != 0 ? dist[h-1][w-1][0] - 1 : -1);

    }

    static class Point {
        int x, y, remainJump;
        Point(int x, int y, int remainJump) {
            this.x = x;
            this.y = y;
            this.remainJump = remainJump;
        }
    }
}
