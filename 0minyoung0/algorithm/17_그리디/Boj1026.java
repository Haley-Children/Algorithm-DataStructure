// 그리디.boj1026;

import java.io.*;
import java.util.*;

public class Boj1026 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        int[] B = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
        	A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
        	B[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(B);
        int ans = 0;
        for (int i=0; i<n; i++) {
        	ans += A[i] * B[n-1-i];
        }
        System.out.println(ans);
    }
}