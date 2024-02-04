package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1932_정수삼각형 {

    static int[][] board, dp;
    public static void main(String[] args) throws IOException {
        // dp[i][j] = (i, j) 좌표를 마지막으로 도달했을 때 얻을 수 있는 최대합

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 삼각형 크기
        board = new int[n][n];
        dp = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기값 세팅
        dp[0][0] = board[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + board[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[i][i] = dp[i-1][i-1] + board[i][i];
        }


        // dp 채우기
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + board[i][j];
            }
        }

        // 최댓값 구하기
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (max < dp[n-1][i]) max = dp[n-1][i];
        }
        System.out.println(max);
    }

}
