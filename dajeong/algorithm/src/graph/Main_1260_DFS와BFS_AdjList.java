package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_DFS와BFS_AdjList { //인접 리스트 방식
    static int N,M,V;
    static boolean[] vis;
    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점 갯수
        M = Integer.parseInt(st.nextToken()); // 간선 갯수
        V = Integer.parseInt(st.nextToken()); // 탐색 시작 정점 번호

        vis = new boolean[N+1];
        adjList = new ArrayList[N+1];
        // 중요) 초기화 -> nullpointerException
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        // 중요) 정렬 (인접리스트는 정점으로 넣어진 순서대로 탐색해서 들어가기때문에 정점번호 오름차순으로 방문 조건이 있으면 별도로 정렬 필요)
        for (int i = 0; i < N+1; i++) {
            Collections.sort(adjList[i]);
        }

        dfs(V);
        Arrays.fill(vis, false);
        System.out.println();
        bfs(V);


    }

    private static void bfs(int v) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(v);
        vis[v] =true;
        System.out.print(v+" ");

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for (int vertex : adjList[cur]) {
                if (!vis[vertex]) {
                    queue.offer(vertex);
                    vis[vertex] = true;
                    System.out.print(vertex+" ");
                }
            }
        }
    }

    private static void dfs(int v) {
        vis[v] = true;
        System.out.print(v+" ");

        for (int vertex : adjList[v]) {
            if (!vis[vertex]) dfs(vertex);
        }
    }

}
