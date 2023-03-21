package floyd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_11780 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 도시의 개수
        int n = Integer.parseInt(br.readLine());
        // 버스의 개수
        int m = Integer.parseInt(br.readLine());
        final int INF = 100000 * n + 1;

        // 최소 비용 배열
        int[][] dist = new int[n+1][n+1];
        // 경로 배열 : 최소 비용으로 [i]에서 [j]로 이동하기 위해 방문해야 하는 다음 정점
        int[][] next = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (dist[u][v] <= w) continue;
            dist[u][v] = w;
            next[u][v] = v;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (dist[j][k] <= dist[j][i] + dist[i][k]) continue;

                    dist[j][k] = dist[j][i] + dist[i][k];
                    // 최소 비용으로 이동하려면 j부터 i로
                    // 최소 비용으로 이동해야 함
                    next[j][k] = next[j][i];
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        // 최소 비용 출력
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                sb.append(dist[i][j] == INF ? 0 : dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        // 경로 복원하기
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // i에서 j로 가는 길이 있을 경우 경로 복원
                List<Integer> path = new ArrayList<>();
                if(next[i][j] != 0) {
                    int cur = i;
                    // 다음 경로가 없을 때까지
                    while (cur != 0) {
                        path.add(cur);
                        cur = next[cur][j];
                    }
                }
                // i에서 j로 가는 경로 출력
                sb.append(path.size()).append(" ");
                for(int p : path) {
                    sb.append(p).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
