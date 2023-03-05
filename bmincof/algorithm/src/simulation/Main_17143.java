package boj;

import java.util.Scanner;

public class Main_17143 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] dr = {0,-1,1,0,0};
        int[] dc = {0,0,0,1,-1};

        // 행, 열의 크기
        int R = sc.nextInt();
        int C = sc.nextInt();
        // 상어 수
        int m = sc.nextInt();

        // 상어의 위치를 저장하는 배열
        Shark[][] map = new Shark[R][C];

        for (int i = 0; i < m; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int speed = sc.nextInt();
            int direction = sc.nextInt();
            int size = sc.nextInt();

            // 상어 정보 저장
            // 처음에는 같은 칸에 상어 중복으로 없음
            map[r][c] = new Shark(direction, speed, size);
        }

        int fisherMan = 0;
        // 잡은 상어의 사이즈 총합
        int total = 0;

        while (fisherMan < C) {

            // 낚시하기
            for (int i = 0; i < R; i++) {
                // 상어가 있으면
                if(map[i][fisherMan] != null) {
                    total += map[i][fisherMan].size;
                    map[i][fisherMan] = null;
                    break;
                }
            }

            Shark[][] newMap = new Shark[R][C];
            // 상어 이동
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if(map[i][j] != null) {
                        Shark shark = map[i][j];

                        int dir = shark.dir;

                        int move = 0;
                        int totalMove = shark.speed % (2 * (dir < 3 ? R - 1 : C - 1));

                        int nr = i;
                        int nc = j;

                        while(move < totalMove) {
                            // 양 끝이면 방향 전환
                            if(dir == 1 && nr == 0 || dir == 2 && nr == R - 1) {
                                // dir 이 2 또는 4이면
                                dir = dir != 1 ? 1 : 2;
                            } else if(dir == 3 && nc == C - 1 || dir == 4 && nc == 0) {
                                dir = dir != 3 ? 3 : 4;
                            }
                            nr += dr[dir];
                            nc += dc[dir];

                            move++;
                        }

                        shark.dir = dir;

                        if(newMap[nr][nc] == null || newMap[nr][nc].size < shark.size) {
                            newMap[nr][nc] = shark;
                        }
                    }
                }
            }

            // 맵 교체
            map = newMap;

            // 낚시왕 이동
            fisherMan++;
        }

        System.out.println(total);
    }

    static class Shark {
        int dir;
        int speed;
        int size;

        public Shark(int dir, int speed, int size) {
            this.dir = dir;
            this.speed = speed;
            this.size = size;
        }
    }
}
