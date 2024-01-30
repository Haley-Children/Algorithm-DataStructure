package simulation.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class code_벽짚고미로탈출하기 {

    static char[][] board;
    static int N, x, y, time;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int dir = 1; // 방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 격자 크기
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken()) - 1; // 시작위치 행 (0-index)
        y = Integer.parseInt(st.nextToken()) - 1; // 열
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        int curX = x;
        int curY = y;
        int time = -1;

        loop:
        while (++time <= N * N) {
            int[] nP = nextPos(curX, curY);
            int nx = nP[0];
            int ny = nP[1];

            // 탈출 여부 체크
            if (!isRange(nx, ny)) {
                time++;
                break loop;
            }

            // 이동할 위치가 벽이라면 반시계 90도로 바꿔주기
            if (board[nx][ny] == '#') {
                boolean flag = false;
                for (int i = 0; i < 4; i++) {
                    dir = (dir - 1 + 4) % 4;
                    nP = nextPos(curX, curY);
                    nx = nP[0];
                    ny = nP[1];
                    if (!isRange(nx, ny)) {
                        time++;
                        break loop;
                    }
                    if (board[nx][ny] != '#') {
                        flag = true;
                        break;
                    }

                }
                // 벽에 막혀 탈출 불가
                if (!flag) {
                    time = -1;
                    break loop;
                }
            }

            // 이동할 위치의 오른쪽 벽이 없다면 시계방향으로 90도 회전
            if (right(nx, ny, (dir + 1) % 4) != '#') {
                dir = (dir + 1) % 4;
            }
            // 전진하기
            curX = nx;
            curY = ny;


        }

        // 벽에 막히지 않더라도, 계속 무한 뺑뺑이 도는 경우 경우의 수 처리
        if (time > N * N) {
            System.out.println(-1);
        } else {
            System.out.println(time);
        }


    }

    private static char right(int x, int y, int d) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        return board[nx][ny];
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private static int[] nextPos(int x, int y) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        return new int[]{nx, ny};
    }

}
