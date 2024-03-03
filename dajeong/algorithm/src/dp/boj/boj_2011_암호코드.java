package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2011_암호코드 {

    static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        str = "0" + str; // 편의상 0-index 추가

        // *** 0처리 주의!!
        // 맨 처음 값이 0인 경우, 0앞에 0 오는 경우, 0앞에 3이상인 값이 오는 경우 불가능
        if (str.charAt(1) - '0' == 0) {
            System.out.println(0);
            System.exit(0);
        }

        int len = str.length();
        int[] dp = new int[len];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < len; i++) {
            int n1 = str.charAt(i) - '0';
            int n2 = Integer.parseInt(str.substring(i - 1, i + 1));

            if (n1 == 0) {
                if (n2 >= 30 || n2 == 0) {
                    System.out.println(0);
                    System.exit(0);
                }
                if (n2 == 10 || n2 == 20) {
                    dp[i] = dp[i - 2];
                }
            } else {
                if (11 <= n2 && n2 <= 26) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else if (n2 >= 27 || n2 < 10) {
                    dp[i] = dp[i - 1];
                }
            }
            dp[i] %= MOD;

        }

        System.out.println(dp[len - 1]);

    }

}
