package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2579_계단오르기 {

    static int[][] dp = new int[301][3];
    static int[] stairs;

    public static void main(String[] args) throws IOException {
        // dp[i][k] = i번째 계단을 마지막을 밟고, 현재 j만큼 연속해서 계단을 올랐을 때 최대 점수
        // dp[i][1] = Max(dp[i-2][0], dp[i-2][1]) + s(i)
        // dp[i][2] = dp[i-1][1] + s(i)
        // ans = Max (dp[i][1], dp[i][2])

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 계단 갯수
        stairs = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        // 초기값 및 Array Out of Index Error 주의
        dp[1][1] = stairs[1];
        dp[1][2] = 0;
        if (N >= 2) {
            dp[2][1] = stairs[2];
            dp[2][2] = stairs[1] + stairs[2];
        }

        for (int i = 3; i <= N; i++) {
            dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + stairs[i];
            dp[i][2] = dp[i - 1][1] + stairs[i];
        }

        System.out.println(Math.max(dp[N][1], dp[N][2]));
    }

}
