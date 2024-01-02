package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17144_미세먼지안녕 {

    // 미세먼지 확산은 1초에 "동시에" 일어난다. 따라서 확산을 기존 맵에서 하면, 변화된 값이 영향을 줄 수 있기 때문에 새로운 맵 사용해야 함
    // 공기청정기가 있거나, "칸이 없으면(빈칸 아님)" 확산이 일어나지 않음
    // 확산되는 양 = 먼지 / 5
    // 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수) => 상하좌우로 확산되는 cnt 세야 함

    static int[] dix = {-1, 1, 0, 0}; // 상하좌우
    static int[] diy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken()); // 행
        int C = Integer.parseInt(st.nextToken()); // 열
        int T = Integer.parseInt(st.nextToken()); // 시간 초

        int A = -1; // 공기청정기 아래 행 좌표
        int[][] map = new int[R][C];


        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == -1) {
                    A = r; // 가장 아래 공기청정기 행 좌표 넣어주기
                }
            }
        }


        for (int t = 0; t < T; t++) {
            // 1. 먼지 확산
            int[][] newMap = new int[R][C]; // 먼지가 확산된 상황을 저장할 배열
            // 공기청정기 좌표 입력
            newMap[A][0] = -1;
            newMap[A - 1][0] = -1;

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c] > 0) { // 먼지일 때
                        int cnt = 0;
                        for (int i = 0; i < 4; i++) {
                            int nx = r + dix[i];
                            int ny = c + diy[i];
                            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                                continue;
                            }
                            if (map[nx][ny] == -1) {
                                continue; // 공기청정기 위치일 경우 확산되지 않음 (빈칸일 때도 일어난다)
                            }
                            newMap[nx][ny] += (map[r][c] / 5);
                            cnt++;
                        }
                        newMap[r][c] += (map[r][c] - cnt * (int)(map[r][c] / 5)); // *** 소수점때문에 int 안붙여주면 더 큰 값이 제거된다.
                    }
                }
            }

            // 2. 공기청정기 가동
            // 공기 청정기 좌표는 (A-1,0)와 (A,0)
            // 위쪽 공기청정기부터 가동

            int x1 = A-2;
            int y1 = 0;
            int x2 = A-1;
            int y2 = 0;

            while(x2 != 0) {
                newMap[x2--][y2] = newMap[x1--][y1];
            }
            x1 = 0;
            y1 = 1;
            while(y2 != C-1) {
                newMap[x2][y2++] = newMap[x1][y1++];
            }
            x1 = 1;
            y1 = C - 1;
            while(x2 != A-1) {
                newMap[x2++][y2] = newMap[x1++][y1];
            }
            x1 = A -1;
            y1 = C - 2;
            while(y2 != 0) {
                newMap[x2][y2--] = newMap[x1][y1--];
            }
            newMap[A-1][1] = 0; // ***
            newMap[A-1][0] = -1;


            // 아래쪽 공기 청정기
            // 1. 아래 -> 위
            x1 = A+1;
            y1 = 0;
            x2 = A;
            y2 = 0;
            while(x2 != R-1) {
                newMap[x2++][y2] = newMap[x1++][y1];
            }

            // 2. 오 -> 왼
            x1 = R-1;
            y1 = 1;
            while(y2 != C-1) {
                newMap[x2][y2++] = newMap[x1][y1++];
            }

            // 3. 위 -> 아래
            x1 = R-2;
            y1 = C-1;
            while(x2 != A) {
                newMap[x2--][y2] = newMap[x1--][y1];
            }

            // 4. 왼 -> 오
            x1 = A;
            y1 = C-2;
            while(y2 != 0) {
                newMap[x2][y2--] = newMap[x1][y1--];
            }
            newMap[A][0] = -1;
            newMap[A][1] = 0; //***

            // 결과를 맵에 할당
            map = newMap;
        }
        int ans = 2;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                ans += map[r][c];
            }
        }
        System.out.println(ans);

    }

}
