import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_3190 {

    public static void main(String[] args) throws Exception {
        final int APPLE = 1;
        final int SNAKE = 9;

        // 오른쪽, 아래, 왼쪽, 위
        final int[] dx = {0,1,0,-1};
        final int[] dy = {1,0,-1,0};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = APPLE;
        }
        map[0][0] = SNAKE;

        int l = Integer.parseInt(br.readLine());
        int[] cmd = new int[10010];
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            cmd[Integer.parseInt(st.nextToken())] = st.nextToken().equals("L") ? -1 : 1;
        }

        int time = 0;
        int dir = 0;

        // 뱀이 점유하는 위치정보
        Deque<int[]> snake = new LinkedList<>();
        snake.add(new int[] {0,0});

        while(true) {
            time++;
            // 머리 이동
            // snake의 가장 마지막에 들어온 위치가 이전 시간의 뱀의 머리
            int[] head = snake.getLast();
            int nx = head[0] + dx[dir];
            int ny = head[1] + dy[dir];

            // 머리 위치가 벽이거나 자신의 몸이라면 게임오버
            if(nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == SNAKE) break;

            // 머리 위치를 뱀에 추가
            snake.add(new int[] {nx, ny});
            if(map[nx][ny] != APPLE) {
                // 뱀의 꼬리위치
                int[] tail = snake.poll();
                map[tail[0]][tail[1]] = 0;
            }
            map[nx][ny] = SNAKE;

            // 해당 시간이 끝났을 때 명령 수행
            dir = (dir + 4 + cmd[time]) % 4;
        }

        System.out.println(time);
    }

}

