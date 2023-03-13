import java.io.*;
import java.util.*;

public class Boj6064 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		tc: for (int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int ans=x; ans<=M*N; ans+=M) {
				if ((ans - y) % N == 0) {
					System.out.println(ans);
					continue tc;
				}
			}
			System.out.println(-1);
		}
	}
}
