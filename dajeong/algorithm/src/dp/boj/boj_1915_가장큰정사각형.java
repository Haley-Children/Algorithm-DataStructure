package dp.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1915_가장큰정사각형 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        // 초기값 보드와 똑같이 세팅
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                int t = str.charAt(j) - '0';
                board[i][j] = t;
                dp[i][j] = t;
            }
        }

        // dp[i][j] = i,j 그 좌표가 오른쪽 하단 꼭짓점일 때 만들 수 있는 가장 큰 정사각형 갯수
        // ** 처음에는 n*m만큼 다시 살펴보면서 0이 나올 때 break하고 체크하는 방식 이용했었음 -> 당연히 시간초과
        // *** dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]) + 1
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]) + 1;
                }
            }
        }

        // 최대 한변의 길이 찾기
        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (max < dp[i][j]) {
                    max = dp[i][j];
                }
            }
        }

        // max는 한 변의 길이이므로 제곱해서 면적 반환
        System.out.println(max*max);

    }

}
