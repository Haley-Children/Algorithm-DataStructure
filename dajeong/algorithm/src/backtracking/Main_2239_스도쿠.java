package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2239_스도쿠 {

    static int[][] board = new int[9][9];
    static boolean[][] rowUsed = new boolean[9][9+1];
    static boolean[][] colUsed = new boolean[9][9+1];
    static boolean[][] boxUsed = new boolean[9][9+1];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = str.charAt(j)-'0';
                if (board[i][j]!=0) {
                    rowUsed[i][board[i][j]] = true; // 행에 해당 숫자가 들어가있는가
                    colUsed[j][board[i][j]] = true; // 열에 해당 숫자가 들어가있는가
                    boxUsed[getBoxNum(i,j)][board[i][j]] = true; // 박스에 해당 숫자가 들어가있는가
                }
            }
        }

        backtracking(0,0);


    }

    private static void backtracking(int row, int col) {
        // 종료 조건
        if (col == 9) { // 열 끝까지 다 왔을 때 열 번호 갱신, row +1
            col = 0;
            row++;
            if (row == 9) { // 9행 9열일 때 스도쿠 출력 후 종료
                printBoard();
                System.exit(0); // 가능한 스도쿠 배열 중 첫번째 스도쿠(사전순)가 완성되었을 때 시스템 종료
                return;
            }
        }

        if (board[row][col] == 0) {
            for (int n = 1; n < 10; n++) {
                int boxNum = getBoxNum(row, col);
                if (rowUsed[row][n] || colUsed[col][n] || boxUsed[boxNum][n]) continue;

                board[row][col] = n;
                rowUsed[row][n] = true;
                colUsed[col][n] = true;
                boxUsed[boxNum][n] = true;

                backtracking(row, col+1);

                board[row][col] = 0;
                rowUsed[row][n] = false;
                colUsed[col][n] = false;
                boxUsed[boxNum][n] = false;
            }
        } else backtracking(row, col+1);
    }

    private static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    // 3*3 박스 번호 리턴
    private static int getBoxNum(int row, int col) {
        return (row/3)*3+(col/3);
    }


}
