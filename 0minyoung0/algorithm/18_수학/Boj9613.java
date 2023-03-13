import java.io.*;
import java.util.*;

public class Boj9613 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			long[] arr = new long[n];
			for (int i=0; i<n; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}
			
			long ans = 0;
			for (int i=0; i<n-1; i++) {
				for (int j=i+1; j<n; j++) {
					ans += GCD(arr[i], arr[j]);
				}
			}
			System.out.println(ans);
		}
	}
	
	private static long GCD(long n1, long n2) {
		long m1 = Math.max(n1, n2);
		long m2 = Math.min(n1, n2);
		
		if (m2 == 0) return m1;
		
		return GCD(m2, m1%m2);
	}
}