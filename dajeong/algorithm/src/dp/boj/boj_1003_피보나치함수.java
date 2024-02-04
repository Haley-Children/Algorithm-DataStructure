package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1003_피보나치함수 {

    static int[][] dp = new int[41][2];

    public static void main(String[] args) throws IOException {
        // dp[i][0] = i에서 0이 출력되는 횟수
        // dp[i][1] = i에서 1이 출력되는 횟수
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i <= 40; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
        int T = Integer.parseInt(br.readLine());
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 0; i < T; i++) {
            int t = Integer.parseInt(br.readLine());
            if (dp[t][0] != -1 && dp[t][1] != -1) {
                sb.append(dp[t][0] + " " + dp[t][1]+"\n");
                continue;
            }
            Fibo0(t);
            Fibo1(t);
            sb.append(dp[t][0] + " " + dp[t][1]+"\n");

        }
        System.out.println(sb);
    }

    private static int Fibo1(int n) {
        if (dp[n][1] != -1 ) {
            return dp[n][1];
        }
        dp[n][1] = Fibo1(n-1) + Fibo1(n-2);
        return dp[n][1];
    }

    private static int Fibo0(int n) {
        if (dp[n][0] != -1 ) {
            return dp[n][0];
        }
        dp[n][0] = Fibo0(n-1) + Fibo0(n-2);
        return dp[n][0];
    }
}
