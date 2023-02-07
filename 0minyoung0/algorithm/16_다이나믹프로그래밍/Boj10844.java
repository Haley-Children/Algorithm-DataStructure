// dp.boj10844;

import java.io.*;
import java.util.*;

public class Boj10844 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] arr = new long[n+1][10];
        for (int i=1; i<=9; i++) {
        	arr[1][i] = 1;
        }
        for (int i=2; i<=n; i++) {
        	arr[i][0] = arr[i-1][1];
        	for (int j=1; j<=8; j++) {
        		arr[i][j] = (arr[i-1][j-1] + arr[i-1][j+1]) % 1000000000;
        	}
        	arr[i][9] = arr[i-1][8];
        }
        long ans = 0;
        for (int i=0; i<=9; i++) {
        	ans += arr[n][i];
        }
        System.out.println(ans%1000000000);
    }
}