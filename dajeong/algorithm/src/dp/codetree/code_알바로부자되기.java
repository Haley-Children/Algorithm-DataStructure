package dp.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class code_알바로부자되기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] alba = new int[N][3];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            alba[i][0] = Integer.parseInt(st.nextToken());
            alba[i][1] = Integer.parseInt(st.nextToken());
            alba[i][2] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        dp[0] = alba[0][2];

        for (int i = 1; i < N; i++) {
            dp[i] = alba[i][2];
            for (int j = 0; j < i; j++) {
                if (dp[j] == -1 || alba[i][0] <= alba[j][1]) {
                    continue;
                }
                dp[i] = Math.max(dp[i], dp[j] + alba[i][2]);
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }

        System.out.println(max);
    }

}
