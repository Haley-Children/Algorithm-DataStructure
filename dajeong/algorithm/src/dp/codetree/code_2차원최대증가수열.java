package dp.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_2차원최대증가수열 {

    static int[][] board, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        dp = new int[n][m]; // 해당 좌표까지 도달했을 때 밟을 수 있는 칸의 최대 수

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;

            }
        }

        
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == -1) continue;
                for (int k = i+1; k < n; k++) {
                    for (int l = j+1; l < m; l++) {
                        if (board[k][l] > board[i][j]) {
                            dp[k][l] = Math.max(dp[i][j] + 1, dp[k][l]);
                        }
                    }
                }
            }
        }

        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (max < dp[i][j]) {
                    max = dp[i][j];
                }
            }
        }

        System.out.println(max);


    }

}
