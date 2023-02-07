// dp.boj11055;

import java.io.*;
import java.util.*;

public class Boj11055 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int[] sum = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	int maxSum = 0;
        	for (int j=1; j<i; j++) {
        		if (arr[j] < arr[i] && sum[j] > maxSum) {
        			maxSum = sum[j];
        		}
        	}
        	sum[i] = maxSum + arr[i];
        }
        int ans = 0;
        for (int i=1; i<=n; i++) {
        	if (ans < sum[i]) ans = sum[i]; 
        }
        System.out.println(ans);
    }
}