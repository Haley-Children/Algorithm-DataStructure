import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_7562 {
    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int l = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            Queue<Point> q = new LinkedList<>();

            int[][] vis = new int[l][l];

            int[] dx = {-1,-2,-2,-1,1,2,2,1};
            int[] dy = {-2,-1,1,2,2,1,-1,-2};

            vis[startX][startY] = 1;
            q.add(new Point(startX, startY));

            while(!q.isEmpty()) {
                Point cur = q.poll();

                for (int dir = 0; dir < 8; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];

                    if(nx < 0 || nx >= l || ny < 0 || ny >= l) continue;
                    if(vis[nx][ny] != 0) continue;

                    vis[nx][ny] = vis[cur.x][cur.y] + 1;
                    q.add(new Point(nx, ny));
                }

            }

            System.out.println(vis[endX][endY] - 1);

        }
    }

}


