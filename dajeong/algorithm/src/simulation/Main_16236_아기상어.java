package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236_아기상어 {

    /*
    처음에 아기 상어의 크기는 2
    아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동
    자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 먹을 수도 없음
    크기가 같은 물고기는 그 물고기가 있는 칸은 지나갈 수 있다. 먹을 수 없음
    자신의 크기보다 작은 물고기는 지나갈 수 있고, 먹을 수 있다.

    자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
    -> 아기상어 크기, 아기상어가 먹은 물고기 수 변수 // 아기상어크기 +1, 먹은 물고기 변수 갱신

    // 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간 출력

    본인보다 크기가 작은 물고기들이 없을 경우 엄마 상어에게 도움 요청 -> ** 물고기 크기에 따른 cnt 배열 두기
    작은 물고기가 1개일 경우, 그 물고기 먹으러 감. (이동 칸 수만큼 시간 ++)
    물고기가 1마리보다 많다면, 가장 가까운 물고기 먹으러 가기
     */

    static int N;
    static int[][] board;
    static int[] fishCnt;
    static Fish babyShark;
    static int sharkSize, time, eatCnt;
    static int[][] dist;
    static boolean[][] vis;
    static int[] dix = {-1, 0, 1, 0};
    static int[] diy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        fishCnt = new int[6 + 1]; // 1~6 size 물고기 수 저장 배열

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
                if (1 <= board[r][c] && board[r][c] <= 6) {
                    fishCnt[board[r][c]]++; // 사이즈별 물고기 수 저장
                } else if (board[r][c] == 9) { // 아기상어
                    babyShark = new Fish(r, c);
                }
            }
        }

        sharkSize = 2; // 아기상어 사이즈
        time = 0; // 아기 상어가 돌아다닌 시간
        dist = new int[N][N]; // bfs 거리 배열
        vis = new boolean[N][N]; // bfs 방문 배열
        eatCnt = 0; // 먹은 물고기 수
        while (true) {
            int canEatable = 0; // 먹을 수 있는 물고기 수

            // 상어 사이즈보다 작은 물고기 수 세기
            for (int i = 1; i <= 6; i++) {
                if (i < sharkSize && fishCnt[i] > 0) {
                    canEatable += fishCnt[i];
                }
            }

            // 먹을 수 있는 물고기가 없을 경우 종료
            if (canEatable == 0) {
                break;
            }

            // bfs 돌리기 전 거리 배열, 방문 배열 갱신
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], 0);
                Arrays.fill(vis[i], false);
            }

            // 먹을 수 있는 물고기가 존재하는 경우 bfs 돌리기
            if(!bfs()) break;
            // 먹은 물고기 수와 아기 상어 사이즈가 같을 경우, 아기 상어 크기 +1, 먹은 물고기 수 갱신
            if (eatCnt == sharkSize) {
                sharkSize++;
                eatCnt = 0;
            }

        }
        // 시간 (정답) 출력
        System.out.println(time);
    }

    private static boolean bfs() {
        // 같은 거리에 있는 물고기면, 가장 위쪽에 있는 물고기 선택  -> 행 위치가 같으면, 가장 왼쪽에 있는 물고기 선택
        PriorityQueue<Fish> pq = new PriorityQueue<>(); // 먹을 수 있는 물고기 저장할 우선순위큐
        Queue<Fish> queue = new ArrayDeque<>(); // 이동할 때 쓰일 큐
        queue.offer(babyShark);
        vis[babyShark.x][babyShark.y] = true;

        int dis = 0;
        while (!queue.isEmpty()) {
            Fish cur = queue.poll(); // 현재 상어 위치
            for (int i = 0; i < 4; i++) { // 상하좌우 탐색

                int nx = cur.x + dix[i];
                int ny = cur.y + diy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || vis[nx][ny]) { // 배열 범위 넘어서거나, 방문했었으면 continue
                    continue;
                }
                int nextFish = board[nx][ny]; // 상하좌우로 찾은 물고기
                if (nextFish > sharkSize) { // 찾은 물고기가 상어보다 큰 물고기일 경우, 이동 불가여서 continue
                    continue;
                } else { // 상어보다 크기가 같거나 작을 경우 + 빈칸일 경우
                    if (dis != 0 && dis < dist[cur.x][cur.y] + 1) continue; // **********
                    vis[nx][ny] = true;
                    dist[nx][ny] = dist[cur.x][cur.y] + 1;
                    queue.offer(new Fish(nx, ny)); // 이동 가능
                    if (0 < nextFish && nextFish < sharkSize) { // 사이즈가 작은 물고기일 경우 먹을 수 있음
                        dis = dist[nx][ny];
                        pq.offer(new Fish(nx, ny));  // 같은 거리에 있는 물고기들 중, 먹을 수 있는 물고기 넣기.
                    }
                }
            }
        }

        if (pq.size() > 0) { // 먹을 수 있는 물고기가 존재한다면, 먹고 종료
            // **** 이동하면서 먹을 수 있는 물고기를 확인하면, 애초에 거리가 같은 모든 물고기들을 다 넣는 것이 아니다!
//        	System.out.println("pq 사이즈: " + pq.size()); // *** comparator가 잘못된 것이 아니었다. 먹을 수 있는 물고기 큐를 찍어보니까 pq size가 1로 나온다. 즉,

            Fish eatableFish = pq.poll();
            int ex = eatableFish.x;
            int ey = eatableFish.y;
            int fishSize = board[ex][ey];
            board[babyShark.x][babyShark.y] = 0; // *** 아기상어 위치 0으로 하기. 아기 상어를 9로 두고 풀면, 아기상어 사이즈가 9보다 클 때 자기 자신을 먹게 됨
            babyShark = new Fish(ex, ey); // 아기상어 위치 갱신
            board[ex][ey] = 0; // *** 물고기 먹기
            eatCnt++; // 물고기 먹은 수 cnt++
            time += dist[ex][ey]; // 시간 += bfs로 탐색한 거리
            fishCnt[fishSize]--; // 먹을 수 있는 물고기 수 갱신
            board[ex][ey] = -1;// ********* 찍기용
            return true; // **********map 상에서 먹을 수 있는 물고기는 있지만 사방으로 더 큰 물고기가 있어서 먹을 수 없는 경우 고려해서 boolean 처리 해야한다.
        } else {
            return false;
        }
    }

    static class Fish implements Comparable<Fish>{

        int x;
        int y;

        public Fish(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Fish o) {
            if(this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }



    }
}