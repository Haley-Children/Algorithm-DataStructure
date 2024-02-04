package simulation.boj;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj_11559_뿌요뿌요 {

    static char[][] board = new char[12][6];
    static boolean[][] visited;
    static boolean[][] check;
    static char[][] tmp = new char[12][6];
    static int bombCnt;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            for (int j = 0; j < 6; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        boolean didExploded = true;
        do { // 1턴
            didExploded = false;
            visited = new boolean[12][6]; // bfs 방문
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (!checkPuyo(board[i][j]) || visited[i][j]) {
                        continue;
                    }
                    check = new boolean[12][6]; // 터져야 할 영역 체크
                    int countToBomb = bfs(i, j, board[i][j]);
                    // 터질 수 있으면, 터뜨리고 연쇄 수 + 1 및 터뜨렸다는 표시
                    if (countToBomb >= 4) {
                        fillToZero();
                        didExploded = true;
                    }
                }
            }
            // 중력
            move();

            // 배열 복사
            copy();

            // 1턴당 연쇄횟수 1번만 더해지는 것 주의!
            if (didExploded) bombCnt++;

        } while (didExploded);

        // 정답 출력
        System.out.println(bombCnt);

    }


    private static int bfs(int r, int c, char target) {
        int areaCnt = 1;
        visited[r][c] = true;
        check[r][c] = true;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + curX;
                int ny = dy[i] + curY;

                if (nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && !visited[nx][ny] && board[nx][ny] == target) {
                    areaCnt++;
                    visited[nx][ny] = true;
                    check[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return areaCnt;
    }

    private static void copy() {
        board = tmp;
        tmp = new char[12][6];
    }

    private static void move() {
        for (int c = 0; c < 6; c++) {
            int tmpIdx = 11; // 위치 주의
            for (int r = 11; r >= 0; r--) {
                if (checkPuyo(board[r][c])) {
                    tmp[tmpIdx--][c] = board[r][c];
                }
            }
        }
    }

    private static boolean checkPuyo(char c) {
        return c == 'R' || c == 'G' || c == 'B' || c == 'P' || c == 'Y';
    }

    private static void fillToZero() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (check[i][j]) {
                    board[i][j] = '.';
                }
            }
        }
    }

}
