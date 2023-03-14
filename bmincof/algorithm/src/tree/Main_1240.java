package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1240 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 노드 개수
        int n = Integer.parseInt(st.nextToken());
        // 쿼리 개수
        int m = Integer.parseInt(st.nextToken());

        // 트리를 무방향 그래프로 표현할 수 있다
        // -> 노드 사이의 거리를 무방향 가중치 그래프로 표현
        // 끊어진 간선은 MAX_VALUE로 표현
        List<Integer>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        int[][] dist = new int[n+1][n+1];

        // n-1 개 줄에 간선 정보
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 간선 연결
            adj[u].add(v);
            adj[v].add(u);

            dist[u][v] = dist[v][u] = weight;
        }

        // 트리의 각 정점까지의 경로는 유일하므로 거리도 유일하다
        // 각 정점부터 다른 정점까지 거리를 bfs로 모두 계산해놓기
        // dist[시작점][도착점] : 시작점부터 도착점까지의 거리
        for (int i = 1; i <= n; i++) {
            Queue<Integer> q = new ArrayDeque<>();
            boolean[] vis = new boolean[n+1];

            q.offer(i);
            vis[i] = true;

            while(!q.isEmpty()) {
                int cur = q.poll();

                for (int next : adj[cur]) {
                    if(vis[next] || next == cur) continue;

                    // i번 노드(시작노드)부터 next 노드 까지 이동해야하는 거리
                    if(dist[i][next] == 0) {
                        dist[i][next] = dist[i][cur] + dist[cur][next];
                    }

                    vis[next] = true;
                    q.offer(next);
                }
            }
        }

        // 쿼리마다 거리 출력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            sb.append(dist[from][to]).append("\n");
        }

        System.out.println(sb);
    }
}
