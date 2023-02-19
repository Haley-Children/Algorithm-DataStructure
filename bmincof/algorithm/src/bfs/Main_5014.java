package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5014 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int F = Integer.parseInt(st.nextToken());
        final int S = Integer.parseInt(st.nextToken());
        final int G = Integer.parseInt(st.nextToken());
        final int U = Integer.parseInt(st.nextToken());
        final int D = Integer.parseInt(st.nextToken());

        if(S == G) {
            System.out.println(0);
            return;
        }

        // F층 크기의 방문정보 배열 (1-indexed)
        // 1층~F층 + 패딩
        int[] vis = new int[F+2];
        // BFS를 위한 큐
        Queue<Integer> q = new ArrayDeque<>();
        // 각 시점에서 이동할 수 있는 방향
        int[] dx = {U,-D};

        vis[S] = 1;
        q.add(S);

        while(!q.isEmpty()) {
            int cur = q.poll();

            // 위 아래로 모두 이동
            for (int dir = 0; dir < 2; dir++) {
                int nx = cur + dx[dir];

                // 건물 층수 내가 아니면 스킵
                if(nx < 1 || nx > F) continue;
                // 방문한 적 있는 층이면 스킵
                if(vis[nx] != 0) continue;

                vis[nx] = vis[cur] + 1;
                q.offer(nx);
            }
        }

        System.out.println(vis[G] == 0 ? "use the stairs" : vis[G] - 1);
    }
}

