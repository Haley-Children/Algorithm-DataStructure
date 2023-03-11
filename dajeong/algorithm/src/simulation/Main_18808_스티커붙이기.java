package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1. 스티커 선택.
// 2. 스티커를 붙일 위치 찾기
// -> 없으면 90도 회전 후 2번 수행-다시 위치 찾기 (4방향 회전 다 했는데도 없으면 1로 이동)
// 2. 스티커 붙이기
// 3. 모든 스티커 붙이기가 끝난 후 총 몇개의 칸이 채워졌는지 확인 후 출력

public class Main_18808_스티커붙이기 {

    static int N, M, K, R, C;
    static int[][] notebook;
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노트북 행
        M = Integer.parseInt(st.nextToken()); // 노트북 열
        K = Integer.parseInt(st.nextToken()); // 스티커 갯수
        notebook = new int[N][M];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken()); // 스티커 행
            C = Integer.parseInt(st.nextToken()); // 스티커 열
            paper = new int[11][11]; // 회전을 더 편하게 하기 위해 스티커가 아닌 모눈종이를 돌린다. (범위 고정)
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    paper[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            int rotateCnt = 4;
            while (rotateCnt-- > 0) {
                boolean flag = false;
                for (int x = 0; x <= N - R; x++) { // ** 스티커 체크할 범위 주의!!!!!! 등호 빼먹어서 계속 틀렸음 ㅠㅠㅠㅠ
                    for (int y = 0; y <= M - C; y++) {
                        if (isPotable(x, y)) {
                            attach(x, y);
                            flag = true;
                            break;
                        }
                    }
                    if (flag) break;
                }
                if (flag) break;
                rotate(); // 스티커 붙일 위치가 없으면, 회전 후 다시 찾아보기
            }
        }
        int cnt = checkFillCnt();
        System.out.println(cnt);
    }

    private static int checkFillCnt() {
        int cnt = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (notebook[r][c] == 1) cnt++;
            }
        }
        return cnt;
    }

    // 스티커 붙일 위치 확인
    private static boolean isPotable(int x, int y) {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (notebook[x+r][y+c] == 1 && paper[r][c] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // 스티커 붙이기
    private static void attach(int x, int y) {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (paper[r][c] == 1) {
                    notebook[x+r][y+c] = 1;
                }
            }
        }
    }

    // 중요) 회전 범위 유의해서 보기. 바뀔 paper는 행, 열이 바뀌어야 하며 최종적인 스티커 행, 열 수도 바꿔주어야 한다.
    private static void rotate() {
        int[][] tmp = new int[11][11];
        for (int r = 0; r < R; r++) {
            tmp[r] = Arrays.copyOf(paper[r], paper[r].length);
        }
        // 모눈종이 행, 열 바뀜
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                paper[i][j] = tmp[R-1-j][i];
            }
        }
        // 스티커 행, 열 수 꼭 바꿔주기.
        int t = R;
        R = C;
        C = t;
    }

}
