package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main_5427 {

    // 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다.
    // 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다.

    static int r,c;
    static Queue<Position> pQueue, fQueue;
    static char[][] board;
    static int[][] pDist, fDist;
    static int[] dix = {1, 0, -1, 0};
    static int[] diy = {0, -1, 0, 1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            pQueue = new LinkedList<>();
            fQueue = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            pDist = new int[r][c];
            fDist = new int[r][c];

            board = new char[r][c];
            for (int j = 0; j < r; j++) {
                String s = br.readLine();
                for (int k = 0; k < c; k++) {
                    board[j][k] = s.charAt(k);
                }
            }

            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    if (board[j][k] == '@') {
                        pQueue.offer(new Position(j,k));
                        pDist[j][k] = 1; // 시작을 1로 두고 경계에 다다랐을 때 경계 Dist값 출력
                    }
                    if (board[j][k] == '*') {
                        fQueue.offer(new Position(j,k));
                        fDist[j][k] = 1;
                    }
                }
            }
            // 불 전파 bfs
            fireBfs();

            // 상근이 탈출 bfs
            personBfs();
        }
        System.out.println(sb);
    }

    private static void fireBfs() {
        while(!fQueue.isEmpty()) {
            Position cur = fQueue.poll();

            for (int j = 0; j < 4; j++) {
                int nx = dix[j] + cur.x;
                int ny = diy[j] + cur.y;

                // 경계
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                // 벽, 이미 방문했을 때
                if (board[nx][ny] == '#' || fDist[nx][ny] > 0) continue;

                fQueue.offer(new Position(nx,ny));
                fDist[nx][ny] = fDist[cur.x][cur.y] + 1;

            }
        }
    }

    private static void personBfs() {
        while (!pQueue.isEmpty()) {
            Position cur = pQueue.poll();

            for (int j = 0; j < 4; j++) {
                int nx = dix[j] + cur.x;
                int ny = diy[j] + cur.y;

                // 경계에서 탈출 성공
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    sb.append(pDist[cur.x][cur.y]).append("\n");
                    return;
                }

                // 벽, 이미 방문했을 때
                if (board[nx][ny] == '#' || pDist[nx][ny] > 0) continue;
                // 불이 이동한 상황에서 상근이보다 불이 먼저 도착할 경우
                if (fDist[nx][ny] > 0 && pDist[cur.x][cur.y]+1 >= fDist[nx][ny]) continue; // 또 실수함.. nx, ny는 아직 모른다!

                pQueue.offer(new Position(nx,ny));
                pDist[nx][ny] = pDist[cur.x][cur.y] + 1;
            }
        }
        sb.append("IMPOSSIBLE").append("\n");
    }
}
