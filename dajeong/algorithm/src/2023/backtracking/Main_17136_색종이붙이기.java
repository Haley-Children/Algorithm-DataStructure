package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17136_색종이붙이기 {

    static boolean[][] board;
    static int[] paperCnt;
    static int emptyCnt, minAns;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        board = new boolean[10][10];
        paperCnt = new int[]{0, 5, 5, 5, 5, 5}; // 남은 색종이 갯수
        emptyCnt = 0;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                if (st.nextToken().equals("1")) {
                    board[i][j] = true;
                    emptyCnt++; // 붙일 수 있는 색종이 넓이/갯수
                }
            }
        }

        // 붙일 수 있는 색종이가 없으면 바로 0 출력
        if(emptyCnt==0) {
            System.out.println(0);
            System.exit(0);
        }

        minAns = Integer.MAX_VALUE;
        // 행/열 정보 n, 붙인 색종이 수 cnt를 파라미터로 넘겨주는 백트래킹 수행
        backtracking(0, 0);
        // 색종이를 다 붙일 수 없어서 minAns가 갱신되지 않은 경우(불가능) -1 출력
        if (minAns == Integer.MAX_VALUE) {
            minAns = -1;
        }
        System.out.println(minAns);

    }

    private static void backtracking(int n, int cnt) { // 행, 열처리가 귀찮으므로 번호 부여 -> 행: n/10, 열: n%10
        if (n >= 100) { // 끝까지 도달한 경우
            if (emptyCnt == 0) { // 남은 색종이 없이 모두 색종이 붙이기를 성공했다면 사용한 색종이 수 세고 정답(최소 사용 색종이 수) 갱신
                if (minAns > cnt) {
                    minAns = cnt;
                }
                return;
            }
        }

        // 색종이를 붙일 필요가 없다면 다음 칸으로 넘어가기
        if (!board[n / 10][n % 10]) {
            backtracking(n + 1, cnt);
        }

        // 현재 전체 최솟값보다 cnt가 커진다면 더이상 탐색할 필요가 없으므로 가지치기
        if(minAns <= cnt) return;

        // 색종이를 붙여야 한다면,
        for (int size = 5; size >= 1; size--) { // 사이즈가 큰 것부터 색종이 붙였다 떼는 백트래킹 시작 (0~4를 더해주어야 범위체크하기 더 쉽긴함)
            // 해당 사이즈의 색종이가 남아 있어야 함
            if(paperCnt[size]<=0) continue;
            // 해당 사이즈로 색종이를 붙일 수 있는지 체크.
            if (!attachable(n, size)) {
                continue;
            }

            // 붙일 수 있으면 색종이 붙이기
            change(n, size, false);
            paperCnt[size]--;
            emptyCnt-=size*size;

            // 백트래킹
            backtracking(n + size, cnt+1);

            // 색종이 떼기
            change(n, size, true);
            paperCnt[size]++;
            emptyCnt+=size*size;


        }


    }

    private static void change(int n, int size, boolean flag) {
        for (int i = n / 10; i < n / 10 + size; i++) {
            for (int j = n % 10; j < n % 10 + size; j++) {
                board[i][j] = flag;
            }
        }

    }

    private static boolean attachable(int n, int size) {
        int r = n / 10;
        int c = n % 10;
        if ( r+size>10  || c+size>10) return false; // 범위 체크 주의
        for (int i = r ; i < r+size; i++) {
            for (int j = c; j < c+size ; j++) {
                if (!board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
