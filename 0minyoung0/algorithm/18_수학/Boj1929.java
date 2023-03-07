import java.io.*;
import java.util.*;

public class Boj1929 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		boolean[] notPrime = new boolean[1001];
		notPrime[1] = true;
		for (int i=2; i<=31; i++) {
			if (!notPrime[i]) {
				int x = 2 * i;
				while (x <= 1000) {
					notPrime[x] = true;
					x += i;
				}
			}
		}
		
		int n = Integer.parseInt(br.readLine());
		
		int ans = 0;
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			if (!notPrime[Integer.parseInt(st.nextToken())]) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}
