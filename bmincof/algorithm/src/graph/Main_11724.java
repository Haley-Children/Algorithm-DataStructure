package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_11724 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 개수
        int n = Integer.parseInt(st.nextToken());
        // 간선 개수
        int m = Integer.parseInt(st.nextToken());

        // 각 정점의 degree
        int[] degrees = new int[n+1];
        // 간선 정보들
        int[][] edges = new int[m][2];
        // 인접 리스트
        int[][] adj = new int[n+1][];
        // adj[i]의 새로운 추가 위치
        int[] idx = new int[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());

            degrees[edges[i][0]]++;
            degrees[edges[i][1]]++;
        }

        for (int i = 1; i <= n; i++) {
            adj[i] = new int[degrees[i]];
        }

        for (int i = 0; i < m; i++) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];

            // 양방향이므로
            adj[v1][idx[v1]++] = v2;
            adj[v2][idx[v2]++] = v1;
        }

        // dfs해서 connected component 찾기
        boolean[] vis = new boolean[n+1];
        Deque<Integer> stack = new ArrayDeque<>();

        // connected component 개수
        int ccCnt = 0;
        // 재귀 호출 순서로 방문하도록
        for (int i = 1; i <= n; i++) {
            if(vis[i]) continue;

            ccCnt++;
            stack.push(i);
            while(!stack.isEmpty()) {
                int cur = stack.pop();

                if(vis[cur]) continue;

                vis[cur] = true;
                for(int next : adj[cur]) {
                    if(vis[next]) continue;
                    stack.push(next);
                }
            }
        }

        System.out.println(ccCnt);
    }
}
