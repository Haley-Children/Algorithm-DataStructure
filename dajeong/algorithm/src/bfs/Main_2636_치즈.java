package bfs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2636_치즈 {

    static int R,C, time, prev;
    static int[][] board;
    static int[] dix = {-1, 1, 0, 0};
    static int[] diy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0,0);
        System.out.println(time);
        System.out.println(prev);
    }

    private static void bfs(int x, int y) {
        // 외부 공기를 담을 큐
        Queue<int[]> airQueue = new ArrayDeque<>();
        boolean[][] vis = new boolean[R][C];
        airQueue.offer(new int[]{x,y});
        vis[x][y] = true;
        // 다음 공기(외부 공기와 맞닿은 치즈)를 담을 큐
        Queue<int[]> newAirQueue = new ArrayDeque<>();

        while(true) {
            while(!airQueue.isEmpty()) {
                int[] cur = airQueue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = dix[i] + cur[0];
                    int ny = diy[i] + cur[1];
                    if (nx<0 || ny<0 || nx>=R || ny>=C || vis[nx][ny]) continue;
                    if (board[nx][ny] == 0) {
                        airQueue.offer(new int[]{nx,ny});
                        vis[nx][ny] = true;
                    } else if (board[nx][ny] == 1) {
                        newAirQueue.offer(new int[]{nx,ny});
                        vis[nx][ny] = true;
                    }
                }
            }
            // 새 공기가 없을 경우(녹일 치즈가 없을 경우) 종료
            if (newAirQueue.isEmpty()) {
                break;
            } else { // 있을 경우
                time++; // 시간 + 1
                prev = newAirQueue.size(); // 녹인 치즈 갯수 갱신

                // 큐 swap (새 공기 배열을 현재 외부 공기 배열로 바꿔주기)
                Queue<int[]> tmp = airQueue;
                airQueue = newAirQueue;
                newAirQueue = tmp;
            }
        }
    }
}
