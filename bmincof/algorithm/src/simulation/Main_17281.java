package simulation;

import java.util.Arrays;
import java.util.Scanner;

public class Main_17281 {
    // 타순을 저장한 배열
    static short[] order = new short[9];
    // 타순 지정을 위한 사용여부 배열
    static boolean[] isUsed = new boolean[10];

    // 이닝 수
    static int n;
    // 각 플레이어가 이닝에서 얻는 결과
    static byte[][] point;
    static int maxScore = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 이닝 수 입력
        n = sc.nextInt();
        point = new byte[n][9];

        // 이닝마다
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 9; j++) {
                // i 이닝에서 j번 선수의 결과
                point[i][j] = sc.nextByte();
            }
        }

        bt(0);
        System.out.println(maxScore);
    }
    
    static void bt(int cnt) {
        // 모든 선수의 순서를 정했으면 게임 시작
        if(cnt == 9) {
            playGame();
            return;
        }

        // 4번타자는 1번 선수
        if(cnt == 3) {
            order[cnt] = 0;
            bt(cnt + 1);
        // 나머지 자리는 중복되지 않게 순서대로
        } else {
            for (short i = 1; i < 9; i++) {
                if (!isUsed[i]) {
                    isUsed[i] = true;
                    order[cnt] = i;

                    bt(cnt + 1);

                    isUsed[i] = false;
                }
            }
        }
    }

    // 모든 이닝을 끝내고 점수 기록
    static void playGame() {
        // 현재 몇 번 타자인지
        int cur = 0;

        // 게임이 끝난 후 점수 총합
        int totalScore = 0;

        // n이닝 동안 진행
        for (int i = 0; i < n; i++) {
            // 각 이닝마다 카운트 할 것들
            // 아웃 횟수
            int out = 0;
            // 점수
            int score = 0;
            // 각 루에 선수가 있는지
            // 0은 사용하지 않는다.
            boolean[] run = new boolean[4];

            while(out < 3) {
                // i 이닝에서 cur 번 타자의 결과
                // 다음 타자로 차례 넘김
                short hit = point[i][order[(cur++) % 9]];
                // 아웃이면
                if(hit == 0) {
                    out++;
                    continue;
                }
                // 아웃이 아니면 주자 진출
                // 기존에 있던 선수부터 이동
                // hit 값만큼 진출한다.
                for (int runner = 3; runner > 0; runner--) {
                    if(run[runner]) {
                        // 홈으로 들어가면
                        if(runner + hit > 3) {
                            run[runner] = false;
                            score++;
                        // 홈으로 들어가지 못했으면 위치만 이동
                        } else {
                            run[runner + hit] = true;
                            run[runner] = false;
                        }
                    }
                }
                // 타자 진출
                // 홈런이면 바로 점수
                if(hit == 4) {
                    score++;
                } else {
                    run[hit] = true;
                }
            }
            // i 이닝에서 얻은 점수 더하기
            totalScore += score;
        }

        maxScore = Math.max(maxScore, totalScore);
    }
}
