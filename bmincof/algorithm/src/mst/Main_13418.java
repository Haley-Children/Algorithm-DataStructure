package mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_13418 {
    // disjoint-set의 대표
    static int[] group;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수
        int V = Integer.parseInt(st.nextToken());
        // 간선의 개수 + 입구와 1번 도로
        int E = Integer.parseInt(st.nextToken());

        group = new int[V+1];
        initGroup(V);

        // 가중치가 높은 간선부터 꺼낼 우선순위 큐
        PriorityQueue<Edge> maxPq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.weight == o2.weight) return o1.u - o2.u;

                return o2.weight - o1.weight;
            }
        });

        // 가중치가 낮은 간선부터 꺼낼 우선순위 큐
        PriorityQueue<Edge> minPq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.weight == o2.weight) return o1.u - o2.u;

                return o1.weight - o2.weight;
            }
        }
        );

        // 입구와 1번 도로 사이의 가중치
        int enter = 1 - Integer.parseInt(br.readLine().split(" ")[2]);

        // 간선 입력받기
        while(E-- > 0) {
            st = new StringTokenizer(br.readLine());

            // 오르막이면 가중치가 1로 생각
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = 1 - Integer.parseInt(st.nextToken());

            Edge e = new Edge(u, v, w);
            maxPq.offer(e);
            minPq.offer(e);
        }

        // 최악의 경우
        int high = enter;
        int sel = 0;
        while(sel < V-1) {
            Edge cur = maxPq.poll();

            // 이미 같은 그룹이면 건너뛰기
            if(!union(cur.u, cur.v)) continue;

            // 처음 합치는 정점이면
            sel++;
            high += cur.weight;
        }

        initGroup(V);
        // 최적의 경우
        int low = enter;
        sel = 0;
        while(sel < V-1) {
            Edge cur = minPq.poll();

            if(!union(cur.u, cur.v)) continue;

            sel++;
            low += cur.weight;
        }

        // 피로도 차이 계산
        System.out.println(high*high - low*low);
    }

    static class Edge {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

    }

    static boolean union(int u, int v) {
        u = find(u);
        v = find(v);

        if(u == v) return false;

        group[v] = u;
        return true;
    }

    static int find(int u) {
        if(group[u] == u) {
            return u;
        }

        return group[u] = find(group[u]);
    }

    static void initGroup(int V) {
        for(int i = 1; i <= V; i++) {
            group[i] = i;
        }
    }

}
