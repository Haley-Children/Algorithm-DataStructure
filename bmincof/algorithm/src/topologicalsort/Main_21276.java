package topologicalsort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_21276 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 사람의 수
        int n = Integer.parseInt(br.readLine());
        // 이름들을 저장
        List<String> names = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            names.add(st.nextToken());
        }

        Collections.sort(names);

        // 인접 그래프(방향o)
        boolean[][] adj = new boolean[n][n];
        int[] indeg = new int[n];
        // 간선의 개수
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int child = Collections.binarySearch(names, st.nextToken());
            int ancestor = Collections.binarySearch(names, st.nextToken());

            adj[ancestor][child] = true;
            indeg[child]++;
        }

        // 트리의 루트들을 저장할 리스트
        List<Integer> roots = new ArrayList<>();
        // indeg == 0 인 정점들(루트)을 추가
        for(int i = 0; i < n; i++) {
            if(indeg[i] == 0) {
                roots.add(i);
            }
        }

        // 출력의 첫 번째 줄
        sb.append(roots.size()).append("\n");

        // 각 노드의 자식 노드들을 저장하는 리스트 배열
        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        // 각 루트마다 위상정렬 하면서 자식을 찾기
        for (int i = 0; i < roots.size(); i++) {
            Queue<Integer> q = new ArrayDeque<>();
            int root = roots.get(i);
            q.offer(root);

            // 출력의 두 번째 줄
            sb.append(names.get(root)).append(" ");

            // 위상정렬
            while(!q.isEmpty()) {
                int cur = q.poll();

                for(int next = 0; next < n; next++) {
                    // next가 cur의 후손이 아니면 스킵
                    if(!adj[cur][next]) continue;

                    // next의 부모노드까지 도달했으면 next를 담기
                    if(--indeg[next] == 0) {
                        q.offer(next);
                        children[cur].add(next);
                    }
                }
            }
        }
        sb.append("\n");

        // 출력의 세 번째 줄부터
        for (int i = 0; i < n; i++) {
            List<Integer> child = children[i];
            sb.append(names.get(i)).append(" ").append(child.size()).append(" ");

            for (int j = 0; j < child.size(); j++) {
                sb.append(names.get(child.get(j))).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
