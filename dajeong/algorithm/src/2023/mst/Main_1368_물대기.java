package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1368_물대기 {

    // 그래프 모델링이 핵심인 문제 - 프림 알고리즘으로 구현
    static int N;
    static int[][] board;
    static boolean[] checkV; // 최소신장트리에 정점이 포함되어있는지 여부 확인 boolean 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        board = new int[N+1][N+1]; // 가상의 점 추가 (우물을 파는 경우)
        for (int i = 0; i < N; i++) {
            board[i][N] = board[N][i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //printBoard();

        checkV = new boolean[N+1];
        int result = 0; // 최소 비용
        int vCnt = 0; // 지금까지 선택된 정점 수

        // 임의의 0번 정점을 첫 시작 정점으로 결정. pq에 w가 0이 아닌 정점들 넣기.
        checkV[0] = true; // 첫번째 정점을 선택 후 방문 표시
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        for (int i = 1; i < N+1; i++) {
            pq.offer(new Vertex(i, board[0][i]));
        }
        while(!pq.isEmpty()) {
            Vertex minVertex = pq.poll();
            if (checkV[minVertex.vNo]) continue;
            result += minVertex.w;
            checkV[minVertex.vNo] = true;
            if (++vCnt == N) break;

            for (int i = 0; i < N+1; i++) {
                if (!checkV[i] && board[minVertex.vNo][i] != 0) {
                    pq.offer(new Vertex(i, board[minVertex.vNo][i]));
                }
            }
        }

        System.out.println(result);
    }

    private static void printBoard() {
        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < N+1; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static class Vertex implements Comparable<Vertex> {
        int vNo;
        int w;

        public Vertex(int vNo, int w) {
            this.vNo = vNo;
            this.w = w;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.w,o.w);
        }
    }
}