package dp.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class code_부분수열의합이m {

    // 부분수열이어야 하므로 각 원소는 최대 한 번까지 사용 가능
    // 합이 m이 되는 경우의 부분수열이므로, 최장공통부분수열 문제와 다르다.
    // 각 원소에 대해 한번씩만 돌면서 m을 거꾸로 돌면 된다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 원소의 갯수
        int m = Integer.parseInt(st.nextToken()); // 만들고자 하는 합
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[m + 1]; // dp[i] = 합 i까지 가능한 부분 수열의 최소 길이
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = m; j > 0; j--) {
                if (j - num >= 0) {
                    if (dp[j - num] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[j] = Math.min(dp[j - num] + 1, dp[j]);
                }
            }
        }

        if (dp[m] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dp[m]);
        }

    }

}
