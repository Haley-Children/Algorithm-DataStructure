package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_11559_PuyoPuyo {

    static char[][] board;
    static int popCnt;
    static boolean[] checkCol;
    static boolean[][] vis;
    static int[] dix = {-1, 0, 1, 0}; // 상우하좌
    static int[] diy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[12][6];
        for (int r = 0; r < 12; r++) {
            String str = br.readLine();
            for (int c = 0; c < 6; c++) {
                board[r][c] = str.charAt(c);
            }
        }

        popCnt = 0; // 연쇄 수
        gameStart: while (true) {
            int puyoCnt = 0; // 뿌요 수
            // 새 턴 시작! 뿌요 수 세기
            for (int r = 0; r < 12; r++) {
                for (int c = 0; c < 6; c++) {
                    if (board[r][c] != '.') puyoCnt++;
                }
            }
            if (puyoCnt == 0) break; // 게임 보드 내에 뿌요가 없을 경우 게임 종료

            // 뿌요가 있을 때 터뜨릴 뿌요 탐색
            int tmpPopCnt = popCnt;
            vis = new boolean[12][6];  // bfs용 방문배열
            boolean flag = false;
            checkCol = new boolean[6]; // 터진 뿌요가 위치한 열 번호 확인용 배열
            for (int c = 0; c < 6; c++) {
                for (int r = 12 - 1; r >= 0; r--) {
                    if (board[r][c] != '.') {
                        if (canPop(r, c)) {
                            flag = true; // 여러 그룹이 터지더라도 한번의 연쇄가 추가되기 때문에 터지는지 여부만 체크
                        }
                    }
                }
            }

            if (flag) {
                popCnt++; // 같은 뿌요끼리 터질 수 있으면, popCnt++
                rearrangeBoard(); // 재정렬 후 게임 다시 시작
                continue gameStart;
            }

            if (tmpPopCnt == popCnt) break; // 뿌요가 있지만 더이상 터질 뿌요가 없을 경우 게임 종료
        }
        System.out.println(popCnt); // 연쇄 수 출력
    }

    private static void rearrangeBoard() {
        for (int c = 0; c < 6; c++) {
            if (!checkCol[c]) continue;
            for (int r = 12 - 2; r >= 0; r--) { // 경계선 위부터 뿌요 탐색
                if (board[r][c] != '.') {
                    down(r,c);
                }
            }
        }
    }

    private static void down(int r, int c) {
        char target = board[r][c];
        int nr = r;
        while (true) {
            nr ++;
            if (nr >= 12) break;
            if (board[nr][c] != '.') break;
        }
        if ((nr-1) != r) {
            board[nr-1][c] = target; // ***
            board[r][c] = '.';
        }
    }

    private static boolean canPop(int r, int c) { // bfs로 터질 수 있는 뿌요 수 세기
        char[][] copyBoard = new char[12][6]; // 터뜨린 뿌요 수가 4 이상일 경우 바꿔치기할 복사 배열
        for (int i = 0; i < 12; i++) {
            copyBoard[i] = Arrays.copyOf(board[i], board[i].length);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r,c});
        vis[r][c] = true;
        checkCol[c] = true;
        copyBoard[r][c] = '.';
        int cnt = 1; // 터뜨릴 수 있는 뿌요 수

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            char curPuyo = board[curX][curY];
            for (int i = 0; i < 4; i++) {
                int nx = curX + dix[i];
                int ny = curY + diy[i];

                if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6 || vis[nx][ny]) continue;
                if (board[nx][ny] != curPuyo) continue;
                queue.offer(new int[] {nx,ny});
                vis[nx][ny] = true;
                checkCol[ny] = true;
                copyBoard[nx][ny] = '.';
                cnt++;
            }
        }

        if (cnt >= 4) {
            board = copyBoard;
            return true;
        }
        return false;
    }
}
