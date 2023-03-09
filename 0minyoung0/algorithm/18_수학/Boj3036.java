import java.io.*;
import java.util.*;

public class Boj3036 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=1; i<n; i++) {
			System.out.println(arr[0]/GCD(arr[0],arr[i])+"/"+arr[i]/GCD(arr[0],arr[i]));
		}
	}
	private static int GCD (int n1, int n2) {
		int x1 = Math.max(n1, n2);
		int x2 = Math.min(n1, n2);
		
		if (x2 == 0) return x1;
		
		return GCD(n2, n1%n2);
	}
}