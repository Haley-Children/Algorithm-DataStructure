import java.io.*;
import java.util.*;

public class Boj11050 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		K = K > N / 2? N - K: K;
		
		int ans = 1;
		for (int i=N; i>N-K; i--) {
			ans *= i;
		}
		for (int i=2; i<=K; i++) {
			ans /= i;
		}
		
		System.out.println(ans);
	}
}