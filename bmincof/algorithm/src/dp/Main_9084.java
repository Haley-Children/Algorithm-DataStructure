package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_9084 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            // 동전 단위
            int[] units = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                units[i] = Integer.parseInt(st.nextToken());
            }
            // 목표 금액
            int goal = Integer.parseInt(br.readLine());
            // [i]번 동전까지 사용해서 [j]원을 만들 수 있는 경우의 수
            int[] dp = new int[goal+1];
            dp[0] = 1;

            // i번 동전까지 써서 j원을 만드는 방법 수 =
            //      i-1 번 동전까지 사용해서 j원을 만드는 방법 수
            //          + i번 동전까지 써서 j-i번 동전 금액으로 만드는 방법의 수
            for (int i = 1; i <= n; i++) {
                if(units[i] > goal) break;
                for (int j = units[i]; j <= goal; j++) {
                    dp[j] += dp[j - units[i]];
                }
                System.out.println(Arrays.toString(dp));
            }
            sb.append(dp[goal]).append("\n");
        }

        System.out.println(sb);
    }
}
