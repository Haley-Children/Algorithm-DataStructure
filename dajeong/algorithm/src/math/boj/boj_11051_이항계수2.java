package math.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11051_이항계수2 {
    static final int MOD = 10_007;

    public static void main(String[] args) throws IOException {
        // dp[i][j] = iCj
        // dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] comb = new int[1002][1002];
        for (int i = 0; i <= 1000; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i-1][j] + comb[i-1][j-1]) % MOD;
            }
        }
        System.out.println(comb[N][K]);

    }

}
