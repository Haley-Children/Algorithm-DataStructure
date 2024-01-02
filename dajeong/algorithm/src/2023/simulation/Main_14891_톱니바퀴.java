package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {
    static int[][] gear = new int[4][8];
    static int[] moveDir;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = str.charAt(j)-'0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken())-1; // 0-indexed
            int dir = Integer.parseInt(st.nextToken());
            moveDir = new int[4]; // 각 턴당 톱니바퀴별 방향 저장 배열
            moveDir[cur] = dir;

            // 왼쪽 오른쪽 이동할 cur변수 따로 두기!!!!!!
            int curTmp = cur;
            int lt = cur-1;
            // 돌릴 현재 톱니바퀴 기준 왼쪽 톱니바퀴들 방향 결정
            while(lt>=0) {
                if (gear[curTmp][6] == gear[lt][2]) break; // 서로 같을 경우 다음 기어들도 다 회전하지 못함
                else {
                    moveDir[lt] = (-1) * moveDir[curTmp];
                }
                lt--;
                curTmp--;
            }

            curTmp = cur;
            int rt = cur+1;
            // 오른쪽 톱니바퀴들 방향 결정
            while(rt<=3) {
                if(gear[curTmp][2] == gear[rt][6]) break;
                else {
                    moveDir[rt] = (-1) * moveDir[curTmp];
                }
                curTmp++;
                rt++;
            }


            // 각 톱니바퀴들 회전시키기
            for (int m = 0; m < 4; m++) {
                if (moveDir[m] == 1) { // 시계방향으로 돌리기
                    int backup = gear[m][7];
                    for (int i = 7; i > 0; i--) {
                        gear[m][i] = gear[m][i-1];
                    }
                    gear[m][0] = backup;

                } else if (moveDir[m] == -1){ // 반시계방향으로 돌리기
                    int backup = gear[m][0];
                    for (int i = 0; i < 7; i++) {
                        gear[m][i] = gear[m][i+1];
                    }
                    gear[m][7] = backup;
                }
            }
        }

        // 모든 시뮬레이션이 끝난 후.. 점수 계산
        int cnt = 0;
        cnt += gear[0][0];
        cnt += gear[1][0] * 2;
        cnt += gear[2][0] * 4;
        cnt += gear[3][0] * 8;
        System.out.println(cnt);

    }

}
