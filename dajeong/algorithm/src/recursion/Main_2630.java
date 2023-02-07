package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2630 {

    static int[][] arr;
    static int zeroCnt, oneCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        recur(0, 0, n);
        System.out.println(zeroCnt);
        System.out.println(oneCnt);
    }

    private static void recur(int x, int y, int size) {
        if (checkSame(x, y, size)) {
            if (arr[x][y] == 0) {
                zeroCnt++;
            }
            if (arr[x][y] == 1) {
                oneCnt++;
            }
            return;
        }
        int half = size / 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                recur(x + i * half, y + j * half, half); // 마지막에 size말고 half 넣는 것 잊지말기!
            }
        }
    }

    private static boolean checkSame(int x, int y, int size) {
        int target = arr[x][y];

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (target != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
