package boj;

import java.util.Arrays;
import java.util.Scanner;

/*
    1. 물고기 위치, 방향을 저장할 배열 fish[16][3] 행, 열, 방향
    2. 상어 위치 저장할 배열 shark[4] 행, 열, 방향, 먹은 물고기 번호 합
    3. 반시계방향 벡터 dx, dy
    4. 먹은 물고기 번호의 합의 최댓값
    5. 위치별 물고기 번호를 저장할 2차원 배열
    메서드
    1. bt() : 물고기를 하나 선택할 때마다 분기가 갈라짐
    더 이상 물고기를 못 먹으면 먹은 물고기 합 갱신
    2. 물고기 이동
    3. 상어 이동
 */
public class Main_19236 {
    // 맵 크기
    static int n = 4;
    // 물고기 수
    static int m = 16;
    static int maxEaten;

    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,-1,-1,-1,0,1,1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 물고기 번호를 저장할 맵
        int[][] map = new int[n][n];
        // 물고기 행, 열, 방향
        int[][] fish = new int[m+1][3];
        // 상어 행, 열, 방향, 먹은 물고기 번호 합
        int[] shark = new int[4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int fishNum = sc.nextInt();
                fish[fishNum][0] = i;
                fish[fishNum][1] = j;
                fish[fishNum][2] = sc.nextInt() - 1;

                map[i][j] = fishNum;
            }
        }

        // 0, 0의 물고기 먹고 시작
        shark[0] = 0;
        shark[1] = 0;
        // 먹은 물고기의 방향 가지기
        shark[2] = fish[map[0][0]][2];
        // 먹은 물고기의 번호 합 누적
        maxEaten = shark[3] += map[0][0];

        // 먹힌 물고기 방향 -1 (죽은 물고기 표시)
        fish[map[0][0]][2] = -1;
        // 먹힌 물고기 지도에서 지우기, 상어위치로 표시
        map[0][0] = -1;

        System.out.println("초기 물고기 상어 위치");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // 분기 나누면서 물고기 먹기 시작
        bt(map, shark, fish);

        System.out.println(maxEaten);
    }

    static void bt(int[][] map, int[] shark, int[][] fish) {
        // 물고기 번호 순으로 이동 시작 (한 칸 이동)
        for (int fishNum = 1; fishNum <= m; fishNum++) {
            if(fish[fishNum][2] == -1) continue;
            // 해당 번호 물고기 정보 꺼내기
            int r = fish[fishNum][0];
            int c = fish[fishNum][1];
            int direction = fish[fishNum][2];

            // 빈 칸 또는 다른 물고기가 있는 칸으로 이동
            // 없으면 이동 하지 않기
            for (int dir = 0; dir < 8; dir++) {
                // 이동할 수 있는 칸을 찾을 때까지 반시계방향으로 회전
                int nr = r + dx[(direction + dir) % 8];
                int nc = c + dy[(direction + dir) % 8];

                // 상어가 있거나 경계 밖이면 이동 불가능
                if(nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == -1) continue;

                // 빈 칸이면 이동
                if(map[nr][nc] == 0) {
                    map[nr][nc] = fishNum;
                    map[r][c] = 0;

                    fish[fishNum][0] = nr;
                    fish[fishNum][1] = nc;
                    fish[fishNum][2] = (direction + dir) % 8;
                    // 다른 물고기가 있으면 자리 변경
                } else {
                    map[r][c] = map[nr][nc];
                    fish[map[nr][nc]][0] = r;
                    fish[map[nr][nc]][1] = c;

                    map[nr][nc] = fishNum;
                    fish[fishNum][0] = nr;
                    fish[fishNum][1] = nc;
                    fish[fishNum][2] = (direction + dir) % 8;
                }
                break;
            }
        }

        System.out.println("물고기 이동 후 위치");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        // 물고기가 이동이 끝나면 상어가 이동
        int sharkR = shark[0];
        int sharkC = shark[1];
        int sharkDir = shark[2];

        int nr = sharkR + dx[sharkDir];
        int nc = sharkC + dy[sharkDir];

        while (0 <= nr && nr < n && 0 <= nc && nc < n) {
            // 바라보고 있는 방향 중 물고기가 있는 칸으로 이동할 수 있다.

            // nr, nc에 물고기가 있는지 확인
            // 물고기가 없으면 같은 방향 다음 칸 이동
            if(map[nr][nc] != 0) {
                // 분기 별 정보를 기억하기 위해 맵 복사해서 진행
                int[][] newMap = new int[n][n];
                for (int i = 0; i < n; i++) {
                    newMap[i] = Arrays.copyOf(map[i], n);
                }

                int[][] newFish = new int[m+1][3];
                for (int i = 1; i <= m; i++) {
                    newFish[i] = Arrays.copyOf(fish[i], 3);
                }

                int[] newShark = new int[4];

                // 물고기가 있는 칸에 도착하면 물고기를 먹고 그 방향을 가진다.
                newShark[0] = nr;
                newShark[1] = nc;
                newShark[2] = newFish[newMap[nr][nc]][2];
                newShark[3] = shark[3] + newMap[nr][nc];

                // 먹힌 물고기 표시
                newFish[newMap[nr][nc]][2] = -1;
                // 원래 상어 위치 빈 칸으로
                newMap[sharkR][sharkC] = 0;
                // 이동한 위치 상어로 표시
                newMap[nr][nc] = -1;

                System.out.println("상어 이동 위치");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(newMap[i][j] + " ");
                    }
                    System.out.println();
                }

                System.out.println("상어가 먹은 번호 합 : " + newShark[3]);

                // 최대 번호 합 갱신
                maxEaten = Math.max(maxEaten, newShark[3]);

                // 다음 단계로 넘어감
                bt(newMap, newShark, newFish);
            }

            nr += dx[sharkDir];
            nc += dy[sharkDir];
        }
    }
}

