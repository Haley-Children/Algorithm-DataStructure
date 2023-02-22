package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7569 {

    static class Point {
        int x; int y; int z;
        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][][] day = new int[h][n][m];
        int[][][] box = new int[h][n][m];
        int[] dx = {1,-1,0,0,0,0};
        int[] dy = {0,0,1,-1,0,0};
        int[] dz = {0,0,0,0,1,-1};
        int last = 1;
        boolean isImpo = false;

        Queue<Point> q = new LinkedList<>();

        for(int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if(box[i][j][k] == 1) {
                        day[i][j][k] = 1;
                        q.add(new Point(i,j,k));
                    }
                }
            }
        }

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for (int dir = 0; dir < 6; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                int nz = cur.z + dz[dir];

                if(nx < 0 || nx >= h || ny < 0 || ny >= n || nz < 0 || nz >= m) continue;
                if(box[nx][ny][nz] != 0 || day[nx][ny][nz] > 0) continue;

                box[nx][ny][nz] = 1;
                day[nx][ny][nz] = day[cur.x][cur.y][cur.z] + 1;
                last = Math.max(last, day[nx][ny][nz]);
                q.add(new Point(nx,ny,nz));
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if(box[i][j][k] == 0) {
                        isImpo = true;
                        break;
                    }
                }
            }
        }

        System.out.println(isImpo ? -1 : last-1);

    }
}

