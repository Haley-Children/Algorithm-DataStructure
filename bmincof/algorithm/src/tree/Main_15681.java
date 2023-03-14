package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15681 {
    /*
        1. adj 리스트로 그래프 정보 저장
        2. 루트부터 bfs 하면서 트리 만들기(부모 정보 저장)
        3. 각 정점의 부모로 이동하면서 방문하는 정점 서브트리의 크기 + 1
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        List<Integer>[] adj = new ArrayList[n+1];
        // 간선의 개수 = n-1
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if(adj[u] == null) {
                adj[u] = new ArrayList<>();
            }
            if (adj[v] == null){
                adj[v] = new ArrayList<>();
            }

            adj[u].add(v);
            adj[v].add(u);
        }

        // dfs로 트리 만들기(부모 노드 저장)
        int[] parents = new int[n+1];

        Deque<Integer> treeS = new LinkedList<>();
        Deque<Integer> subS = new LinkedList<>();
        boolean[] vis = new boolean[n+1];

        treeS.push(root);
        subS.push(root);
        vis[root] = true;

        while(!treeS.isEmpty()) {
            int cur = treeS.pop();

            for (int i = 0; i < adj[cur].size(); i++) {
                int next = adj[cur].get(i);

                // 방문한 적 있는 노드면 건너뛰기
                if(vis[next]) continue;
                // next노드의 부모를 현재 노드로 설정
                parents[next] = cur;
                vis[next] = true;
                treeS.push(next);
                subS.push(next);
            }
        }

        // i번 노드가 루트인 서브트리의 크기
        int[] sizeOfSubtree = new int[n+1];
        // 만들어진 트리의 각 정점마다 루트로 거슬러올라가면서 서브트리 개수 + 1
        while(!subS.isEmpty()) {
            int cur = subS.pop();

            sizeOfSubtree[parents[cur]] += ++sizeOfSubtree[cur];
        }

        for (int i = 0; i < q; i++) {
            int query = Integer.parseInt(br.readLine());

            sb.append(sizeOfSubtree[query]).append("\n");
        }

        System.out.println(sb);
    }
}
