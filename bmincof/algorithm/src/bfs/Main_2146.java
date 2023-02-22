package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 각 섬마다 섬의 경계에서 또 다른 섬을 만날 때까지 BFS를 통해서 가장 먼저 만나는 섬까지의 거리가 다리의 최소 길이이다.
 * 섬을 구분하고 섬의 경계에서 BFS를 시작하는 방법을 찾는 것이 관건이다.
 * 1. 완전 탐색을 하면서 섬을 만나면 BFS를 통해 섬 전체를 방문 표시한다.
 * 2. 이때 동시에 다리만들기 BFS를 하기 위한 큐에도 같이 넣어 놓는다.
 * 3. 섬 전체를 모두 방문 표시하고 나면 다리만들기를 시작한다.
 * 4. 섬 내부 영역은 BFS 탐색 조건에 의해 무시되고 섬의 경계에서부터 BFS를 시작하게 되므로 원하는 결과를 얻을 수 있다.
 * 5. 조건이 꼬이지 않게 하기 위해 각 섬에서 다리만들기 BFS를 진행할 때마다 거리 배열을 새로 만든다.
 */
public class Main_2146 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        // bfs를 진행한 섬인지 확인하기 위한 맵
        boolean[][] vis = new boolean[n][n];
        // 거리 정보를 기록하기 위한 맵
        int[][] dist = new int[n][n];

        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        // 지도 정보를 입력받음과 동시에 거리 배열을 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = -1;
            }
        }

        // 섬 탐색을 하기 위한 큐
        Queue<Point> qIsland = new LinkedList<>();

        // 최소 다리 길이를 저장하기 위한 변수
        int minBridge = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 새로운 섬이 있으면 탐색
                if(map[i][j] == 1 && !vis[i][j]) {
                    // 다리 길이 측정을 위한 큐
                    Queue<Point> bridge = new LinkedList<>();
                    Point start = new Point(i,j);

                    // 가장 짧은 다리를 찾기 위해 맵 복사
                    int[][] nDist = new int[n][];
                    for (int k = 0; k < n; k++) {
                        nDist[k] = Arrays.copyOf(dist[i], n);
                    }

                    // BFS를 위한 큐 두 개에 모두 방문한 노드를 넣어준다.
                    qIsland.offer(start);
                    bridge.offer(start);

                    nDist[i][j] = 0;
                    // 섬마다 BFS를 하기 위해 들렀던 섬은 방문표시를 해준다.
                    vis[i][j] = true;
                    // 섬의 모든 영역을 탐색할 때까지
                    while(!qIsland.isEmpty()) {
                        Point cur = qIsland.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int nx = cur.x + dx[dir];
                            int ny = cur.y + dy[dir];

                            // 범위 밖이면 스킵
                            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                            // 바다거나 방문한 적 있는 땅이면 스킵
                            if(map[nx][ny] != 1 || nDist[nx][ny] != -1) continue;

                            Point newLand = new Point(nx, ny);
                            nDist[nx][ny] = 0;
                            vis[nx][ny] = true;
                            qIsland.offer(newLand);
                            bridge.offer(newLand);
                        }
                    }

                    // 다리 놓기 시작, 방문한 적 없는 새로운 섬을 만날때까지 바다 이동
                    bridge:
                    while(!bridge.isEmpty()) {
                        Point cur = bridge.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int nx = cur.x + dx[dir];
                            int ny = cur.y + dy[dir];

                            // 범위 밖이면 스킵
                            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                            // 방문한 적 없는 섬이면 지금의 다리 길이가 최소
                            if(map[nx][ny] == 1 && nDist[nx][ny] == -1) {
                                minBridge = Math.min(minBridge, nDist[cur.x][cur.y]);
                                break bridge;
                            }
                            // 방문한 적 있으면 재방문 x
                            if(nDist[nx][ny] > -1) continue;

                            bridge.offer(new Point(nx, ny));
                            nDist[nx][ny] = nDist[cur.x][cur.y] + 1;
                        }
                    }
                }
            }
        }

        System.out.println(minBridge);
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

