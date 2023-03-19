package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_11057 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // [i]번째 자리수가 [j]일 때 경우의 수
        int[][] dp = new int[n+1][10];

        Arrays.fill(dp[0], 1);

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                if(j == 0) {
                    dp[i][j] = 1;
                } else {
                    // i-1의 자리가 j 이하인 숫자인 경우의 수
                    // == 앞자리가 0부터 j-1까지의 경우의 수 누적 = dp[i][j-1]
                    dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 10007;
                }
            }
        }

        System.out.println(dp[n][9]);
    }
}
