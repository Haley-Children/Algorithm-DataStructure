package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11403_경로찾기 {

    static int N;
    static int[][] board, newBoard;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 정점 하나씩(인접행렬의 행) bfs 탐색하면서 방문하는 정점들을 boolean 배열에 표시하고, 새로운 배열에 표시해준다.
        newBoard = new int[N][N];
        boolean[] vis = new boolean[N];
        for (int i = 0; i < N; i++) { // 탐색할 정점(인접행렬의 행)
            Arrays.fill(vis, false); // 방문배열 갱신
            bfs(i, vis);

            // 방문한 정점들만 새 배열에 표시해준다
            for (int j = 0; j < N; j++) {
                if (vis[j]) {
                    newBoard[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(newBoard[i][j]+" ");
            }
            System.out.println();
        }

    }

    private static void bfs(int start, boolean[] vis) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < N; i++) {
                if (board[cur][i] == 1 && !vis[i]) {
                    queue.offer(i);
                    vis[i] = true;
                }
            }
        }
    }
}
