package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11725_트리의부모찾기 {

    // 트리의 bfs, dfs(재귀, 비재귀)는 특이하다.
    // 임의의 정점을 골랐을 때, 인접한 정점들(부모와 자식들) 중 부모만 빼고 나머지들만 탐색하면 된다. => 부모의 정점을 저장하는 배열 필요

    static ArrayList<Integer>[] adjList;
    static boolean[] vis;
    static int[] parents;
    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1]; // 루트가 1
        parents = new int[N+1]; // 정점별 부모 저장할 배열
        parents[1] = 1; // 루트의 부모는 자기자신으로 저장

        for (int i = 0; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(end);
            adjList[end].add(start);
        }
        vis = new boolean[N+1];
        dfs(1);
        for (int i = 2; i < N+1; i++) {
            System.out.println(parents[i]);
        }

    }

    private static void dfs(int vertex) {
        vis[vertex] = true;
        for (int a : adjList[vertex]) {
            if (vis[a]) continue;
            if (parents[vertex] == a) continue; // 부모일 경우 건너뛰기
            parents[a] = vertex; // 부모가 아닐 경우 자식이므로 부모 배열 관계 표시
            dfs(a);
        }

    }

}
