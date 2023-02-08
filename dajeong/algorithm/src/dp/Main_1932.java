package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1932 {
    /*
     * 시간복잡도문제로 dp 이용
     * d[i][k] = i번째 줄에서 가능한 최대합(누적). (k = 현재 idx, 1이상 502 이상)
     * d[i][k] = max (d[i-1][k], d[i-1][k-1]) + arr[k]
     * d[1] = arr[0]
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] d = new int[n+1][505];

        d[1][1] = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(d[1][1]);
            return;
        }
        for (int i = 2; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                d[i][j] = Math.max(d[i-1][j], d[i-1][j-1]) + Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (d[n][i] > max) max = d[n][i];
        }
        System.out.println(max);

    }
}
