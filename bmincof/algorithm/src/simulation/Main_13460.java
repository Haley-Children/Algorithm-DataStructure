package simulation;

import java.util.Arrays;
import java.util.Scanner;

public class Main_13460 {
    static int n, m;
    static int minTilt = Integer.MAX_VALUE;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        char[][] map = new char[n][];

        // 지도 입력받기
        for (int i = 0; i < n; i++) {
            map[i] = sc.next().toCharArray();
        }

        for (int dir = 0; dir < 4; dir++) {
            bt(map, 0, dir);
        }
        // 모두 실패했으면 -1, 최소로 성공했으면 최솟값
        System.out.println(minTilt == Integer.MAX_VALUE ? -1 : minTilt + 1);
    }

    // 매 순간 각 방향으로 기울인 경우마다 가능한 모든 경우의 수 체크하는 함수
    static void bt(char[][] map, int k, int predir) {
        // 10번 이상 기울여도 아무것도 탈출하지 못하면 분기 종료
        if(k == 10) {
            return;
        }

        // dir : 0 (상), 1 (하), 2 (좌), 3 (우)
        for (int dir = 0; dir < 4; dir++) {
            if(dir == predir) continue;
            // 다음 단계로 넘겨줄 새로운 맵
            // 이번에 판을 dir 방향으로 기울였을 때 결과를 담고있다
            char[][] nMap = new char[n][];

            // 이전 단계의 맵 복사
            for (int i = 0; i < n; i++) {
                nMap[i] = Arrays.copyOf(map[i], m);
            }

            int result = tilt(nMap, dir);
            // 빨간 구슬이 먼저 탈출했으면 값을 갱신하고 해당 분기 종료
            if(result == 1) {
                minTilt = Math.min(minTilt, k);
                return;
                // 아무도 탈출하지 못했으면 다음 단계를 살펴본다
            } else if(result == 0) {
                // 파란 구슬이 나간 분기는 다음 단계를 볼 필요가 없다.
                bt(nMap, k + 1, dir);
            }

        }
    }

    // 보드를 dir 방향으로 기울이는 메서드
    // 빨간 구슬이 먼저 탈출했으면 1
    // 아무 구슬도 탈출하지 못했으면 0
    // 파란 구슬이 먼저 탈출했으면 -1
    // 같은 시간에 탈출했으면 -1
    // 두 구슬 중 하나도 움직이지 않았으면 -1
    static int tilt(char[][] map, int dir) {
        boolean redEscaped = false;
        boolean blueEscaped = false;

        boolean movedOnce = false;

        // dir : 0 (상), 1 (하), 2 (좌), 3 (우)
        switch (dir) {
            // 위로 기울이기
            case 0:
                // 모든 행/ 열 탐색하면서 더 먼저 굴러가는 것부터 굴리기
                for (int col = 0; col < m; col++) {
                    for (int row = 0; row < n; row++) {
                        // 구슬이면 위로 굴리기
                        if(map[row][col] == 'R' || map[row][col] == 'B') {
                            int curX = row;
                            int curY = col;
                            while(true) {
                                int nx = curX + dx[dir];
                                int ny = curY + dy[dir];

                                // 벽이나 구슬이 있으면 더 이상 굴러가지 않고 정지
                                if(map[nx][ny] == '#' || map[nx][ny] == 'R' || map[nx][ny] == 'B') {
                                    if(curX != row || curY != col) movedOnce = true;

                                    char tmp = map[row][col];
                                    map[row][col] = map[curX][curY];
                                    map[curX][curY] = tmp;

                                    break;
                                } else if(map[nx][ny] == 'O') {
                                    // 빨간 구슬이 탈출했다면
                                    if(map[row][col] == 'R') {
                                        map[row][col] = '.';
                                        redEscaped = true;
                                        // 파란 구슬이 탈출했다면
                                    } else {
                                        map[row][col] = '.';
                                        blueEscaped = true;
                                    }
                                    break;
                                }

                                // 더 굴러갈 수 있으면
                                curX = nx;
                                curY = ny;
                            }
                        }
                    }
                }
                // 아래로 기울이기
            case 1:
                for (int col = 0; col < m; col++) {
                    for (int row = n - 1; row >= 0; row--) {
                        // 구슬이면 위로 굴리기
                        if(map[row][col] == 'R' || map[row][col] == 'B') {
                            int curX = row;
                            int curY = col;
                            while(true) {
                                int nx = curX + dx[dir];
                                int ny = curY + dy[dir];

                                // 벽이면 더 이상 굴러가지 않고 정지
                                if(map[nx][ny] == '#' || map[nx][ny] == 'R' || map[nx][ny] == 'B') {
                                    if(curX != row || curY != col) movedOnce = true;

                                    char tmp = map[row][col];
                                    map[row][col] = map[curX][curY];
                                    map[curX][curY] = tmp;

                                    break;
                                } else if(map[nx][ny] == 'O') {
                                    // 빨간 구슬이 탈출했다면
                                    if(map[row][col] == 'R') {
                                        map[row][col] = '.';
                                        redEscaped = true;
                                        // 파란 구슬이 탈출했다면
                                    } else {
                                        map[row][col] = '.';
                                        blueEscaped = true;
                                    }
                                    break;
                                }

                                // 더 굴러갈 수 있으면
                                curX = nx;
                                curY = ny;
                            }
                        }
                    }
                }
                // 좌로 기울이기
            case 2:
                for (int row = 0; row < n; row++) {
                    for (int col = 0; col < m; col++) {
                        // 구슬이면 위로 굴리기
                        if(map[row][col] == 'R' || map[row][col] == 'B') {
                            int curX = row;
                            int curY = col;
                            while(true) {
                                int nx = curX + dx[dir];
                                int ny = curY + dy[dir];

                                // 벽이면 더 이상 굴러가지 않고 정지
                                if(map[nx][ny] == '#' || map[nx][ny] == 'R' || map[nx][ny] == 'B') {
                                    if(curX != row || curY != col) movedOnce = true;

                                    char tmp = map[row][col];
                                    map[row][col] = map[curX][curY];
                                    map[curX][curY] = tmp;

                                    break;
                                } else if(map[nx][ny] == 'O') {
                                    // 빨간 구슬이 탈출했다면
                                    if(map[row][col] == 'R') {
                                        map[row][col] = '.';
                                        redEscaped = true;
                                        // 파란 구슬이 탈출했다면
                                    } else {
                                        map[row][col] = '.';
                                        blueEscaped = true;
                                    }
                                    break;
                                }

                                // 더 굴러갈 수 있으면
                                curX = nx;
                                curY = ny;
                            }
                        }
                    }
                }
                // 우로 기울이기
            case 3:
                for (int row = 0; row < n; row++) {
                    for (int col = m - 1; col >= 0; col--) {
                        // 구슬이면 위로 굴리기
                        if(map[row][col] == 'R' || map[row][col] == 'B') {
                            int curX = row;
                            int curY = col;
                            while(true) {
                                int nx = curX + dx[dir];
                                int ny = curY + dy[dir];

                                // 벽이면 더 이상 굴러가지 않고 정지
                                if(map[nx][ny] == '#' || map[nx][ny] == 'R' || map[nx][ny] == 'B') {
                                    if(curX != row || curY != col) movedOnce = true;

                                    char tmp = map[row][col];
                                    map[row][col] = map[curX][curY];
                                    map[curX][curY] = tmp;

                                    break;
                                } else if(map[nx][ny] == 'O') {
                                    // 빨간 구슬이 탈출했다면
                                    if(map[row][col] == 'R') {
                                        map[row][col] = '.';
                                        redEscaped = true;
                                        // 파란 구슬이 탈출했다면
                                    } else {
                                        map[row][col] = '.';
                                        blueEscaped = true;
                                    }
                                    break;
                                }

                                // 더 굴러갈 수 있으면
                                curX = nx;
                                curY = ny;
                            }
                        }
                    }
                }
        }

        if(redEscaped && !blueEscaped) return 1;
        else if(blueEscaped) return -1;
        else if(!movedOnce) return -1;
        else return 0;
    }

}
