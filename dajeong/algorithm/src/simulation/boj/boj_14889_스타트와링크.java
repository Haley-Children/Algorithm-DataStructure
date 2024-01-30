package simulation.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_14889_스타트와링크 {

    static int N, total;
    static int min = Integer.MAX_VALUE;
    static boolean[] selected;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        selected = new boolean[N + 1];
        board = new int[N + 1][N + 1]; // 행, 열에 0 채워주기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int t = Integer.parseInt(st.nextToken());
                board[i][j] = t;
                total += t;
            }
        }
        bt(0, 1);
        System.out.println(min);
    }

    private static void bt(int cnt, int start) {
        if (cnt == N / 2) {
            int diff = calculate();
            if (min > diff) {
                min = diff;
            }
            return;
        }

        for (int i = start; i <= N; i++) {
            selected[i] = true;
            bt(cnt + 1, i + 1);
            selected[i] = false;
        }
    }

    private static int calculate() {
        int s = 0;
        int l = 0;
        for (int i = 0; i < selected.length - 1; i++) {
            for (int j = i + 1; j < selected.length; j++) {
                if (selected[i] && selected[j]) {
                    s += board[i][j];
                    s += board[j][i];
                } else if (!selected[i] && !selected[j]) {
                    l += board[i][j];
                    l += board[j][i];
                }
            }
        }
        // 잘못된 생각
//        int l = total - s;
        return Math.abs(s - l);
    }



}
