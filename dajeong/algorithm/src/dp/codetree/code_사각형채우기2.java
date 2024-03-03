package dp.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class code_사각형채우기2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i-2] * 2 + dp[i-1];
            dp[i] %= 10007;
        }
        System.out.println(dp[n]);

    }

}
