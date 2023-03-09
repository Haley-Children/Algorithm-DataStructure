import java.io.*;
import java.util.*;

public class Boj1476 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int e = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int ans = s;
		while ((ans - m) % 19 != 0) {
			ans += 28;
		}
		while ((ans - e) % 15 != 0) {
			ans += 28 * 19;
		}
		
		System.out.println(ans);
	}
}