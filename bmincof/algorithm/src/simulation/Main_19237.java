package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19237 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 맵 크기
        int n = Integer.parseInt(st.nextToken());
        // 상어 수
        int m = Integer.parseInt(st.nextToken());
        // 냄새 지속시간
        int k = Integer.parseInt(st.nextToken());

        // 위, 아래, 왼쪽, 오른쪽
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        // 좌표마다 상어번호, 냄새 시간
        int[][][] smells = new int[n][n][2];
        // [i번 상어]의 [보고 있는 방향]에서 [탐색 우선순위]
        int[][][] sharkInfo = new int[m+1][4][4];
        // 상어의 마지막 위치, 방향
        // r, c, 방향(-1이면 쫓겨남)
        int[][] sharks = new int[m+1][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                // 상어 위치, 냄새 저장
                smells[i][j][0] = Integer.parseInt(st.nextToken());
                if(smells[i][j][0] != 0) {
                    // 냄새 정보저장
                    smells[i][j][1] = k;
                    sharks[smells[i][j][0]][0] = i;
                    sharks[smells[i][j][0]][1] = j;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        // 보고있는 방향 저장
        for (int i = 1; i <= m; i++) {
            sharks[i][2] = Integer.parseInt(st.nextToken()) - 1;
        }


        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < 4; l++) {
                    sharkInfo[i][j][l] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        int time = 0;
        int leftShark = m;
        while(time <= 1000 && leftShark > 1) {
            int[][][] newSmells = new int[n][n][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(smells[i][j][0] != 0) {
                        newSmells[i][j][0] = smells[i][j][0];
                        newSmells[i][j][1] = smells[i][j][1];
                    }
                }
            }
            // 모든 상어 이동
            for (int i = 1; i <= m; i++) {
                // 쫓겨난 상어가 아니면 이동 시작
                if(sharks[i][2] != -1) {
                    int sharkDir = sharks[i][2];
                    int[] dirs = sharkInfo[i][sharkDir];

                    boolean isMoved = false;

                    // 빈칸이 있는지 먼저 탐색
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = sharks[i][0] + dx[dirs[dir]];
                        int ny = sharks[i][1] + dy[dirs[dir]];

                        System.out.println("상어번호 : " + i + "빈 칸 탐색");
                        System.out.println("현재 보는 칸 : " + nx + ", " + ny);

                        if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                        // 아무도 냄새를 묻힌 적 없으면
                        if(smells[nx][ny][0] == 0 && newSmells[nx][ny][0] == 0) {
                            // 냄새 저장
                            newSmells[nx][ny][0] = i;
                            newSmells[nx][ny][1] = k + 1;

                            // 이동한 후의 상어 정보 저장
                            sharks[i][0] = nx;
                            sharks[i][1] = ny;
                            sharks[i][2] = dirs[dir];
                        // 같은 시간대에 냄새를 묻힌 거면
                        } else if(smells[nx][ny][0] == 0 && newSmells[nx][ny][1] == k + 1){
                            // 쫓겨났다고 표시
                            sharks[i][2] = -1;
                            leftShark--;
                        } else {
                            continue;
                        }

                        isMoved = true;
                        break;
                    }

                    // 빈칸으로 이동할 수 없으면 자기 냄새가 있는 칸으로
                    if(!isMoved) {
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = sharks[i][0] + dx[dirs[dir]];
                            int ny = sharks[i][1] + dy[dirs[dir]];

                            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                            if(smells[nx][ny][0] == i) {
                                sharks[i][0] = nx;
                                sharks[i][1] = ny;
                                sharks[i][2] = dirs[dir];

                                // 냄새 초기화
                                newSmells[nx][ny][1] = k + 1;
                                break;
                            }
                        }
                    }
                    System.out.println("상어번호 : " + i + ", nx : " + sharks[i][0] + ", ny : " + sharks[i][1] + ", dir : " + sharks[i][2]);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(newSmells[i][j][0] != 0 && --newSmells[i][j][1] == 0) {
                        newSmells[i][j][0] = 0;
                    }
                }
            }

            smells = newSmells;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print("[" +smells[i][j][0] + ", " + smells[i][j][1] + "] ");
                }
                System.out.println();
            }
            System.out.println();
            time++;
        }

        System.out.println(time > 1000 ? -1 : time);
    }
}

