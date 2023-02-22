package backtracking;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main_1941 {
    static final int n = 5;
    static final int k = 7;

    static char[][] map = new char[n][];
    static boolean[][] isUsed = new boolean[n][n];

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static int total = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < n; i++) {
            map[i] = sc.next().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                isUsed[i][j] = true;
                bt(i, j, 1);
                isUsed[i][j] = false;
            }
        }

        System.out.println(total);
    }

    // row, col : 이전에 고른 사람의 좌표
    // level : 선택한 사람의 수
    static void bt(int row, int col, int level) {
        // 7명을 뽑았을 때
        if(level == k) {
            total += bfs(row, col) ? 1 : 0;
            return;
        }

        // 이전에 선택한 행의 열보다 한 칸 뒤에서부터 남은 칸 확인
        for (int j = col + 1; j < n; j++) {
            // 이번에 사용한 곳 표시
            isUsed[row][j] = true;
            // 다음 단계로 넘어가서 반복
            bt(row, j, level + 1);
            // 사용이 끝났으므로 표시 복구
            isUsed[row][j] = false;
        }

        // 그 다음 행부터 다시 모두 확인
        for (int i = row + 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 이번에 사용한 곳 표시
                isUsed[i][j] = true;
                // 다음 단계로 넘어가서 반복
                bt(i, j, level + 1);
                // 사용이 끝났으므로 표시 복구
                isUsed[i][j] = false;
            }
        }
    }

    static boolean bfs(int row, int col) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[n][n];

        q.offer(new int[] {row, col});
        vis[row][col] = true;

        int count = map[row][col] == 'S' ? 1 : 0;
        int area = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(vis[nx][ny] || !isUsed[nx][ny]) continue;

                count += map[nx][ny] == 'S' ? 1 : 0;
                area++;

                vis[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }
        }

        return area == 7 && count >= 4;
    }
}
