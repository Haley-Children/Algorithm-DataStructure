package simulation;

import java.util.Scanner;

public class Main_15684 {
    static int n, m, h;
    static int[][] ladder;
    static int minHorizontal = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 사다리의 세로선 개수
        n = sc.nextInt();
        // 사다리의 초기 가로선 개수
        m = sc.nextInt();
        // 사다리의 층수
        h = sc.nextInt();

        // 초기 사다리 상태
        ladder = new int[h][n];

        // 초기 가로선 놓기
        for (int i = 0; i < m; i++) {
            int floor = sc.nextInt() - 1;
            int from = sc.nextInt() - 1;

            // from 에서 오른쪽 1칸으로 연결
            ladder[floor][from] = 1;
            // 연결된 곳에서 from 으로 1칸 연결(왼쪽으로)
            ladder[floor][from + 1] = -1;
        }
//        System.out.println(Arrays.deepToString(ladder));

        // 모두 처음 시작한 세로 막대에서 끝났는지 저장하는 변수
        boolean isNotShifted = true;
        // 모든 사다리 타기
        for(int i = 0; i < n; i++) {
            // 모든 세로선에서 내려가면서 시작 막대에서 끝나는지 저장
            isNotShifted &= downLadder(i);
            // 하나라도 다른 곳에서 끝나면 다음 단계 넘어가기
        }
        if(isNotShifted) {
            System.out.println(0);
            return;
        } else {
            bt(0);
        }
        System.out.println(minHorizontal == 3 ? -1 : minHorizontal + 1);
    }

    static boolean downLadder(int lane) {
        // 현재 위치하는 세로막대의 번호
        int curLane = lane;

        for (int i = 0; i < h; i++) {
            // 중간에 가로막대를 만나면 해당 위치로 이동
            curLane += ladder[i][curLane];
        }

        // 사다리를 모두 내려왔을 때 위치하는 세로막대의 번호가 처음과 같은지 반환
        return curLane == lane;
    }

    static void bt(int k) {
        if(k >= minHorizontal) {
            return;
        }

        for (int floor = 0; floor < h; floor++) {
            for (int lane = 0; lane < n-1; lane++) {

                // 해당 위치에 이미 가로선이 있다면 스킵
                if(ladder[floor][lane] != 0 || ladder[floor][lane + 1] != 0) {
                    continue;
                }

                // 가로선 놓기
                ladder[floor][lane] = 1;
                ladder[floor][lane + 1] = -1;

                boolean isNotShifted = true;
                // 사다리 타기
                for (int i = 0; i < n; i++) {
                    // 모든 세로선에서 내려가면서 시작 막대에서 끝나는지 저장
                    isNotShifted &= downLadder(i);
                    // 3번 이하로 끝나는 경우를 찾은 적 없고 하나라도 다른 곳에서 끝나면 다음 단계 넘어가기
                    if(!isNotShifted) {
                        bt(k + 1);
                        break;
                    }
                }
                // 변경한 사다리 복구
                ladder[floor][lane] = 0;
                ladder[floor][lane + 1] = 0;

                // 모두 시작한 막대에서 끝났다면
                if(isNotShifted) {
                    minHorizontal = Math.min(minHorizontal, k);
                    // 필요한 최소 가로선의 개수를 변경
//                    System.out.println(minHorizontal);
//                    System.out.println(Arrays.deepToString(ladder));
                    // 해당 분기 종료
                    return;
                }

            }
        }

    }

}