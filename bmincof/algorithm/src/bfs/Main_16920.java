package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16920 {
    // 1. 각 플레이어의 시작점을 저장해뒀다가 플레이어 순서대로 큐에 넣는다.
    // 2. 플레이어의 순서대로 BFS를 시작한다. (플레이어가 방문한 노드 정보를 담은 큐 q로)
    // 3. 플레이어마다 인접한 range칸을 움직일 수 있으므로 플레이어가 방문했던 위치에서 시작해 range번 움직이는 bfs를 수행한다.
    //    (방문한 노드부터 몇 번 움직였는지 정보도 담긴 큐 iq로)
    // 4. range번 움직이면 q에 방문정보를 저장한다.
    // 5. 모든 노드를 방문하면 각 플레이어가 차지한 성을 센다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        // 각 플레이어가 한 번에 이동할 수 있는 칸 수
        int[] range = new int[p + 1];
        st = new StringTokenizer(br.readLine());
        // 이동 범위 입력
        for (int i = 1; i <= p; i++) {
            range[i] = Integer.parseInt(st.nextToken());
        }

        // 맵의 정보
        char[][] map = new char[n][m];
        // 방문 정보
        boolean[][] vis = new boolean[n][m];

        // 플레이어 p의 시작점을 저장
        ArrayList<int[]>[] startPoints = new ArrayList[p + 1];
        for (int i = 1; i <= p; i++) {
            startPoints[i] = new ArrayList<>();
        }

        // 초기 지도 입력받기
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] != '.' && map[i][j] != '#') {
                    // p번 플레이어의 큐에 bfs 시작점을 저장
                    startPoints[map[i][j] - '0'].add(new int[] {i, j});
                    vis[i][j] = true;
                }
            }
        }

        // bfs를 위한 큐
        Queue<Point> q = new ArrayDeque<>();

        // 저장해둔 시작점들을 플레이어 순서에 맞게 큐에 입력
        for (int player = 1; player <= p; player++) {
            for(int[] start : startPoints[player]) {
                q.offer(new Point(start[0], start[1], player));
            }
        }
        startPoints = null;

        // bfs 시작
        while(!q.isEmpty()) {
            // 각 플레이어마다 range칸 움직이기 위한 BFS용 큐
            Queue<InnerBfs> iq = new ArrayDeque<>();

            // 이번 차례의 플레이어가 방문했던 정보를 모두 iq에 담는다.
            while (iq.isEmpty() || !q.isEmpty() && (iq.peek().point.player == q.peek().player)) {
                iq.offer(new InnerBfs(q.poll(), 0));
            }

            while (!iq.isEmpty()) {
                InnerBfs curIq = iq.poll();
                Point cur = curIq.point;
                // 각 플레이어가 움직일 수 있는 범위만큼 이동해서 성 세우기
                // 인접한 네 방향에 대해서
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];

                    // 맵 밖이면 스킵
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    // 해당 위치가 벽이거나 지도
                    if (map[nx][ny] == '#' || vis[nx][ny]) continue;

                    map[nx][ny] = (char) (cur.player + '0');
                    vis[nx][ny] = true;
                    Point nPoint = new Point(nx, ny, cur.player);

                    // 움직일 수 있는 횟수를 모두 움직였으면 iq에 그만 넣는다.
                    if(curIq.move + 1 == range[cur.player]) {
                        // range만큼 움직인 결과를 q에도 넣어준다.
                        q.offer(nPoint);
                    } else {
                        // 움직인 횟수를 + 1 하고 iq에도 넣어준다.
                        iq.offer(new InnerBfs(nPoint, curIq.move + 1));
                    }
                }
            }
        }

        int[] count = new int[p + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] != '.' && map[i][j] != '#') {
                    count[map[i][j] - '0']++;
                }
            }
        }

        for (int i = 1; i <= p; i++) {
            System.out.print(count[i] + " ");
        }

    }

    static class Point {
        int x, y, player;
        Point(int x, int y, int player) {
            this.x = x;
            this.y = y;
            this.player = player;
        }
    }

    static class InnerBfs {
        Point point;
        int move;
        InnerBfs(Point point, int move) {
            this.point = point;
            this.move = move;
        }
    }
}

