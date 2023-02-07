// dp.boj1912;

import java.io.*;
import java.util.*;

public class Boj1912 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	if (arr[i-1] > 0) {
        		arr[i] += arr[i-1];
        	}
        }
        int ans = arr[1];
        for (int i=2; i<=n; i++) {
        	if (ans < arr[i]) {
        		ans = arr[i]; 
        	}
        }
        System.out.println(ans);
    }
}