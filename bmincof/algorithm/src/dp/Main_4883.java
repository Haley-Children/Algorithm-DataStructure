package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_4883 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int count = 0;
        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            // with padding
            int[][] graph = new int[n][3];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                graph[i][0] = Integer.parseInt(st.nextToken());
                graph[i][1] = Integer.parseInt(st.nextToken());
                graph[i][2] = Integer.parseInt(st.nextToken());
            }

            // 비용은 정수이므로 횡 이동이 이득인 경우도 있다.
            // j번째 행의 k번째 열에 도착하기 위해 이전에 도달해야 하는 곳
            // (j, k - 1), (j - 1, k - 1), (j - 1, k), (j - 1, k + 1)
            // 위 4 곳 중 가장 비용이 적은 경로를 선택하면 됨

            // 0행의 중간(1열)부터 출발해야 하므로 0행과 1행에 초기값 설정
            int[][] dp = new int[n][3];

            dp[0][1] = graph[0][1];
            dp[0][2] = graph[0][2] + dp[0][1];

            dp[1][0] = graph[1][0] + dp[0][1];
            dp[1][1] = graph[1][1] + Math.min(Math.min(dp[0][1], dp[0][2]), dp[1][0]);
            dp[1][2] = graph[1][2] + Math.min(Math.min(dp[0][1], dp[0][2]), dp[1][1]);

            // dp 배열 채우기
            for (int i = 2; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    int minCost = dp[i-1][j];
                    if(j > 0) {
                        minCost = Math.min(minCost, dp[i][j-1]);
                        minCost = Math.min(minCost, dp[i-1][j-1]);
                    }
                    if(j < 2) {
                        minCost = Math.min(minCost, dp[i-1][j+1]);
                    }

                    dp[i][j] = graph[i][j] + minCost;
                }
            }

            sb.append(++count).append(". ").append(dp[n-1][1]).append("\n");
        }

        System.out.println(sb);
    }
}
