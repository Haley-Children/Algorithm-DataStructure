package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2805 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 나무의 수
		int n = Integer.parseInt(st.nextToken());
		// 최소한으로 원하는 나무의 길이
		int m = Integer.parseInt(st.nextToken());
		
		int[] tree = new int[n];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 1000000000;
		
		while(start < end) {
			int mid = (start + end + 1) / 2;
			
			long total = 0;
			for(int height : tree) {
				if(height > mid) total += height - mid;
			}
			
			// 원하는 길이만큼 가져올 수 있으면
			if(total >= m) {
				start = mid;
			} else {
				end = mid - 1;
			}
		}
		
		System.out.println(start);
	}	
}

