package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main_12852 {

    /*
     * 1. d[i] = min(d[i/2],d[i/3],d[i-1]) + 1
     * 2. 경로 복원 -> pre[]
     * 3. d[1] = 0, d[2] = 1, d[3] = 1
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] d = new int[n + 1];
        int[] pre = new int[n + 1];

        d[1] = 0;
        for (int i = 2; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            pre[i] = i-1;
            if (i % 2 == 0 && d[i] > d[i/2] + 1) {
                d[i] = d[i / 2] + 1;
                pre[i] = i/2;
            }
            if (i % 3 == 0 && d[i] > d[i/3]+1) {
                d[i] = d[i / 3] + 1;
                pre[i] = i/3;
            }
        }
        sb.append(d[n]).append("\n");
        sb.append(n).append(" ");
        while(true) {
            if (pre[n] < 1) break;
            sb.append(pre[n]).append(" ");
            n = pre[n];
        }
        System.out.println(sb);
    }
}
