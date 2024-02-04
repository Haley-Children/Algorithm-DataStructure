package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// Z - 사고과정 이해하고 나중에 다시 한번 풀어보기
public class Main_1074 {
    static int n, r, c;
    static int[][] order;

    private static int recur(int n, int r, int c) {
        // n = 0일 때 return
        if (n == 0) return 0;

        int half = (int) Math.pow(2, n-1);
        // 1사분면
        if (half > r && half > c) return recur(n - 1, r, c);
        // 2사분면
        if (half > r && half <= c) return half * half + recur(n - 1, r, c - half);
        // 3사분면
        if (half <= r && half > c) return 2 * half * half + recur(n - 1, r - half, c);
        // 4사분면
        return 3 * half * half + recur(n - 1, r - half, c - half);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        r = Integer.parseInt(s[1]);
        c = Integer.parseInt(s[2]);
        order = new int[n][n];
        System.out.println(recur(n, r, c));
    }

}
