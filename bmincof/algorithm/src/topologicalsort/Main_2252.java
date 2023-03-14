package topologicalsort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2252 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 개수와 간선 개수 입력받기
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 인접 리스트 (directed)
        List<Integer>[] adj = new ArrayList[n+1];
        // indegree 입력받기
        int[] indegs = new int[n+1];

        // 간선 입력 받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(adj[from] == null) {
                adj[from] = new ArrayList<>();
            }

            adj[from].add(to);
            indegs[to]++;
        }

        // 위상 정렬 준비
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            // indegree가 0인 점들을 모두 담는다
            if(indegs[i] == 0) {
                q.offer(i);
            }
        }

        // 정렬 결과를 담을 스트링빌더
        StringBuilder sb = new StringBuilder();
        // 위상 정렬 시작
        while(!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(" ");

            // cur에서 갈 수 있는 노드의 indegree를 -1 (개념적으로 간선 끊기)
            if(adj[cur] == null) continue;

            for (int i = 0; i < adj[cur].size(); i++) {
                int next = adj[cur].get(i);

                // 현재 노드를 방문하면서 indegree가 0이 된 노드를 큐에 추가
                if(--indegs[next] == 0) {
                    q.offer(next);
                }
            }
        }

        System.out.println(sb);
    }
}
