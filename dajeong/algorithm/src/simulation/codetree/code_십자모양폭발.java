package simulation.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_십자모양폭발 {

    static int n; // 격자 크기
    static int[][] board; // 원본 격자
    static int[][] tmpBoard; // tmp 격자


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        tmpBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 폭탄 터뜨리기
        st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken()) - 1;
        int col = Integer.parseInt(st.nextToken()) - 1;
        int bombRange = board[row][col];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // *** bombRange를 밖에서 선언해주지 않았을 때, 지우는 과정에서 range 값이 0이 되어 제대로 폭탄 삭제가 되지 않는다.
                if (bomb(i, j, row, col, bombRange)) {
                    board[i][j] = 0;
                }
            }
        }

        // 터뜨린 후 중력 작용한 결과를 tmp에 적용
        for (int c = 0; c < n; c++) {
            int tmpIdx = n - 1;
            for (int r = n - 1; r >= 0; r--) {
                if (board[r][c] != 0) {
                    tmpBoard[tmpIdx--][c] = board[r][c];
                }
            }
        }

        // 원본배열을 tmp 배열로 교체
        board = tmpBoard;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }

    // 그냥 거리로만 따지면, 대각선 영역까지 거리계산되므로 x축 혹은 y축이 같은 조건 추가
    private static boolean bomb(int x, int y, int centerX, int centerY, int range) {
        return (x == centerX || y == centerY) && (Math.abs(x - centerX) + Math.abs(y - centerY)
                < range);
    }

}
