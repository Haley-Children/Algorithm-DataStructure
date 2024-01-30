package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_9095_123더하기 {

    static int[] dp = new int[12];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        Arrays.fill(dp, -1);
        for (int i = 0; i < T; i++) {
            int t = Integer.parseInt(br.readLine());
            if (dp[t] != -1) {
                System.out.println(dp[t]);
                continue;
            }

            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            for (int j = 4; j <= t; j++) {
                dp[j] = dp[j-1] + dp[j-2] + dp[j-3];
            }
            System.out.println(dp[t]);
        }
    }

}
