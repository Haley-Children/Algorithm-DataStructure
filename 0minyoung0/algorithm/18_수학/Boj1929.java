import java.io.*;
import java.util.*;

public class Boj1929 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		boolean[] notPrime = new boolean[1000001];
		notPrime[1] = true;
		for (int i=2; i<=1000; i++) {
			if (!notPrime[i]) {
				for (int j=i*i; j<=1000000; j+=i) {
					notPrime[j] = true;
				}
			}
		}
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		for (int i=m; i<=n; i++) {
			if (!notPrime[i]) {
				sb.append(i + "\n");
			}
		}
		
		System.out.println(sb);
	}
}
