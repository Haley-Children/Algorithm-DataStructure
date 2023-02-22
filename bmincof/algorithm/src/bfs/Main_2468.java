package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2468 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        int n = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        // 최소 높이
        int minValue = Integer.MAX_VALUE;
        // 최대 높이
        int maxValue = Integer.MIN_VALUE;
        // 안전 지대의 최대 수를 저장하는 변수
        // 비가 최소 높이보다 적게 오면 큰 한덩이가 안전지대
        int maxCount = 1;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minValue = Math.min(minValue, map[i][j]);
                maxValue = Math.max(maxValue, map[i][j]);
            }
        }

        Queue<int[]> q = new ArrayDeque<>();

        for (int rain = minValue; rain < maxValue; rain++) {
            // 강우량이 rain일 때 방문정보와 구역의 개수
            boolean[][] vis = new boolean[n][n];
            int count = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 첫 방문이면서 비에 잠기지 않는 곳이면 방문
                    if(map[i][j] > rain && !vis[i][j]) {
                        vis[i][j] = true;
                        q.offer(new int[] {i,j});
                        count++;

                        while (!q.isEmpty()) {
                            int[] cur = q.poll();

                            for (int dir = 0; dir < 4; dir++) {
                                int nx = cur[0] + dx[dir];
                                int ny = cur[1] + dy[dir];

                                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                                // 들렀거나 잠기는 곳이면 방문하지 않기
                                if(vis[nx][ny] || map[nx][ny] <= rain) continue;

                                vis[nx][ny] = true;
                                q.offer(new int[] {nx, ny});
                            }
                        }
                    }
                }

            }

            maxCount = Math.max(count, maxCount);
        }

        System.out.println(maxCount);
    }
}

