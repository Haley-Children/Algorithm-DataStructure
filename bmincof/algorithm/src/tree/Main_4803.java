package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_4803 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int caseNum = 0;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 정점의 개수, 간선의 개수
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            boolean[][] adj = new boolean[n+1][n+1];
            // edge 연결
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                adj[u][v] = true;
                adj[v][u] = true;
            }

            int numOfTrees = 0;
            boolean[] vis = new boolean[n+1];
            // dfs로 사이클이 있는지 판단
            for (int i = 1; i <= n; i++) {
                if(vis[i]) continue;
                Deque<int[]> stack = new LinkedList<>();

                vis[i] = true;
                stack.push(new int[] {i, i});
                boolean hasCycle = false;

                while (!stack.isEmpty() && !hasCycle) {
                    // cur[0] : 현재 점의 값, cur[1] : 부모 노드의 값
                    int[] cur = stack.pop();

                    vis[cur[0]] = true;
//                    System.out.println("cur : " + cur[0]);

                    for (int next = n; next >= 1; next--) {
                        // cur와 next 사이에 edge가 없다면 건너뜀
                        if(!adj[cur[0]][next] || next == cur[1]) continue;
                        // 부모 노드가 아닌 이미 방문한 적 있는 곳으로 연결되면 사이클이 있는 것
                        if(vis[next]) {
//                            System.out.println("next : " + next);
                            hasCycle = true;
                            continue;
                        }

                        stack.push(new int[] {next, cur[0]});
                    }
                }

                if (!hasCycle) {
                    numOfTrees++;
                }
            }
            sb.append("Case ").append(++caseNum).append(": ");

            if(numOfTrees == 0) {
                sb.append("No trees.\n");
            } else if(numOfTrees == 1) {
                sb.append("There is one tree.\n");
            } else {
                sb.append("A forest of ").append(numOfTrees).append(" trees.\n");
            }
        }

        System.out.println(sb);
    }
}
