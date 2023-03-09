import java.io.*;
import java.util.*;

public class Boj1057 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 참가자의 수
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken()) - 1;
		int i = Integer.parseInt(st.nextToken()) - 1;
		
		int ans = 1;
		int m = 2;
		while(true) {
			// 2^ans로 나눈 몫이 같으면 n라운드에서 만남
			if (k / m == i / m) {
				System.out.println(ans);
				return;
			}
			ans++;
			m*=2;
		}
	}
}