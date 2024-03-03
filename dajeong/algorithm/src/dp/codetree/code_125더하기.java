package dp.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class code_125더하기 {

    static final int MOD = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        int[] num = {1, 2, 5};
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < num.length; j++) {
                if (i - num[j] >= 0) {
                    dp[i] += dp[i-num[j]];
                }
            }
            dp[i] %= MOD;
        }
        System.out.println(dp[n]);
    }

}
