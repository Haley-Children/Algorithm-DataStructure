import java.io.*;
import java.util.*;

public class Boj4796 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int case_num = 0;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			if (l == 0) {
				break;
			}
			
			int ans = v / p * l;
			ans += v % p > l? l : v % p;
			
			System.out.println("Case " + (++case_num) + ": " + ans);
		}
	}
}