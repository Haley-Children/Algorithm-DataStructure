import java.io.*;
import java.util.*;

public class Boj1644 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수 구하기
		int n = Integer.parseInt(br.readLine());
		
		// n 이하인 소수 찾기
		List<Integer> prime = new ArrayList<>();
		boolean[] isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);
		isPrime[1] = false;
		for (int i=2; i<=n; i++) {
			if (!isPrime[i]) continue;
			prime.add(i);
			for (int x=2*i; x<=n; x+=i) {
				isPrime[x] = false;
			}
		}
		
		// 소수가 없는 경우 0 출력 후 리턴
		if (n == 1) {
			System.out.println(0);
			return;
		}
		
		// 투포인터 사용
		int ans = 0;
		int sum = prime.get(0);
		int e = 0;
		find: for (int s=0; s<prime.size(); s++) {
			while (sum < n) {
				if (++e == prime.size()) break find;
				sum += prime.get(e);
			}
			if (sum == n) ans++;
			sum -= prime.get(s);
		}
		
		// 답 출력
		System.out.println(ans);
	}
}