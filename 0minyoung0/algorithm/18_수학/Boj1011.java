import java.io.*;
import java.util.*;

public class Boj1011 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		tc: for (int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			long x = Integer.parseInt(st.nextToken());
			long y = Integer.parseInt(st.nextToken());
			y -= x;
			
			long ans = (long)Math.sqrt(y-1);
			while (true) {
				// 1+2+...+(n-1)+n+(n-1)+...+2+1 = n^2
				if (ans * ans >= y) {
					sb.append(2*ans-1).append("\n");
					continue tc;
				}
				// 1+2+...+(n-1)+n+n+(n-1)+...+2+1 = n*(n+1)
				if (ans * (ans + 1) >= y) {
					sb.append(2*ans).append("\n");
					continue tc;
				}
				ans++;
			}
		}
		
		System.out.println(sb);
	}
}