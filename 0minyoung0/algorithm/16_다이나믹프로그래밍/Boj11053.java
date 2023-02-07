// dp.boj11053;

import java.io.*;
import java.util.*;

public class Boj11053 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int[] length = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	int maxLength = 0;
        	for (int j=1; j<i; j++) {
        		if (arr[j] < arr[i] && length[j] > maxLength) {
        			maxLength = length[j];
        		}
        	}
        	length[i] = maxLength + 1;
        }
        int ans = 0;
        for (int i=1; i<=n; i++) {
        	if (ans < length[i]) {
        		ans = length[i];
        	}
        }
        System.out.println(ans);
    }
}