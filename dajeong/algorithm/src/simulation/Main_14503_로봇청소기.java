package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503_로봇청소기 {

    static int N, M;
    static int[][] board;
    static int[] dix = {-1, 0, 1, 0};
    static int[] diy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Vacuum curVac = new Vacuum(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(clean(curVac));

    }

    private static int clean(Vacuum curVac) {
        int cnt = 0;
        Vacuum curV = curVac;
        while (true) {

            //printBoard();


            // 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (board[curV.x][curV.y] == 0) {
                board[curV.x][curV.y] = 2; // 청소된 경우 2 표시
                cnt++;
            }
            // 주변 4칸 중 청소되지 않는 빈칸이 있는지 여부 확인 플래그
            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                int nx = curV.x + dix[i];
                int ny = curV.y + diy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (board[nx][ny] == 1) continue;
                // 현재 칸 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                if (board[nx][ny] == 0) {
                    flag = true;
                    curV = rotate90(curV); // 최적화) 전진 가능한 방향까지 방향 설정한 뒤에 이동
                    break;
                }
            }
            // 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            if (!flag) {
                int nx = curV.x + dix[(curV.d+2)%4]; // if문 분기 필요 없음..
                int ny = curV.y + diy[(curV.d+2)%4];
                // 후진 불가할 경우 작동 멈추기
                if (nx<0 || ny<0 || nx>=N || ny>=M) break;
                if (board[nx][ny] == 1) break;
                // 후진 가능할 경우 후진
                curV.x = nx;
                curV.y = ny;
            }

        }

        return cnt;
    }

    private static Vacuum rotate90(Vacuum curV) {
        int d = curV.d;
        int x = curV.x;
        int y = curV.y;
        while(true) { // 헐.....ㅋㅋ 주어진 방향 순서 잘못 알고 있었다
            d = (d + 3) % 4;

            int nnx = x + dix[d];
            int nny = y + diy[d];
            if (nnx < 0 || nny < 0 || nnx >= N || nny >= M) {
                continue;
            }
            if (board[nnx][nny] == 0) {
                return new Vacuum(nnx, nny, d);
            }

        }
    }

    private static class Vacuum {

        int x;
        int y;
        int d;

        public Vacuum(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Vaccum{" +
                    "x=" + x +
                    ", y=" + y +
                    ", d=" + d +
                    '}';
        }
    }
}