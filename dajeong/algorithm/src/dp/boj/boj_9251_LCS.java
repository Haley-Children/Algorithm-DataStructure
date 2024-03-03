package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_9251_LCS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        // 1-index로 이용하기 위해 앞에 문자 더해주기
        str1 = "#" + str1;
        str2 = "#" + str2;

        int n = str1.length();
        int m = str2.length();
        // dp[i][j] = 첫번째 문자열 i번째까지 고려하고 두번째 문자열에서 j번째 문자까지 고려했을 때 최장 공통 부분수열의 길이
        int[][] dp = new int[n][m];

        // 초기 세팅
        // dp[1][1] = 각 첫번째 문자열이 서로 같으면 1, 다르면 0
        // 각 문자열의 첫번째 문자에 대하여 나머지 문자열을 순회하며 두 문자가 같아지기 전까지는 0, 한번이라도 같아지고 난 이후에는 1 채워넣기
        dp[1][1] = str1.charAt(1) == str2.charAt(1) ? 1 : 0;
        for (int i = 2; i < n; i++) {
            if (str1.charAt(i) == str2.charAt(1)) {
                dp[i][1] = 1;
            } else {
                dp[i][1] = dp[i - 1][1];
            }
        }

        for (int j = 2; j < m; j++) {
            if (str2.charAt(j) == str1.charAt(1)) {
                dp[1][j] = 1;
            } else {
                dp[1][j] = dp[1][j - 1];
            }
        }

        // dp 채워넣기
        for (int i = 2; i < n; i++) {
            for (int j = 2; j < m; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 정답
        System.out.println(dp[n - 1][m - 1]);


    }
}
