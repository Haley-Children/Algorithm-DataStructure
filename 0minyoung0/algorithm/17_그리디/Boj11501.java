// 그리디.boj11501;

import java.io.*;
import java.util.*;

public class Boj11501 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i=0; i<T; i++) {
        	int n = Integer.parseInt(br.readLine());
        	st = new StringTokenizer(br.readLine());
        	long[] price = new long[n];
        	for (int j=0; j<n; j++) {
        		price[j] = Integer.parseInt(st.nextToken());
        	}
        	long ans = 0;
        	long max = 0;
        	for (int j=n-1; j>=0; j--) {
        		if (price[j] > max) {
        			max = price[j];
        		}
        		else {
        			ans += max - price[j];
        		}
        	}
        	sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}