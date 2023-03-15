package dp;

import java.util.Scanner;

public class Main_9251 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        int len1 = str1.length();
        int len2 = str2.length();

        // str2의 0 ~ i 까지의 부분 문자열로 str1의 0 ~ j 까지의
        // 부분 문자열 내에서 만들 수 있는 부분 공통 수열의 최대 길이
        int[][] dp = new int[len2+1][len1+1];

        // 같은 문자를 발견했으면 이전까지 만들었던 최장 공통 부분 수열 길이에 + 1
        // 서로 다른 문자이면 i-1, j-1 크기의 문자열로 만든 최장 공통 부분 수열 길이 중 더 큰 것을 선택
        // -> dp[i][j] : (i, j) 까지 나온 최장 공통 부분 수열의 길이
        for (int i = 1; i <= len2; i++) {
            char c2 = str2.charAt(i-1);
            for (int j = 1; j <= len1; j++) {
                char c1 = str1.charAt(j-1);

                if(c1 == c2) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[len2][len1]);
    }
}
