// 배열.boj2577;

import java.io.*;

public class Boj2577 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int ans = Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine());
        int[] cnt = new int[10];
        while(ans != 0) {
        	cnt[ans % 10]++;
        	ans /= 10;
        }
        for (int i=0; i<10; i++) {
        	sb.append(cnt[i]).append("\n");
        }
        System.out.println(sb);
    }
}