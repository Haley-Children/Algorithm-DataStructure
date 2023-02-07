// dp.boj9461;

import java.io.*;
import java.util.*;

public class Boj9461 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long[] arr = new long[101];
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        arr[4] = 2;
        arr[5] = 2;
        for (int i=6; i<=100; i++) {
        	arr[i] = arr[i-1] + arr[i-5];
        }
        for (int i=0; i<T; i++) {
        	int n = Integer.parseInt(br.readLine());
        	System.out.println(arr[n]);
        }
    }
}