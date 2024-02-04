package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 크루스칼 알고리즘 - path compression
public class Main_1197_최소스패닝트리_Kruskal {

    static int V, E;
    static List<Edge> edgeList;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        // 간선 정보를 저장하는 리스트
        edgeList = new ArrayList<>();
        // 간선 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList.add(
                new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
        parent = new int[V + 1]; // 1-indexed
        // 가중치가 작은 순서대로 정렬
        Collections.sort(edgeList, (o1, o2)-> o1.w - o2.w );
        Arrays.fill(parent, -1); // make set (자기자신이 root인경우 -1)

        long result = 0; // 최소 스패닝 트리의 가중치의 합
        int cnt = 0; // 최소스패닝 트리에 포함된 간선의 수
        for (Edge edge : edgeList) {
            if (union(edge.x, edge.y)) {
                result += edge.w;
                if (++cnt == V - 1) {
                    break;
                }
            }
        }
        System.out.println(result);

    }

    private static boolean union(int x, int y) {
        int xRoot = findSet(x);
        int yRoot = findSet(y);
        if (xRoot == yRoot) {
            return false;
        }
        if (xRoot <= yRoot) {
            parent[xRoot] = yRoot;
        } else {
            parent[yRoot] = xRoot;
        }
        return true;
    }

    private static int findSet(int x) {
        if (parent[x] == -1) {
            return x;
        }
        return parent[x] = findSet(parent[x]); // path compression
    }

    private static class Edge implements Comparable<Edge> {

        int x; // 정점1
        int y; // 정점2
        int w; // 가중치

        public Edge(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }


        @Override
        public int compareTo(Edge o) { // 가중치가 작은 순으로 오름차순 정렬
            return this.w - o.w;
        }
    }
}