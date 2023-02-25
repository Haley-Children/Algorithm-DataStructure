package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main_14499_주사위굴리기 {

    static int N,M,x,y,K;
    static Pos curPos;
    static int[][] board;
    static int[] command;
    static int[] dice;
    static boolean flag;
    static int[] dix = {0, 0, 0, -1, 1}; // 이동x, 동서북남
    static int[] diy = {0, 1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        x = Integer.parseInt(st.nextToken()); // 시작 위치 x
        y = Integer.parseInt(st.nextToken()); // 시작 위치 y
        curPos = new Pos(x,y); // 현재 위치를 알려주는 객체
        K = Integer.parseInt(st.nextToken()); // 명령어 갯수
        // 보드 입력
        board = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // 명령어 입력
        command = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        dice = new int[6];
        for (int t = 0; t < K; t++) {
            int dir = command[t]; // 방향
            flag = true;
            go(dir); // 다이스 위치 이동
            if (!flag) continue;
            switch (dir){
                case 1:
                    right();
                    break;
                case 2:
                    left();
                    break;
                case 3:
                    up();
                    break;
                case 4:
                    down();
                    break;
            }
            copyVal(); // 다이스와 보드 값 카피
            sb.append(dice[0]).append("\n"); // 상단에 쓰여진 값 출력
        }
        System.out.println(sb);
    }

    private static void copyVal() {
        int bottom = dice[5];
        int boardNum = board[curPos.x][curPos.y];
        if (boardNum == 0) {
            board[curPos.x][curPos.y] = bottom;
        } else {
            dice[5] = boardNum;
            board[curPos.x][curPos.y] = 0;
        }
    }

    private static void down() {
        int[] shiftArr = new int[6];
        shiftArr[4] = dice[0];
        shiftArr[5] = dice[4];
        shiftArr[1] = dice[5];
        shiftArr[0] = dice[1];
        shiftArr[2] = dice[2];
        shiftArr[3] = dice[3];
        dice = shiftArr;
    }

    private static void up() {
        int[] shiftArr = new int[6];
        shiftArr[1] = dice[0];
        shiftArr[5] = dice[1];
        shiftArr[4] = dice[5];
        shiftArr[0] = dice[4];
        shiftArr[2] = dice[2];
        shiftArr[3] = dice[3];
        dice = shiftArr;
    }

    private static void left() {
        int[] shiftArr = new int[6];
        shiftArr[3] = dice[0];
        shiftArr[5] = dice[3];
        shiftArr[2] = dice[5];
        shiftArr[0] = dice[2];
        shiftArr[1] = dice[1];
        shiftArr[4] = dice[4];
        dice = shiftArr;
    }

    private static void right() {
        int[] shiftArr = new int[6];
        shiftArr[2] = dice[0];
        shiftArr[5] = dice[2];
        shiftArr[3] = dice[5];
        shiftArr[0] = dice[3];
        shiftArr[1] = dice[1];
        shiftArr[4] = dice[4];
        dice = shiftArr;
    }



    private static void go(int dir) {
        int x = curPos.x;
        int y = curPos.y;
        x += dix[dir];
        y += diy[dir];
        if (x<0||x>=N||y<0||y>=M) { // 보드 밖으로 이동하려고 할 경우, 종료 및 명령어 무시를 위한 플래그 설정
            flag = false;
            return;
        }
        curPos.x = x;
        curPos.y = y;
    }

}
