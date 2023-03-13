import java.io.*;

public class Boj1676 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int ans = 0;
		
		for (int i=5; i<=n; i+=5) {
			int temp = i;
			while (temp % 5 == 0) {
				temp /= 5;
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}