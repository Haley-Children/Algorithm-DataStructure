import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012 {

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        final int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            Queue<Point> q = new LinkedList<>();
            int count = 0;

            int[][] cabbages = new int[n][m];
            int[][] vis = new int[n][m];

            int[] dx = {1,-1,0,0};
            int[] dy = {0,0,1,-1};

            // initialize
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                cabbages[r][c] = 1;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(cabbages[i][j] == 1 && vis[i][j] == 0) {
                        vis[i][j] = 1;
                        q.add(new Point(i, j));

                        while(!q.isEmpty()) {
                            Point cur = q.poll();

                            for (int dir = 0; dir < 4; dir++) {
                                int nx = cur.x + dx[dir];
                                int ny = cur.y + dy[dir];

                                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                                if(cabbages[nx][ny] == 0 || vis[nx][ny] != 0) continue;

                                vis[nx][ny] = 1;
                                q.add(new Point(nx, ny));
                            }

                        }

                        count++;
                    }

                }

            }

            System.out.println(count);

        }
    }

}

