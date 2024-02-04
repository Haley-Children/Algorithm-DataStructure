package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7562 {

    static int[][] arr, vis;
    static int l;
    static int x,y, tx, ty;
    static int[] dix = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] diy = {1, 2, 2, 1, -1, -2, -2, -1};
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            l = Integer.parseInt(br.readLine());
            arr = new int[l][l];
            vis = new int[l][l];

            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            tx = Integer.parseInt(st.nextToken());
            ty = Integer.parseInt(st.nextToken());

            bfs(x,y);
        }
        System.out.println(sb);
    }

    // nx, ny에서 체크하면 왜 안되지
    private static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        vis[i][j] = 1; // 1로 설정했기 때문에 마지막에 -1

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == tx && cur[1] == ty) {
                sb.append(vis[cur[0]][cur[1]] -1).append("\n");
                return;
            }
            for (int k = 0; k < 8; k++) {
                int nx = dix[k] + cur[0];
                int ny = diy[k] + cur[1];

                if (nx < 0 || nx >= l || ny < 0 || ny >= l || vis[nx][ny] != 0) continue;
                queue.offer(new int[] {nx,ny});
                vis[nx][ny] = vis[cur[0]][cur[1]] + 1;
            }
        }
    }
}
