package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


public class Main_10026 {

    static int n;
    static char[][] arr;
    static boolean[][] vis, visRG;
    static int[] dix = {-1, 0, 1, 0};
    static int[] diy = {0, 1, 0, -1};
    static int normalCnt, specificCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        vis = new boolean[n][n];
        visRG = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.charAt(j); // ************* 입출력 실수!!
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j]) continue;
                bfs(i,j, vis);
                normalCnt++;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 'R') {
                    arr[i][j] = 'G';
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visRG[i][j]) continue;
                bfs(i,j, visRG);
                specificCnt++;
            }
        }

        System.out.println(normalCnt + " " + specificCnt);
    }

    private static void bfs(int i, int j, boolean[][] vis) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(i,j));
        vis[i][j] = true;

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = dix[k] + cur.x;
                int ny = diy[k] + cur.y;

                if (nx < 0 || nx >= n || ny <0 || ny >= n || vis[nx][ny]) continue;
                if (arr[nx][ny] != arr[cur.x][cur.y]) continue;
                queue.offer(new Pos(nx,ny));
                vis[nx][ny] = true;
            }
        }
    }

}
