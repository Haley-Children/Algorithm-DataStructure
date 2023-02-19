package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2206 {
    // BFS로 해결하되 dist 배열에 이전 방문시 벽 부순 횟수보다 높으면 덮어쓰기
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 지도를 저장하기 위한 배열
        char[][] map = new char[n][];
        // 해당 위치에 방문했을 때 정보를 나타내기 위한 배열
        Visit[][] visInfo = new Visit[n][m];

        // 맵 탐색 위한 델타
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        // 지도 입력 받기
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // BFS 시작
        Queue<Point> q = new LinkedList<>();
        visInfo[0][0] = new Visit(1, 1);
        q.offer(new Point(0,0,1));

        search:
        while(!q.isEmpty()) {
            Point cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                // 범위 밖이면 건너뛰기
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                // 다음 위치가 벽이고 벽을 부술 수 있다면 벽을 부수고 방문
                if(map[nx][ny] == '1') {
                    if(cur.left > 0) {
                        visInfo[nx][ny] = new Visit(visInfo[cur.x][cur.y].dist + 1, cur.left - 1);
                        q.offer(new Point(nx, ny, cur.left - 1));
                    }
                    continue;
                }
                // 다음 위치가 길이고 방문한 적 있을 때
                if(map[nx][ny] == '0' && visInfo[nx][ny] != null) {
                    // 이전에 방문했어도 부술 수 있는 횟수가 더 많으면 덮어쓰기
                    if(visInfo[nx][ny].left < cur.left) {
                        visInfo[nx][ny] = new Visit(visInfo[cur.x][cur.y].dist + 1, cur.left);
                        q.offer(new Point(nx, ny, cur.left));
                    }
                    // 이전에 방문한 적 없고 처음 들르는 곳일 때
                } else {
                    visInfo[nx][ny] = new Visit(visInfo[cur.x][cur.y].dist + 1, cur.left);
                    q.offer(new Point(nx, ny, cur.left));
                }

                // 가장 먼저 도착한 결과를 반환
                if(nx == n-1 && ny == m-1) break search;
            }
        }

        System.out.println(visInfo[n-1][m-1] == null ? -1 : visInfo[n-1][m-1].dist);

    }

    // 방문 시 시작점으로부터 이동한 거리와 남은 벽 부수기 횟수
    static class Visit {
        int dist;
        int left;

        Visit(int dist, int left) {
            this.dist = dist;
            this.left = left;
        }
    }

    // 현재의 x, y 위치, 현재까지오면서 벽 부순 횟수
    static class Point {
        int x;
        int y;
        int left;

        Point(int x, int y, int left) {
            this.x = x;
            this.y = y;
            this.left = left;
        }
    }
}
