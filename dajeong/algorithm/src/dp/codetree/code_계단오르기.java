package dp.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class code_계단오르기 {

    static long[] dp = new long[1001];
    public static void main(String[] args) throws IOException {
        // dp[i] = dp[i-2]+dp[i-3]
        // dp[1] = 0, dp[2] = 1, dp[3] = 1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp[2] = 1; dp[3] = 1;

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i-2]+ dp[i-3];
            dp[i] %= 10007;
        }
        System.out.println(dp[n]);
    }

}
