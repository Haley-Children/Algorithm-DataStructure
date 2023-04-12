package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3190_뱀 {

    static int N,K,L;
    static int[][] board;
    static int[] dix = {0, 1, 0, -1}; // 오아왼위
    static int[] diy = {1, 0, -1, 0};
    static int[][] timeDir;
    static int[] snail_x = new int[10101];
    static int[] snail_y = new int[10101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // 보드 크기
        K = Integer.parseInt(br.readLine()); // 사과의 갯수
        board = new int[N][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x-1][y-1] = -2; // 사과 표시
        }
        L = Integer.parseInt(br.readLine()); // 뱀 방향 횟수
        timeDir = new int[L+1][2]; // 시간에 따른 방향 변화 기록할 배열 (바뀌는 시간, 회전 방향)
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char tmp = st.nextToken().charAt(0);
            int d;
            if (tmp == 'D') {
                d = 1; // 오른쪽 90도 회전
            } else {
                d = -1; // 왼쪽 90도 회전
            }

            timeDir[i][0]= t;
            timeDir[i][1] = d;
        }

        int headIdx = 0; // head 포인터
        int tailIdx = 0; // tail 포인터
        int dir = 0; // 처음 방향: 오른쪽
        int dirIdx = 0;
        // 뱀 위치 기록
        snail_x[headIdx] = 0;
        snail_y[headIdx] = 0;
        board[0][0] = -1; // 뱀 초기 위치 보드에 기록

        int time = 0;
        while(true) {
            time++;
            int nx = snail_x[headIdx] + dix[dir];
            int ny = snail_y[headIdx] + diy[dir];

            // 이동하려는 위치 범위 체크, 자기 자신과 닿는지 체크
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == -1) break;

            // 이동하려는 위치에 사과가 없으면 꼬리 이동
            if (board[nx][ny] != -2) {
                board[snail_x[tailIdx]][snail_y[tailIdx]] = 0;
                tailIdx++;
            }

            // 머리 이동
            headIdx++;
            snail_x[headIdx] = nx;
            snail_y[headIdx] = ny;
            board[nx][ny] = -1;


            if (dirIdx < L && time == timeDir[dirIdx][0]) {
                dir += timeDir[dirIdx][1];
                dir = (dir + 4) % 4; // 0~3 인덱스로 조정
                dirIdx++;
            }

        }
        System.out.println(time);


    }

    private static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j]+"      ");
            }
            System.out.println();
        }
    }


}
