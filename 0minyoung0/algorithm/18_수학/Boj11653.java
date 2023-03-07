import java.io.*;

public class Boj11653 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int prime = 2;
		
		while (n != 1) {
			if (n % prime == 0) {
				System.out.println(prime);
				n /= prime;
			}else if (prime * prime > n) {
				System.out.println(n);
				n = 1;
			}else {
				prime++;
			}
		}
	}
}
