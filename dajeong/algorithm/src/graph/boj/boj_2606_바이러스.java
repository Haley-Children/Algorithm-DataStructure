package graph.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2606_바이러스 {
    static int V,E;
    static ArrayList<Integer>[] adList;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine()); // 정점 수
        E = Integer.parseInt(br.readLine()); // 간선 수
        adList = new ArrayList[V+1];
        for (int i = 0; i <= V; i++) {
            adList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adList[s].add(e);
            adList[e].add(s);
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        boolean[] vis = new boolean[V+1];
        queue.offer(1);
        vis[1] = true;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int c : adList[cur]) {
                if (vis[c]) {
                    continue;
                }
                queue.offer(c);
                vis[c] = true;
                cnt++;
            }
        }

        System.out.println(cnt);

    }

}
