package topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2252_줄세우기 {
    static int N,M;
    static int[] deg;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 각 정점은 outdegree, indegree가 여러개일 수 있다. 따라서 배열 형태는 안된다.
        adj = new ArrayList[N+1]; // 각 정점별 outdegree 리스트 / 1-indexed

        for (int i = 0; i < N+1; i++) {
            adj[i] = new ArrayList<>();
        }

        deg = new int[N+1]; // 각 정점별 indegree 수 배열 / 1-indexed
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adj[start].add(end); // outdegree 정점 기록
            deg[end]++; // indegree수 +1
        }

        // indegree수가 0인 정점을 큐에 넣기
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            if (deg[i]==0) {
                queue.offer(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            ans.add(cur);
            for (int a : adj[cur]) {
                deg[a]--;
                if (deg[a] == 0) queue.offer(a);
            }
        }

        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i)+" ");
        }


    }


}
