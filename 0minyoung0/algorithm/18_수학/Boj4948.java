import java.io.*;

public class Boj4948 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[] notPrime = new boolean[246913];
		for (int i=2; i<=499; i++) {
			if (!notPrime[i]) {
				for (int j=i*i; j<=246912; j+=i) {
					notPrime[j] = true;
				}
			}
		}
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) return;
			
			int ans = 0;
			for (int i=n+1; i<=2*n; i++) {
				if (!notPrime[i]) ans++;
			}
			System.out.println(ans);
		}
	}
}