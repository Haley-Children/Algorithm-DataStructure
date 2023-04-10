package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1654 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		long[] wires = new long[K];
		for(int i = 0; i < K; i++) {
			wires[i] = Long.parseLong(br.readLine());
		}
		
		long start = 1;
		long end = Integer.MAX_VALUE;
		
		while(start < end) {
			long mid = (start + end + 1) / 2;
			
			long cnt = 0;
			
			for(long wire : wires) {
				cnt += wire / mid;
			}
			
			// 나눈 전선이 N개보다 적다면 더 작게 잘라야 함
			if(cnt < N) {
				end = mid - 1;
			} else {
				start = mid;
			}
		}
		
		System.out.println(start);
	}
}

