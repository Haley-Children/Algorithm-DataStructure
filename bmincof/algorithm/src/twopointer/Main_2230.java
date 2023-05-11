package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2230 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.parallelSort(arr);
		
		int end = 1;
		long diff = Long.MAX_VALUE;
		for(int start = 0; start < n; start++) {
			while(end < n && arr[end] - arr[start] < m) {
				end++;
			}
			if(end == n) break;
			
			if(arr[end] - arr[start] < diff) {
				diff = arr[end] - arr[start];
				if(diff == m) break;
			}
			
		}
		
		System.out.println(diff);
		
	}
}

