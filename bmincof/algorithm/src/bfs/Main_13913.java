package bfs;

import java.util.*;

public class Main_13913 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] dx = {-1, 1, 2};

        // 방문했을 때 시간
        int[] vis = new int[100010];
        // i까지 도달하는데 거쳐온 경로
        int[] path = new int[100010];

        Queue<Integer> q = new ArrayDeque<>();
        vis[n] = 1;
        path[n] = -1;
        q.offer(n);

        bfs:
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int dir = 0; dir < 3; dir++) {
                int nx = cur;

                if(dir == 2) {
                    nx *= dx[dir];
                } else {
                    nx += dx[dir];
                }

                if(nx < 0 || nx > 100000) continue;
                if(vis[nx] != 0) continue;

                vis[nx] = vis[cur] + 1;
                path[nx] = cur;

                if(nx == k) break bfs;
                q.offer(nx);
            }
        }

        sb.append(vis[k] - 1).append("\n");

        List<Integer> route = new ArrayList<>();

        int cur = k;
        while(cur != -1) {
            route.add(cur);
            cur = path[cur];
        }

        for (int i = route.size() - 1; i >= 0; i--) {
            sb.append(route.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}
