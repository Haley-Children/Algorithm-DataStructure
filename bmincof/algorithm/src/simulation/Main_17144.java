package simulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_17144 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();
        // 공기청정기 작동 시간
        int t = sc.nextInt();

        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};

        // 미세먼지의 양
        int[][] map = new int[r][c];
        // 공기청정기의 위치
        int[] filter = new int[2];

        // 미세먼지 정보 입력
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = sc.nextInt();
                // 공기청정기 위치 저장
                if(map[i][j] == -1) {
                    if(filter[0] == 0) {
                        filter[0] = i;
                    } else {
                        filter[1] = i;
                    }
                }
            }
        }

        // 확산을 위한 큐
        Queue<int[]> diffuseQ = new LinkedList<>();
        int time = 0;
        while(time < t) {
            // 미세먼지 확산
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    // 미세먼지가 있으면
                    if (map[i][j] > 0) {
                        // 미세먼지가 확산되는 양
                        int amount = map[i][j] / 5;

                        for (int dir = 0; dir < 4; dir++) {
                            int nx = i + dx[dir];
                            int ny = j + dy[dir];

                            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                            if (map[nx][ny] == -1) continue;

                            diffuseQ.offer(new int[]{nx, ny, amount});
                            // 미세먼지 퍼진만큼 차감
                            map[i][j] -= amount;
                            // 미세먼지가 0 이하면 0으로 변경
                            map[i][j] = Math.max(map[i][j], 0);
                        }

                    }
                }
            }

            while(!diffuseQ.isEmpty()) {
                int[] cur = diffuseQ.poll();

                map[cur[0]][cur[1]] += cur[2];
            }

            // 공기 순환
            // 청정기 위쪽부터
            int dir = 0;
            int row = filter[0] + dx[dir];
            int col = dy[dir];

            // 필터로 돌아올 때까지
            while(!(row == filter[0] && col == 0)) {
                // 이전 칸으로 값 전달
                // 이전 칸이 청정기면 먼지 삭제
                if(row - dx[dir] == filter[0] && col == 0) {
                    map[row][col] = 0;
                // 아니면 전달
                } else {
                    map[row - dx[dir]][col - dy[dir]] = map[row][col];
                    map[row][col] = 0;
                }

                // 바람 방향이 바뀌어야 하는 위치에서 델타 변경
                if(row == 0 && col == 0 || row == 0 && col == c - 1 || row == filter[0] && col == c - 1) {
                    dir = (dir + 1) % 4;
                }
                row += dx[dir];
                col += dy[dir];
            }

            // 청정기 아래
            dir = 2;
            row = filter[1] + dx[dir];
            col = dy[dir];

            // 필터로 돌아올 때까지
            while(!(row == filter[1] && col == 0)) {
                // 이전 칸으로 값 전달
                // 이전 칸이 청정기면 먼지 삭제
                if(row - dx[dir] == filter[1] && col == 0) {
                    map[row][col] = 0;
                    // 아니면 전달
                } else {
                    map[row - dx[dir]][col - dy[dir]] = map[row][col];
                    map[row][col] = 0;
                }

                // 바람 방향이 바뀌어야 하는 위치에서 델타 변경
                if(row == r - 1 && col == 0 || row == r - 1 && col == c - 1 || row == filter[1] && col == c - 1) {
                    dir = (dir - 1 + 4) % 4;
                }
                row += dx[dir];
                col += dy[dir];
            }

            time++;
        }


        int left = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j] > 0) {
                    left += map[i][j];
                }
            }
        }
        System.out.println(left);
    }
}
