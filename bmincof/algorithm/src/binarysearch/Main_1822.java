package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1822 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int[] setA = new int[a];
		int[] setB = new int[b];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < a; i++) {
			setA[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < b; i++) {
			setB[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.parallelSort(setA);
		Arrays.parallelSort(setB);
		
		int cnt = 0;
		for(int i = 0; i < a; i++) {
			int find = Arrays.binarySearch(setB, setA[i]);
			
			if(find < 0) {
				cnt++;
				sb.append(setA[i]).append(" ");
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb);
		
	}
}

