package simulation.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_숫자의순차적이동 {

    static int n,m;
    static int[][] board;
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 격자 크기
        m = Integer.parseInt(st.nextToken()); // 턴 수

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < m; t++) {
            int idx = 0;
            loop:
            while (idx++ < n*n) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (board[i][j] == idx) {
                            move(i, j);
                            continue loop;
                        }
                    }
                }

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }

    }

    private static void move(int x, int y) {
        int max = 0;
        int mX = 0;
        int mY = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (inRange(nx,ny) && board[nx][ny] > max) {
                max = board[nx][ny];
                mX = nx;
                mY = ny;
            }
        }
        board[mX][mY] = board[x][y];
        board[x][y] = max;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}
