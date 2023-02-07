// dp.boj14501;

import java.io.*;
import java.util.*;

public class Boj14501 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][2];
        for (int i=1; i<=n; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
        }
        int[] p = new int[n+2];
        for (int i=n; i>=1; i--) {
        	if (i+arr[i][0]-1 > n) {
        		p[i] = p[i+1];
        	}
        	else {
        		p[i] = Math.max(p[i+1], arr[i][1] + p[i+arr[i][0]]);
        	}
        }
        int ans = 0;
        for (int i=1; i<=n; i++) {
        	if (p[i] > ans) {
        		ans = p[i];
        	}
        }
        System.out.println(ans);
    }
}