package floyd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_11404 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int notConnected = 1000000000;
        // 도시 개수
        int n = Integer.parseInt(br.readLine());
        // 버스 개수
        int m = Integer.parseInt(br.readLine());

        // 도시 끼리 이동하는데 드는 최소 비용
        int[][] minCosts = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == j) continue;

                minCosts[i][j] = notConnected;
            }
        }

        // 최소 비용 저장
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            minCosts[from][to] = Math.min(minCosts[from][to], cost);
        }

        // 최소 비용 갱신
        // 이번에 거쳐갈 도시
        for (int center = 1; center <= n; center++) {
            // 모든 시작점, 도착점에 대해서 기존보다 center번 도시를 거쳐가는게 빠르면 비용 변경
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    minCosts[from][to] = Math.min(minCosts[from][to], minCosts[from][center] + minCosts[center][to]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                bw.write(String.valueOf(minCosts[i][j] == notConnected ? 0 : minCosts[i][j]));
                bw.write(' ');
            }
            bw.write('\n');
        }

        bw.flush();
    }
}
