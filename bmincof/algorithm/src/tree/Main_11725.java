package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11725 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer>[] adj = new ArrayList[n+1];
        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(adj[u] == null) {
                adj[u] = new ArrayList<>();
            }
            if(adj[v] == null) {
                adj[v] = new ArrayList<>();
            }

            adj[u].add(v);
            adj[v].add(u);
        }

        int[] parents = new int[n+1];
        Arrays.fill(parents, -1);
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        parents[1] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if(adj[cur] == null) continue;
            for (int i = 0; i < adj[cur].size(); i++) {
                int next = adj[cur].get(i);
                // 부모 노드이면
                if(parents[next] != -1) continue;
                parents[next] = cur;
                queue.offer(next);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb);
    }
}
