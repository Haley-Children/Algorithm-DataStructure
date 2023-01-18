// boj 1074
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1074 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		System.out.println(FindZ(n,r,c));
	}
	private static int FindZ(int n, int r, int c) {
		if (n == 0) return 0;
		int ans = 0;
		if (r >= (int)Math.pow(2, n-1)) {
			ans += (int)Math.pow(2, 2*n-1);
			r -= (int)Math.pow(2, n-1);
		}
		if (c >= (int)Math.pow(2, n-1)) {
			ans += (int)Math.pow(2, 2*n-2);
			c -= (int)Math.pow(2, n-1);
		}
		return ans + FindZ(n-1, r, c);
	}
}