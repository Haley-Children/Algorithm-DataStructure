package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10971_외판원순회2 {
//     두 방법이 생각났다.
//     1. 순열(백트래킹) (풀이 작성)
//     2. 최소신장트리
//     + 시작점을 별도로 관리해서, 마지막 도시와 시작 도시가 연결되어있는지 체크
//     + 최종 sum에 시작점 - 마지막 도시 비용도 더해주기
    static int N, minSum, start;
    static int[][] board;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        minSum = Integer.MAX_VALUE;
        visited = new boolean[N];

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int s = 0; s < N; s++) {
            start = s;
            visited[s] = true;
            dfs( s, 1,0);
        }
        System.out.println(minSum);
    }

    private static void dfs(int vertex, int cnt, int sum) {
        if (cnt==N) {
            if (board[vertex][start] != 0) minSum = Math.min(minSum, sum+board[vertex][start]);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && board[vertex][i]!=0) {
                visited[i] = true;
                dfs(i, cnt+1, sum+board[vertex][i]);
                visited[i] = false;
            }
        }
    }



}
