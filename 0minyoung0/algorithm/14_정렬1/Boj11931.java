import java.io.*;
import java.util.*;

public class Boj11931 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.parallelSort(arr);
		
		for (int i=N-1; i>=0; i--) {
			sb.append(arr[i]).append("\n");
		}
		
		System.out.println(sb);
		
	}
}
