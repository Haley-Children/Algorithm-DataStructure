package mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1197 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 수
        int n = Integer.parseInt(st.nextToken());
        // 간선의 수
        int m = Integer.parseInt(st.nextToken());

        // [i]번 정점의 간선 정보를 저장
        // 간선 : [이어진 정점, 가중치]
        List<int[]>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        // 그래프 만들기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[u].add(new int[] {v, weight});
            adj[v].add(new int[] {u, weight});
        }

        // 가중치가 작은 것부터 꺼내는 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o1[0] < o2[0] ? -1 : 1;
                } else {
                    return o1[1] < o2[1] ? -1 : 1;
                }
            }
        });

        // 1번 정점에 연결된 간선들 모두 저장
        pq.addAll(adj[1]);

        // 신장트리에 포함된 노드인지 확인하는 배열
        // 1이면 트리에 포함
        boolean[] isTree = new boolean[n+1];
        isTree[1] = true;

        // 선택한 간선의 개수
        int edgeCnt = 0;
        // 총 가중치
        int totalWeight = 0;

        while (!pq.isEmpty() && edgeCnt < n - 1) {
            int[] curEdge = pq.poll();

            int next = curEdge[0];
            // 목적지 노드가 아직 포함되지 않았다면
            if(!isTree[next]) {
                // 그룹에 포함
                isTree[next] = true;
                // 가중치 더하기
                totalWeight += curEdge[1];
                // 간선 개수 + 1
                edgeCnt++;
                // 새로 포함시킨 정점에 연결된 간선들 모두 큐에 넣기
                pq.addAll(adj[next]);
            }
        }

        System.out.println(totalWeight);
    }
}
