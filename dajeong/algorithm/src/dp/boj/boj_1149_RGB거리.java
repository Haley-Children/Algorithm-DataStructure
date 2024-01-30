package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1149_RGB거리 {

    static int[][] cost = new int[1001][3];
    static int[][] dp = new int[1001][3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 집 수
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken()); // 빨
            cost[i][1] = Integer.parseInt(st.nextToken()); // 초
            cost[i][2] = Integer.parseInt(st.nextToken()); // 파
        }

        // dp[i][0] = Min(dp[i-1][1], dp[i-1][2]) + cost[i][0]
        // dp[i][1], dp[i][2] 똑같이 ㅇㅇ
        dp[1][0] = cost[1][0];
        dp[1][1] = cost[1][1];
        dp[1][2] = cost[1][2];

        for (int i = 2; i <= N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + cost[i][2];
        }

        int min = Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]);
        System.out.println(min);

    }

}
