//boj 1629
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1629 {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		long c = Integer.parseInt(st.nextToken());
		System.out.println(pow(a,b,c));
	}
	private static long pow(long x, long y, long z) {
		if (y == 1) return x % z;
		long result = pow(x, y/2, z);
		result = result * result % z;
		if (y % 2 == 0) return result;
		else return result * x % z;
	}
}