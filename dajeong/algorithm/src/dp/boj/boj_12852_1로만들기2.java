package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_12852_1로만들기2 {

    static int[] dp, path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        path = new int[N + 1];

        dp[1] = 0;
        path[1] = 0;
        if (N >= 2) {
            dp[2] = 1;
            path[2] = 1;
        }
        if (N >= 3) {
            dp[3] = 1;
            path[3] = 1;
        }
        for (int i = 4; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            path[i] = i-1;
            if (i % 3 == 0) {
                if (dp[i / 3] + 1 < dp[i]) {
                    dp[i] = dp[i / 3] + 1;
                    path[i] = i/3;
                }
            }
            if (i % 2 == 0) {
                if (dp[i / 2] + 1 < dp[i]) {
                    dp[i] = dp[i/2] + 1;
                    path[i] = i/2;
                }
            }

        }
        System.out.println(dp[N]);
        int tmp = N;
        while (tmp >= 1) {
            System.out.print(tmp+" ");
            tmp = path[tmp];
        }
    }

}
