package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main_1149 {

    /*
     * 1. d[i][j] = i번째 집을 j 색으로 칠했을 때 비용의 최솟값 (j=0:r/j=1:g/j=2:b)
     * 2. d[i][0] = min (d[i-1][1],d[i-1][2]) + r[i]
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] d = new int[n+1][3];
        int[] r = new int[n+1];
        int[] g = new int[n+1];
        int[] b = new int[n+1];
        int min = 0;

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            g[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
        }
        d[1][0] = r[1];
        d[1][1] = g[1];
        d[1][2] = b[1];
        if (n == 1) {
            min = Math.min(r[1], g[1]);
            System.out.println(Math.min(min, b[1]));
            return;
        }
        for (int i = 2; i <= n; i++) {
            d[i][0] = Math.min(d[i-1][1],d[i-1][2]) + r[i];
            d[i][1] = Math.min(d[i-1][0],d[i-1][2]) + g[i];
            d[i][2] = Math.min(d[i-1][0],d[i-1][1]) + b[i];
        }
        min = Math.min(d[n][0], d[n][1]);
        min = Math.min(min, d[n][2]);
        System.out.println(min);
    }

}
