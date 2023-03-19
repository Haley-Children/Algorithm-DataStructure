package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5567 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean[][] adj = new boolean[n+1][n+1];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u][v] = adj[v][u] = true;
        }

        int[] dist = new int[n+1];
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(1);
        dist[1] = 1;

        int count = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int j = 2; j <= n; j++) {
                if(dist[j] > 0 || !adj[cur][j]) continue;

                dist[j] = dist[cur] + 1;
                if(dist[j] < 3) q.offer(j);
                count++;
            }
        }

        System.out.println(count);
    }
}
