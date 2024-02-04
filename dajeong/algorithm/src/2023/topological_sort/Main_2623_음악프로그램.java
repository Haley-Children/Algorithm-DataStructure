package topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2623_음악프로그램 {

    static int N,M;
    static ArrayList<Integer>[] adj;
    static int[] deg;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];

        // 초기화
        for (int i = 0; i < N+1; i++) {
            adj[i] = new ArrayList<>();
        }
        deg = new int[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int cur = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt-1; j++) {
                int nxt = Integer.parseInt(st.nextToken());
                adj[cur].add(nxt);
                deg[nxt]++;
                cur = nxt;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            if (deg[i]==0) queue.offer(i);
        }

        List<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            ans.add(cur);
            for(int a : adj[cur]) {
                deg[a]--;
                if (deg[a] == 0) queue.offer(a);
            }
        }

        if (ans.size() != N) {
            System.out.println(0);
        } else {
            for (int i = 0; i < ans.size(); i++) {
                System.out.println(ans.get(i));
            }
        }





    }

}
