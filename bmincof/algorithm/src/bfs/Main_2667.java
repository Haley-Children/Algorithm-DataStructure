package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2667 {
    // m*n 크기 맵을 만들고 입력받은 영역을 true로 변경한다.
    // 모든 영역을 입력받으면 true가 아닌 영역들을 bfs로 카운트한다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};

        int n = Integer.parseInt(br.readLine());

        char[][] map = new char[n][];
        // 직사각형 입력받기
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 각 단지에 속한 집의 개수 카운트
        int areaCnt = 0;
        // 나눠진 단지의 크기들을 저장
        List<Integer> areas = new ArrayList<>();
        // BFS를 위한 큐, x,y좌표 저장
        Queue<int[]> q = new LinkedList<>();
        // BFS 방문 표시를 위한 배열
        boolean[][] vis = new boolean[n][n];

        // bfs로 영역마다 넓이를 구하고, 개수 카운트
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                // 집(1)이고 방문한 적 없으면 BFS 시작
                if(map[r][c] == '1' && !vis[r][c]) {
                    q.offer(new int[] {r, c});
                    vis[r][c] = true;

                    // 이번 영역의 크기
                    int area = 1;
                    while(!q.isEmpty()) {
                        int[] cur = q.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int nx = cur[0] + dx[dir];
                            int ny = cur[1] + dy[dir];

                            // 범위 밖이면 스킵
                            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                            // 집이 아니거나 방문한 곳이면 스킵
                            if(map[nx][ny] == '0' || vis[nx][ny]) continue;

                            q.offer(new int[] {nx, ny});
                            vis[nx][ny] = true;
                            area++;
                        }
                    }

                    // 나눠진 새로운 영역이므로 영역 개수 + 1
                    areaCnt++;
                    // 구한 영역의 크기를 저장
                    areas.add(area);
                }
            }
        }

        // 모든 영역을 확인했으면 영역들의 크기를 오름차순으로 정렬
        Collections.sort(areas);
        StringBuilder sb = new StringBuilder();
        sb.append(areaCnt).append("\n");
        for (int area : areas) {
            sb.append(area).append("\n");
        }

        System.out.println(sb);
    }


}

