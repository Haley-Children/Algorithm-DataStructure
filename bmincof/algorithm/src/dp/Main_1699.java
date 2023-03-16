package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_1699 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // [i]를 만들 수 있는 제곱수들의 최소 개수
        // [0] = 0
        int[] dp = new int[n+1];

        // n보다 작은 제곱수들 (단위)
        List<Integer> units = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            units.add(i*i);
        }

        for (int i = 1; i <= n; i++) {
            int minCnt = Integer.MAX_VALUE;

            // 지금 수보다 작은 제곱수를 뺀 수를 만들 때
            // 가장 개수가 적게 필요한 것을 선택
            for (int unit : units) {
                if(i < unit) break;

                minCnt = Math.min(minCnt, dp[i - unit]);
            }

            dp[i] = minCnt + 1;
        }

        System.out.println(dp[n]);
    }
}
