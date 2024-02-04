package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_4803_트리 {

    // union find 이용
    // cycle 반례 주의
    static int n, m;
    static int[] parents;
    static boolean[] cycledChild;
    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = 0;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 정점 수 (1부터 시작)
            int m = Integer.parseInt(st.nextToken()); // 간선 수

            // 테스트케이스 종료
            if (n == 0 && m == 0) {
                break;
            }
            sb.append("Case ").append(++T).append(": ");

            // 트리 - 인접리스트
            adjList = new ArrayList[n + 1];
            for (int i = 0; i < n + 1; i++) {
                adjList[i] = new ArrayList<>();
            }

            parents = new int[n + 1];
            cycledChild = new boolean[n+1];
            // 원소가 1개인 서로소 집합 세팅 (부모 -1로 세팅)
            Arrays.fill(parents, -1);

            // 간선 입력
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                // 합집합
                union(start, end);

            }

            // 자기자신이 루트 노드인 집합을 표시
            int cnt = 0;
            for (int k = 1; k < n + 1; k++) {
                if (parents[k] == -1) {
                    cnt++;
                }
            }

            // cycledChild의 root 노드(cycledParent)를 구해서 표시한다.
            boolean[] cycledParent = new boolean[n+1];
            for (int i = 0; i < n+1; i++) {
                if (cycledChild[i]) {
                    cycledParent[findSet(i)] = true;
                }
            }

            // cycledParent의 수를 구한다
            int cnt2 = 0;
            for (int i = 0; i < n+1; i++) {
                if (cycledParent[i]) cnt2++;
            }

            // 전체 루트 노드 수(cnt)에서 cycledParent 수(cnt2)를 뺀다
            if (cnt-cnt2 == 0) {
                sb.append("No trees.\n");
            } else if (cnt-cnt2 == 1) {
                sb.append("There is one tree.\n");

            } else if (cnt - cnt2 > 0) {
                sb.append(String.format("A forest of %d trees.\n", cnt-cnt2));
            }
        }

        System.out.println(sb);


    }

    private static void union(int x, int y) {
        int xRoot = findSet(x);
        int yRoot = findSet(y);

        if (xRoot != yRoot) {
            if (xRoot <= yRoot) {
                parents[yRoot] = xRoot;
            } else {
                parents[xRoot] = yRoot;
            }
        } else { // 부모가 같을 경우 -> cycleChild 표시
            cycledChild[x] = true;
            cycledChild[y] = true;
        }
    }

    private static int findSet(int v) {
        if (parents[v] == -1) {
            return v;
        }
        return parents[v] = findSet(parents[v]); // path 압축
    }

}
