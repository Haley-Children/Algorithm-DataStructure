package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_11403 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[][] adj = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int from = 0; from < n; from++) {
            for (int to = 0; to < n; to++) {
                boolean[] vis = new boolean[n];
                Deque<Integer> stack = new ArrayDeque<>();
                stack.push(from);

                while(!stack.isEmpty()) {
                    int cur = stack.pop();

                    for (int next = 0; next < n; next++) {
                        if(adj[cur][next] == 0 || vis[next]) continue;

                        vis[next] = true;
                        stack.push(next);
                    }
                }

                sb.append(vis[to] ? 1 : 0).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
