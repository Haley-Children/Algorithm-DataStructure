package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11727 {
    /*
     * d[i] = 2* d[i-2] + d[i-1]
     * d[1] = 1, d[2] = 3
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] d = new int[n+1];
        if (n==1) {
            System.out.println(1);
            return;
        } else if (n==2) {
            System.out.println(3);
            return;
        }
        d[1] = 1;
        d[2] = 3;
        for (int i = 3; i <= n; i++) {
            d[i] = (2* d[i-2] + d[i-1]) % 10007;
        }
        System.out.println(d[n]);
    }
}
