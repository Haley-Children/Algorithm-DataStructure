package simulation.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_행복한수열의개수 {

    static int n,m, cnt;
    static int[][] board;
    static int[] tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 격자 크기
        m = Integer.parseInt(st.nextToken()); // 연속해야 하는 숫자의 수
        board = new int[n][n];
        tmp = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가로 탐색
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                tmp[c] = board[r][c];
            }
            if(valid()) {
                cnt++;
            }
        }



        // 세로 탐색
        for (int c = 0; c < n; c++) {
            for (int r = 0; r < n; r++) {
                tmp[r] = board[r][c];
            }
            if (valid()) {
                cnt++;
            }
        }

        System.out.println(cnt);


    }

    private static boolean valid() {
        int count = 1;
        int target = tmp[0];
        int maxCount = 1;
        for (int i = 1; i < n; i++) {
            if (tmp[i] == target) {
                count++;
            } else {
                count = 1;
                target = tmp[i];
            }
            // 아래에서 m이상인 것을 확인 후 바로 return해버리면, n이 1인 격자의 경우 확인 불가능
            if (count >= maxCount) {
                maxCount = count;
            }
        }
        if (maxCount >= m) return true;
        else return false;
    }

}
