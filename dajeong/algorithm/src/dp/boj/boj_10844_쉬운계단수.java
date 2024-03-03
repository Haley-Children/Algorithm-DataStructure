package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_10844_쉬운계단수 {

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n][10]; // dp[i][j] = i회까지 숫자를 고르고 마지막 숫자가 j일 때 가능한 계단 수의 갯수

        for (int i = 1; i < 10; i++) {
            dp[0][i] = 1;
        }

        // 숫자가 증가하거나 감소할 때, 이전 가짓수를 "더해 주어야" 한다. 곱하는 것 아니다.
        // 그리고 나누기 연산하는 것 문제 나중에 보고 알았다. 주의깊게 문제를 보자.
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                // 이전에 -1된 숫자가 올 수 있는 경우
                if (j >= 1 && dp[i - 1][j - 1] != -1) {
                    dp[i][j] += dp[i - 1][j - 1] % MOD;
                }
                // 이전에 +1된 숫자가 올 수 있는 경우
                if (j <= 8 && dp[i - 1][j + 1] != -1) {
                    dp[i][j] += dp[i - 1][j + 1] % MOD;
                }
            }
        }


        // long 주의!
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            if (dp[n - 1][i] != -1) {
                sum += dp[n - 1][i];
                sum %= MOD;
            }
        }

        System.out.println(sum);
    }

}
