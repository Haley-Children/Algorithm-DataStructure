package simulation.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class code_뱀은사과를좋아해 {

    static int N,M,K, dir, time;
    static HashMap<Character, Integer> dirMap = new HashMap<>();
    static ArrayDeque<int[]> snake = new ArrayDeque<>();
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0}; // 상우하좌
    static int[] dy = {0, 1, 0, -1};

    static final int BLANK = 0;
    static final int APPLE = 1;
    static final int SNAKE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 사과 개수
        K = Integer.parseInt(st.nextToken()); // 뱀 방향 변환 횟수
        board = new int[N][N];
        dirMap.put('U', 0);
        dirMap.put('D', 2);
        dirMap.put('R', 1);
        dirMap.put('L', 3);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = APPLE; // 사과
        }

        int curX = 0;
        int curY = 0;
        snake.offer(new int[]{curX, curY});
        board[curX][curY] = SNAKE;


        loop: // 뱀이 전부 움직이면 종료
        for (int i = 0; i < K; i++) {
            // 뱀 이동
            st = new StringTokenizer(br.readLine());
            char command = st.nextToken().charAt(0);
            dir = dirMap.get(command);
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                time++;
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];


                // 격자를 벗어날 경우 종료
                if (!inRange(nx, ny)) {
                    break loop;
                }

                // 움직이는 도중 몸이 꼬여 서로 겹쳐졌을 경우 종료
                // ** 꼬리일 경우에는 제외해야함!!
                if (board[nx][ny] == SNAKE && !isTail(nx, ny)) {
                    break loop;
                }

                if (board[nx][ny] == APPLE) {
                    snake.offerFirst(new int[]{nx,ny});
                    board[nx][ny] = SNAKE;
                    // ** 사과가 아니면서, 꼬리에 다다랐을 때에도 BLANK 처리해줘야 한다. 조건 처리 놓쳤다.
                } else if (board[nx][ny] == BLANK || (board[nx][ny]==SNAKE && isTail(nx, ny))){
                    snake.offerFirst(new int[]{nx,ny});
                    board[nx][ny] = SNAKE;
                    // ** 꼬리부터 지워주고 머리를 넣어주면 꼬이는걸 더 잘 알 수 있었을 것이다.
                    int[] last = snake.pollLast();
                    board[last[0]][last[1]] = BLANK;

                }



                // 위치 바꿔주는거 잊지 말기!
                curX = nx;
                curY = ny;

            }

        }
        System.out.println(time);
    }

    private static boolean isTail(int x, int y) {
        int[] last = snake.peekLast();
        return x == last[0] && y == last[1];
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
