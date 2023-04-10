package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16401 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 조카의 수
		int M = Integer.parseInt(st.nextToken());
		// 과자의 수
		int N = Integer.parseInt(st.nextToken());
	
		int[] tonk = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tonk[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 1000000000;
		
		while(start < end) {
			int mid = (start + end + 1) / 2;
			
			long count = 0;
			
			for(int length : tonk) {
				count += length / mid;
			}
			
			// M 명에게 똑같은 길이의 과자를 줄 수 있으면, 더 크게 줄 수 있는지 찾아보기
			if(count >= M) {
				start = mid;
			} else {
				end = mid - 1;
			}
		}
		
		System.out.println(start);
		
	}
}

