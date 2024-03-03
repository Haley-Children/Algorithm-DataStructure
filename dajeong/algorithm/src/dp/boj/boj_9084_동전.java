package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_9084_동전 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine()); // 동전 수
            int[] coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine()); // 금액
            int[] dp = new int[M + 1]; // m원까지의 가짓수
            dp[0] = 1; // *** 아무것도 선택하지 않는 가짓수 1개!

            // 각각 동전에 대해 dp 순회하면서 갱신하기!
            for (int i = 0; i < N; i++) {
                int c = coins[i];
                for (int j = 1; j <= M; j++) {
                    if (j - c >= 0) {
                        dp[j] += dp[j - c];
                    }
                }
            }
            sb.append(dp[M] + "\n");
        }
        System.out.println(sb);
    }
}
