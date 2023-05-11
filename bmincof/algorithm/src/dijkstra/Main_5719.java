package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_5719 {
    // 시작점으로부터의 최단거리
    static int[] minDist;
    // 인접 리스트
    static List<Edge>[] adj;
    // 다익스트라를 위한 우선순위 큐
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static boolean[] hasShortest;

    static int INF, end;

    // 간선 정보
    static class Edge implements Comparable<Edge> {
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.dist == o.dist) return this.to - o.to;
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        INF = 10000000;

        while(true) {
            st = new StringTokenizer(br.readLine());
            // 정점의 수
            int V = Integer.parseInt(st.nextToken());
            // 간선의 수
            int E = Integer.parseInt(st.nextToken());

            if(V == 0 && E == 0) break;

            // 초기화
            minDist = new int[V];
            adj = new ArrayList[V];
            hasShortest = new boolean[V];

            for(int i = 0; i < V; i++) {
                minDist[i] = INF;
                adj[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            // 시작점과 도착점
            int start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            // 그래프 만들기
            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());

                adj[from].add(new Edge(to, dist));
            }

            // 초기 간선 정보들로 다익스트라 시작
            dijkstra(start);

            // 다익스트라로 구한 최단거리를 이용해 최단거리 간선들을 추려내기
            pruning(start);

            // 거리정보를 초기화
            Arrays.fill(minDist, INF);
            // 추려낸 간선들로 다시 다익스트라를 사용해 거의 최단경로 계산
            dijkstra(start);

            sb.append(minDist[end] >= INF ? -1 : minDist[end]).append("\n");
        }

        System.out.println(sb);
    }

    // start로부터 최단경로를 구하는 메서드
    static void dijkstra(int start) {

        minDist[start] = 0;
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge curr = pq.poll();

            if(minDist[curr.to] != curr.dist) continue;

            for(Edge next : adj[curr.to]) {
                // 최단 경로가 아니면 해당 간선은 사용 x
                if(minDist[next.to] <= curr.dist + next.dist) continue;

                // 최단 경로 갱신
                minDist[next.to] = curr.dist + next.dist;
                pq.offer(new Edge(next.to, minDist[next.to]));
            }
        }
    }

    // 최단경로에 포함되는 간선들을 제외하는 메서드
    static boolean pruning(int curr) {
        // 마지막 지점이라면
        if(curr == end) return true;
        // 현재 정점에서 사용할 수 있는 간선들을 모두 체크
        for(Edge next : adj[curr]) {
            // to로 이동할 때 next 간선을 사용했을 때 최단거리라면
            if(minDist[curr] + next.dist == minDist[next.to]) {
                // 한 단계 더 들어가기
                // 도착지점에 성공적으로 도착했다면 최단 경로에 포함되는 간선이므로
                // 해당 간선을 삭제
                if(hasShortest[next.to]) {
                    hasShortest[curr] = true;
                    next.dist = INF;
                    continue;
                }

                boolean check = pruning(next.to);
                hasShortest[curr] |= check;

                if(check) {
                    next.dist = INF;
                }
            }
        }

        return hasShortest[curr];
    }
}
