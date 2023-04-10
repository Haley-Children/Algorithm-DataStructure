import java.io.*;
import java.util.*;

public class Boj10989 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 수의 개수
		int N = Integer.parseInt(br.readLine());

		// N개의 수
		int[] arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 정렬
		Arrays.parallelSort(arr);
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			sb.append(arr[i]).append("\n");
		}
		System.out.println(sb);
	}
}
