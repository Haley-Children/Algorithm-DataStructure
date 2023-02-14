// 배열.boj1475;

import java.io.*;

public class Boj1475 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cnt = new int[10];
        while (n != 0) {
        	cnt[n % 10]++;
        	n /= 10;
        }
        int ans = (cnt[6] + cnt[9] + 1) / 2;
        for (int i=0; i<6; i++) {
        	if (cnt[i] > ans) ans = cnt[i];
        }
        for (int i=7; i<9; i++) {
        	if (cnt[i] > ans) ans = cnt[i];
        }
        System.out.println(ans);
    }
}