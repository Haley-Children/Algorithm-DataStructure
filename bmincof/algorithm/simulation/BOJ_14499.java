import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    주사위 굴리기
    1. 해당 방향으로 굴려서 지도 밖이면 스킵
    2. 주사위를 굴린다. 굴리려는 방향별로 구현
    3. 이동한 칸의 숫자에 따라 주사위 바닥면에 값을 복사
    4. 주사위 윗면에 있는 값 출력
 */
public class BOJ_14499 {

    // diceUD[1] : 주사위 윗면 , diceUd[3] : 주사위 바닥면
    static int[] diceUD = new int[4];
    static int[] diceLR = new int[4];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 명령마다 이동해야 할 방향
        int[] dx = {0,0,0,-1,1};
        int[] dy = {0,1,-1,0,0};

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int cmds = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cmds; i++) {
            int dir = Integer.parseInt(st.nextToken());
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            rollDice(dir);


            // 주사위의 바닥에 쓰여있는 값을 지도로 이동
            if(map[nx][ny] == 0) {
                map[nx][ny] = diceUD[3];
            }
            // 지도의 값을 주사위 바닥으로 이동
            else {
                diceUD[3] = map[nx][ny];
                diceLR[3] = map[nx][ny];
                map[nx][ny] = 0;
            }

            sb.append(diceUD[1]).append('\n');
            x = nx;
            y = ny;
        }

        System.out.println(sb);
    }

    static void rollDice(int dir) {
        // 동쪽으로 이동
        if(dir == 1) {
            // shift Right diceLR
            int tmp = diceLR[3];
            diceLR[3] = diceLR[2];
            diceLR[2] = diceLR[1];
            diceLR[1] = diceLR[0];
            diceLR[0] = tmp;

            diceUD[1] = diceLR[1];
            diceUD[3] = diceLR[3];
        }
        // 서쪽으로 이동
        else if(dir == 2) {
            // shift Left diceLR
            int tmp = diceLR[0];
            diceLR[0] = diceLR[1];
            diceLR[1] = diceLR[2];
            diceLR[2] = diceLR[3];
            diceLR[3] = tmp;

            diceUD[1] = diceLR[1];
            diceUD[3] = diceLR[3];
        }
        // 북쪽으로 이동
        else if(dir == 3) {
            // shift Left diceUD
            int tmp = diceUD[0];
            diceUD[0] = diceUD[1];
            diceUD[1] = diceUD[2];
            diceUD[2] = diceUD[3];
            diceUD[3] = tmp;

            diceLR[1] = diceUD[1];
            diceLR[3] = diceUD[3];
        }
        // 남쪽으로 이동
        else {
            // shift Right diceUD
            int tmp = diceUD[3];
            diceUD[3] = diceUD[2];
            diceUD[2] = diceUD[1];
            diceUD[1] = diceUD[0];
            diceUD[0] = tmp;

            diceLR[1] = diceUD[1];
            diceLR[3] = diceUD[3];
        }
    }

}

