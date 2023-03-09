import java.io.*;
import java.util.*;

public class Boj1292 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int temp = 1;
		int cnt = 0;
		for (int i=1; i<a; i++) {
			if (temp == ++cnt) {
				temp++;
				cnt = 0;
			}
		}
		
		int ans = 0;
		for (int i=a; i<=b; i++) {
			ans += temp;
			if (temp == ++cnt) {
				temp++;
				cnt = 0;
			}
		}
		
		System.out.println(ans);
	}
}