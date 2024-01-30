package simulation.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14503_로봇청소기 {

    static int[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N,M, curX, curY, dir, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        st = new StringTokenizer(br.readLine());
        curX = Integer.parseInt(st.nextToken());
        curY = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean stop;
        do {
            stop = false;
            clean(curX, curY);
            if (hasDirtyRoom(curX, curY)) {
                dir = (dir - 1 + 4) % 4;
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];
                if (inRange(nx, ny) && board[nx][ny]==0) {
                    curX = nx;
                    curY = ny;
                }
            } else {
                // 방향 유지한채로 후진
                int rDir = (dir + 2) % 4;
                int nx = curX + dx[rDir];
                int ny = curY + dy[rDir]; // curY를 curX라고 해서 계속 틀림... 잘 보자..^^
                if (inRange(nx, ny) && board[nx][ny] != 1) {
                    curX = nx;
                    curY = ny;
                } else if (inRange(nx, ny) && board[nx][ny] == 1){
                    stop = true;
                }
            }
        } while (!stop);

        System.out.println(cnt);

    }

    private static boolean hasDirtyRoom(int x, int y) {
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!inRange(nx, ny)) continue;
            if (board[nx][ny] == 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private static void clean(int x, int y) {
        if (inRange(x,y) && board[x][y] == 0) {
            board[x][y] = -1;
            cnt++;
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
