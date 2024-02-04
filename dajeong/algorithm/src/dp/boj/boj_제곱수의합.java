package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class boj_제곱수의합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i * i < 100_000; i++) {
            nums.add(i * i);
        }

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < N + 1; i++) {
            for (int j = 0; j < nums.size(); j++) {
                int t = nums.get(j);
                if (i - t >= 0) {
                    if (dp[i - t] == Integer.MAX_VALUE) {
                        continue;
                    }
                    dp[i] = Math.min(dp[i], dp[i - t] + 1);
                }
            }
        }

        System.out.println(dp[N]);
    }

}
