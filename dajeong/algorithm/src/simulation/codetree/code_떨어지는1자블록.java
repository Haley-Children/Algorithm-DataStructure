package simulation.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_떨어지는1자블록 {
    static int[][] board;
    static int n,m,k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 격자 크기
        m = Integer.parseInt(st.nextToken()); // 블록의 크기
        k = Integer.parseInt(st.nextToken())-1; // 블록이 떨어질 위치 (col 0-indexed)

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 최대로 떨어질 수 있는 행 idx 구하기
        int r = 0;
        while (r < n) {
            if (possible(r)) {
                r++;
            } else {
                break;
            }
        }

        if (r > 0) r -= 1;

        // 블럭 위치시키기
        for (int c = k; c < k+m; c++) {
            board[r][c] = 1;
        }

        // 보드 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }

    }

    private static boolean possible(int r) {
        boolean flag = true;
        for (int i = k; i < k+m; i++) {
            if (board[r][i] != 0) return false;
        }
        return flag;
    }

}
