package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1026 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		
		
		int[] a = new int[n];
		int[] b = new int[n];
		
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st1.nextToken());
			b[i] = Integer.parseInt(st2.nextToken());
		}
		
		// 두 배열의 각 요소를 곱했을 때 최소가 되게 하려면
		// a배열의 i번째 큰 값과 b배열의 i번째 작은 값을 서로 곱했을 때 최소
		
		// 두 배열의 최소와 최대를 비교하기 위해 정렬
		Arrays.sort(a);
		Arrays.sort(b);
		
		int sum = 0;
		// 큰 값 * 작은 값
		for(int i = 0; i < n; i++) {
			sum += a[i] * b[n-1-i];
		}
		
		System.out.println(sum);
	}
}

