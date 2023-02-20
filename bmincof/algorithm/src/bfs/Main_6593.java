package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6593 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dx = {1,-1,0,0,0,0};
        int[] dy = {0,0,1,-1,0,0};
        int[] dz = {0,0,0,0,1,-1};

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (l == 0 && r == 0 && c == 0) break;

            char[][][] map = new char[l][r][c];
            int[][][] dist = new int[l][r][c];

            Queue<int[]> q = new ArrayDeque<>();

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    char[] line = br.readLine().toCharArray();
                    for (int k = 0; k < c; k++) {
                        map[i][j][k] = line[k];

                        // 시작점이면 큐에 넣고, 거리를 1로 초기화
                        if (map[i][j][k] == 'S') {
                            dist[i][j][k] = 1;
                            q.offer(new int[]{i, j, k});
                        }

                    }
                }
                // 각 2차원 배열을 입력받을 때마다 한 칸 더 내려가야함
                br.readLine();
            }

            // 탈출하면 true
            boolean escaped = false;
            // 탈출에 걸린 시간
            int escapeTime = 0;
            // bfs 시작
            while (!q.isEmpty() && !escaped) {
                int[] cur = q.poll();

                for (int dir = 0; dir < 6; dir++) {
                    int nx = cur[0] + dx[dir];
                    int ny = cur[1] + dy[dir];
                    int nz = cur[2] + dz[dir];

                    // 범위 밖이면 종료
                    if (nx < 0 || nx >= l || ny < 0 || ny >= r || nz < 0 || nz >= c) continue;
                    // 벽이거나 들렀던 곳이면 방문 x
                    if (map[nx][ny][nz] == '#' || dist[nx][ny][nz] != 0) continue;

                    dist[nx][ny][nz] = dist[cur[0]][cur[1]][cur[2]] + 1;
                    if(map[nx][ny][nz] == 'E') {
                        escapeTime = dist[nx][ny][nz] - 1;
                        escaped = true;
                    }
                    q.offer(new int[]{nx, ny, nz});
                }
            }
            
            // 정답 출력
            if(escaped) {
                sb.append(String.format("Escaped in %d minute(s).\n", escapeTime));
            } else {
                sb.append("Trapped!\n");
            }
        }

        System.out.println(sb);
    }
}
