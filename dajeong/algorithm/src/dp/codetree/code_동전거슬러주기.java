package dp.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class code_동전거슬러주기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[M+1];
        // dp[i] = 동전의 합이 i일 때 가능한 동전 최소 갯수
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < N; j++) {
                if (i - coins[j] >= 0) {
                    if (dp[i-coins[j]] == Integer.MAX_VALUE) { // ***
                        continue;
                    }
                    dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1); // ***
                }
            }
        }


        long ans = (dp[M] == Integer.MAX_VALUE) ? -1 : dp[M];

        System.out.println(ans);
    }

}
